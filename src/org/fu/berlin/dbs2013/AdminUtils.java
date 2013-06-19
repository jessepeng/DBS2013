package org.fu.berlin.dbs2013;

import java.util.List;

import org.fu.berlin.dbs2013.data.Ort;
import org.fu.berlin.dbs2013.data.Wetterstation;
import uk.me.jstott.jcoord.LatLng;

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
		
		for (int i=0;i<orte.size();i++){
			/** 
			 * tempS_id beinhaltet die S_ID der Station mit der aktuell geringsten Entfernung zum aktuellen Ort und wird mit der nullten S_ID initialisiert
			 * tempOrt ist ein LatLng Objekt des aktuellen Ortes für die Abstandsberechnung
			 * tempMinDistance aktuell geringste Entfernung, initialisiert mit der Entfernung zur Station an nullter Stelle
			 */
			Integer tempS_id = stationen.get(0).getS_id();
			LatLng tempOrt = new LatLng(orte.get(i).getGeo_breite(),orte.get(i).getGeo_laenge());
			double tempMinDistance = tempOrt.distance(new LatLng(stationen.get(0).getGeo_breite(),stationen.get(0).getGeo_laenge()));
			
			for (int j=1;j<stationen.size();j++){
				double tempDistance = tempOrt.distance(new LatLng(stationen.get(j).getGeo_breite(),stationen.get(j).getGeo_laenge()));
				if (tempDistance<tempMinDistance){
					tempMinDistance = tempDistance;
					tempS_id = stationen.get(j).getS_id();
				}
			}
			
			orte.get(i).setS_id(tempS_id);
			db.performUpdateQuery(orte.get(i),"Ort", "o_id=\'"+orte.get(i).getO_id()+"\'");
		}
		
		
		return success;
	}
	
}
