package org.fu.berlin.dbs2013.data;

public class Ort {

	private int o_id;
	private int PLZ;
	private String name;
	private double geo_breite;
	private double geo_laenge;
	private int s_id;

	public Ort() {

	}

	public Ort(int plz, String name, double geo_breite, double geo_laenge) {
		this.PLZ = plz;
		this.name = name;
		this.geo_breite = geo_breite;
		this.geo_laenge = geo_laenge;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	public int getPLZ() {
		return PLZ;
	}

	public void setPLZ(Integer PLZ) {
		this.PLZ = PLZ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGeo_breite() {
		return geo_breite;
	}

	public void setGeo_breite(Double geo_breite) {
		this.geo_breite = geo_breite;
	}

	public double getGeo_laenge() {
		return geo_laenge;
	}

	public void setGeo_laenge(Double geo_laenge) {
		this.geo_laenge = geo_laenge;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

}
