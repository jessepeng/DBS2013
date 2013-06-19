package org.fu.berlin.dbs2013.data;

import java.util.Date;

public class Wettermessung {

	private Double s_id;
	private Date datum;
	private Double min_temp;
	private Double durchschnitt_temp;
	private Double max_temp;
	private Double relative_feuchte;
	private Double mittel_wind;
	private Double max_wind;
	private Double sonne;
	private Double bedeckung;
	private Double niederschlag;
	private Double luftdruck;

	public Wettermessung() {
	}

	public Double getS_id() {
		return s_id;
	}

	public void setS_id(Double s_id) {
		this.s_id = s_id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Double getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(Double min_temp) {
		this.min_temp = min_temp;
	}

	public Double getDurchschnitt_temp() {
		return durchschnitt_temp;
	}

	public void setDurchschnitt_temp(Double durchschnitt_temp) {
		this.durchschnitt_temp = durchschnitt_temp;
	}

	public Double getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(Double max_temp) {
		this.max_temp = max_temp;
	}

	public Double getRelative_feuchte() {
		return relative_feuchte;
	}

	public void setRelative_feuchte(Double relative_feuchte) {
		this.relative_feuchte = relative_feuchte;
	}

	public Double getMittel_wind() {
		return mittel_wind;
	}

	public void setMittel_wind(Double mittel_wind) {
		this.mittel_wind = mittel_wind;
	}

	public Double getMax_wind() {
		return max_wind;
	}

	public void setMax_wind(Double max_wind) {
		this.max_wind = max_wind;
	}

	public Double getSonne() {
		return sonne;
	}

	public void setSonne(Double sonne) {
		this.sonne = sonne;
	}

	public Double getBedeckung() {
		return bedeckung;
	}

	public void setBedeckung(Double bedeckung) {
		this.bedeckung = bedeckung;
	}

	public Double getNiederschlag() {
		return niederschlag;
	}

	public void setNiederschlag(Double niederschlag) {
		this.niederschlag = niederschlag;
	}

	public Double getLuftdruck() {
		return luftdruck;
	}

	public void setLuftdruck(Double luftdruck) {
		this.luftdruck = luftdruck;
	}


}
