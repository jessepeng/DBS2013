package org.fu.berlin.dbs2013.data;

import java.util.Date;

public class Wettermessung {

	private double s_id;
	private Date datum;
	private double min_temp;
	private double durchschnitt_temp;
	private double max_temp;
	private double relative_feuchte;
	private double mittel_wind;
	private double max_wind;
	private double sonne;
	private double bedeckung;
	private double niederschlag;
	private double luftdruck;

	public Wettermessung() {
	}

	public double getS_id() {
		return s_id;
	}

	public void setS_id(double s_id) {
		this.s_id = s_id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public double getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(double min_temp) {
		this.min_temp = min_temp;
	}

	public double getDurchschnitt_temp() {
		return durchschnitt_temp;
	}

	public void setDurchschnitt_temp(double durchschnitt_temp) {
		this.durchschnitt_temp = durchschnitt_temp;
	}

	public double getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(double max_temp) {
		this.max_temp = max_temp;
	}

	public double getRelative_feuchte() {
		return relative_feuchte;
	}

	public void setRelative_feuchte(double relative_feuchte) {
		this.relative_feuchte = relative_feuchte;
	}

	public double getMittel_wind() {
		return mittel_wind;
	}

	public void setMittel_wind(double mittel_wind) {
		this.mittel_wind = mittel_wind;
	}

	public double getMax_wind() {
		return max_wind;
	}

	public void setMax_wind(double max_wind) {
		this.max_wind = max_wind;
	}

	public double getSonne() {
		return sonne;
	}

	public void setSonne(double sonne) {
		this.sonne = sonne;
	}

	public double getBedeckung() {
		return bedeckung;
	}

	public void setBedeckung(double bedeckung) {
		this.bedeckung = bedeckung;
	}

	public double getNiederschlag() {
		return niederschlag;
	}

	public void setNiederschlag(double niederschlag) {
		this.niederschlag = niederschlag;
	}

	public double getLuftdruck() {
		return luftdruck;
	}

	public void setLuftdruck(double luftdruck) {
		this.luftdruck = luftdruck;
	}

}
