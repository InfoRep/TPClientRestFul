package client.model;

import java.sql.Date;

public class Activite {
	private int code;
	private Date dateJour;	
	private int nbloc;
	
	private Sejour sejour;

	public Activite(int code, Date dateJour, int nbloc, Sejour sejour) {
		super();
		this.code = code;
		this.dateJour = dateJour;
		this.nbloc = nbloc;
		this.sejour = sejour;
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

	public int getCode() {
		return code;
	}
}
