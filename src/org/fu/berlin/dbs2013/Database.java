package org.fu.berlin.dbs2013;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fu.berlin.dbs2013.data.Ort;
import org.fu.berlin.dbs2013.data.Wettermessung;

import com.mysql.jdbc.Driver;

/**
 * Bietet Funktionen, um auf die MySQL-Datenbank zuzugreifen und Querys auszuführen.
 * @author Jan-Christopher
 *
 */
public class Database {

	private static Database _instance;

	private static final String DATABASE_URL = "jdbc:mysql://dd17302.kasserver.com/d01740b7";
	private static final String DATABASE_USER = "d01740b7";
	private static final String DATABASE_PW = "Pxk2rPRX4bNg34Ns";

	private Driver _databaseDriver;
	private Connection _databaseConnection;

	private Database() {
		try {
			_databaseDriver = new Driver();
			_databaseConnection = DriverManager.getConnection(DATABASE_URL,
					DATABASE_USER, DATABASE_PW);
			_databaseConnection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gibt die aktuelle Instanz des Database-Objects zurück.
	 * @return
	 */
	public synchronized static Database getInstance() {
		if (_instance == null) {
			return (_instance = new Database());
		} else {
			return _instance;
		}
	}
	
	/**
	 * Erstellt eine neue Instanz falls keine vorhanden, gibt sie aber nicht zurück.
	 */
	public synchronized static void createInstance() {
		if (_instance == null) {
			_instance = new Database();
		}
	}
	
	/**
	 * Zerstört die Instanz und deregistriert den Treiber.
	 */
	public synchronized static void destroyInstance() {
		if (_instance != null) {
			getInstance().destroy();
		}
	}
	
	private void destroy() {
		try {
			_databaseConnection.close();
			DriverManager.deregisterDriver(_databaseDriver);
		} catch (SQLException e) {}
	}

	/**
	 * Gibt die Datenbank-Verbindung zurück, um Querys oder ähnlich auszuführen.
	 * @return
	 */
	public Connection getDatabaseConnection() {
		return _databaseConnection;
	}
	
	/**
	 * Führt eine Query auf der Datenbank aus und gibt das Ergebnis als eine Liste von gewünschten Objekten aus.
	 * Felder, die nicht in der SELECT-Abfrage vorkommen, werden auch nicht gesetzt.
	 * @param query
	 * 				Gewünschte Query der Form <code>SELECT a, b, c FROM table WHERE bedingung</code>
	 * @param clazz
	 * 				Gewünschte Klasse als Ergebnis
	 * @return
	 * 				Ergebnisliste
	 */
	public <T> List<T> performSelectQuery(String query, final Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		
		Connection connection = getDatabaseConnection();
		Statement statement = null;
		ResultSet results = null;
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			if (results.first()) {
				ResultSetMetaData metaData = results.getMetaData();
				List<String> columnNames = new ArrayList<String>(metaData.getColumnCount());
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					columnNames.add(metaData.getColumnLabel(i));
				}
				do {
					T resultObject = clazz.newInstance();
					
					int i = 1;
					for (String columnName : columnNames) {
						String setMethodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
						Method setMethod;
						try {
							Class<?> paramClass = null;
							switch (metaData.getColumnType(i)) {
							case Types.VARCHAR:
							case Types.CHAR:
								paramClass = String.class;
								break;
							case Types.INTEGER:
							case Types.BIGINT:
							case Types.NUMERIC:
								paramClass = Integer.class;
								break;
							case Types.DOUBLE:
							case Types.DECIMAL:
								paramClass = Double.class;
								break;
							}
							setMethod = clazz.getMethod(setMethodName, paramClass);
							setMethod.invoke(resultObject, results.getObject(columnName));
						} catch (NoSuchMethodException | InvocationTargetException e) {
							Logger.getGlobal().log(Level.WARNING, "Couldn't set field " + columnName + " in class " + clazz.getName() + ".");
						}
						i++;
					}
					
					result.add(resultObject);
				} while (results.next());
			} 
		} catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e) {
			destroyInstance();
			createInstance();
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException  e) {
			e.printStackTrace();
		} finally {
			try {
				if (results != null) results.close();
				if (statement != null) statement.close();
			} catch (SQLException e) {}
		}
		
