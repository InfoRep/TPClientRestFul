package client.model;

import java.sql.Date;

public class Activite {
	private int id;
	private Sport sport;
	private Date dateJour;	
	private int nbloc;
	
	private Sejour sejour;

	public Activite(int id, Sport sport, Date dateJour, int nbloc, Sejour sejour) {
		super();
		this.id = id;
		this.sport = sport;
		this.dateJour = dateJour;
		this.nbloc = nbloc;
		this.sejour = sejour;
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getDateJour() {
		return dateJour;
	}

	public void setDateJour(Date dateJour) {
		this.dateJour = dateJour;
	}

	public int getNbloc() {
		return nbloc;
	}

	public void setNbloc(int nbloc) {
		this.nbloc = nbloc;
	}

	public Sejour getSejour() {
		return sejour;
	}

	public void setSejour(Sejour sejour) {
		this.sejour = sejour;
	}

	public Sport getSport() {
		return sport;
	}
}
