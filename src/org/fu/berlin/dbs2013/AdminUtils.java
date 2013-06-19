package org.fu.berlin.dbs2013;

import java.util.List;

import org.fu.berlin.dbs2013.data.Ort;
import org.fu.berlin.dbs2013.data.Wetterstation;

/**
 * Utility class für administrative Aufgaben
 * @author Jan-Christopher
 *
 */
public class AdminUtils {

	private AdminUtils() { }
	
	public static boolean connectOrtWithWetterstation() {
		boolean success = false;
		
		Database db = Database.getInstance();
		
		List<Ort> orte = db.performSelectQuery("SELECT * FROM Ort", Ort.class);
		List<Wetterstation> stationen = db.performSelectQuery("SELECT s_id, geo_breite, geo_laenge FROM Wetterstation", Wetterstation.class); 
		
		return success;
	}
	
}
