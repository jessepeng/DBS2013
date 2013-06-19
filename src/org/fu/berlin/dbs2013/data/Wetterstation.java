package org.fu.berlin.dbs2013.data;

public class Wetterstation {

	private Integer s_id;
	private String standort;
	private Double geo_breite;
	private Double geo_laenge;
	private Double hoehe;

	public Wetterstation() {
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getStandort() {
		return standort;
	}

	public void setStandort(String standort) {
		this.standort = standort;
	}

	public Double getGeo_breite() {
		return geo_breite;
	}

	public void setGeo_breite(Double geo_breite) {
		this.geo_breite = geo_breite;
	}

	public Double getGeo_laenge() {
		return geo_laenge;
	}

	public void setGeo_laenge(Double geo_laenge) {
		this.geo_laenge = geo_laenge;
	}

	public Double getHoehe() {
		return hoehe;
	}

	public void setHoehe(Double hoehe) {
		this.hoehe = hoehe;
	}

}
