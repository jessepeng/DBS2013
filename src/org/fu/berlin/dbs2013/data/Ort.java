package org.fu.berlin.dbs2013.data;

public class Ort {
	
	private int id;
	private int plz;
	private String name;
	private double geo_breite;
	private double geo_laenge;
	
	public Ort() {
		
	}
	
	public Ort(int plz, String name, double geo_breite, double geo_laenge) {
		this.plz = plz;
		this.name = name;
		this.geo_breite = geo_breite;
		this.geo_laenge = geo_laenge;
	}
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(Integer plz) {
		this.plz = plz;
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
	public void setGeo_breite(double geo_breite) {
		this.geo_breite = geo_breite;
	}
	public double getGeo_laenge() {
		return geo_laenge;
	}
	public void setGeo_laenge(double geo_laenge) {
		this.geo_laenge = geo_laenge;
	}

}