		return result;
		
	}
	
	/**
	 * Fügt ein geändertes Objekt wieder in die Datenbank ein.
	 * @param object
	 * 				Das geänderte Objekt. Es werden alle Felder mit ihrem Namen in die Tabelle eingetragen. Daher müssen alle Felder auch als Spalten in der Tabelle vorhanden sein.
	 * @param table
	 * 				Die Tabelle, in die das Objekt eingefügt werden soll.
	 * @param where
	 * 				Eine where-Klausel, mit dem das Objekt eindeutig beschrieben werden kann.
	 * @param clazz
	 * 				Die Klasse, um die es sich handelt.
	 * @return
	 */
	public <T> boolean performUpdateQuery(T object, String table, String where) {
		Connection connection = getDatabaseConnection();
		Statement statement = null;
		boolean success = false;
		Class<?> clazz = object.getClass();
		
		StringBuilder queryString = new StringBuilder("UPDATE ").append(table).append(" SET ");
		try {
			statement = connection.createStatement();
			for (Field field : clazz.getDeclaredFields()) {
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Method getMethod = clazz.getMethod(getMethodName);
					Object fieldValue = getMethod.invoke(object);
					if (fieldValue != null) {
						queryString.append(fieldName).append("=");
						if (fieldValue instanceof String) queryString.append("'");
						queryString.append(fieldValue.toString());
						if (fieldValue instanceof String) queryString.append("'");
						queryString.append(", ");
					}
				} catch (IllegalArgumentException | NoSuchMethodException |  IllegalAccessException | InvocationTargetException e) {
					Logger.getGlobal().log(Level.WARNING, "Couldn't get value of field " + fieldName + " from Object " + clazz.getName());
				}
			}
			
			queryString.deleteCharAt(queryString.length() - 2).append("WHERE ").append(where);
			
			success = statement.execute(queryString.toString());
			
		} catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e) {
			destroyInstance();
			createInstance();
		} catch (SQLException | SecurityException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) statement.close();
			} catch (SQLException e) { }
		}
		
		return success;
	}

	/**
	 * Gibt eine Liste von Orten für Autocomplete zurück.
	 * @param from
	 * 			String der Form "12xxx", um nach PLZ zu suchen oder Beginn eines Ortsnamens, um nach dem Ort zu suchen.
	 * @return
	 * 			Liste von Vorschlägen
	 */
	public List<String> getAutoCompleteSuggestions(String from) {
		List<String> result = new ArrayList<String>();
		
		StringBuilder queryString = new StringBuilder("SELECT name, PLZ FROM Ort WHERE ");
		
		if (from.matches("[0-9]*")) {
			// numeric only
			int length = from.length();
			for (int i = 1; i <= 5 - length; i++) {
				from += "0";
			}
			int plz = Integer.valueOf(from);
			int plzTo = (plz + (int)(Math.pow(10, 5 - length)));
			
			queryString.append("PLZ >= ").append(plz).append(" AND PLZ <= ").append(plzTo).append(" ORDER BY plz");
		} else {
			// name
			queryString.append("name LIKE '").append(from).append("%' ORDER BY name");
		}
		
		List<Ort> suggestions = performSelectQuery(queryString.toString(), Ort.class);
		
		for (Ort ort : suggestions) {
			result.add(ort.getName() + " (" + ort.getPLZ() + ")");
		}
		
		return result;
	}
	
	public Wettermessung getLastWettermessung(String from) {
		String queryString = "SELECT * FROM Wettermessung m, Ort o WHERE o.plz = " + from + " AND o.hat_station = m.gemessen_von ORDER BY m.datum";
		
		List<Wettermessung> messungen = performSelectQuery(queryString, Wettermessung.class);
		
		return messungen.get(messungen.size() - 1);
	}
}
