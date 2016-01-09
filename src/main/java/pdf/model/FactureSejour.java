package pdf.model;

import java.util.Date;

import client.model.Sejour;

public class FactureSejour {
	private int num;
	private Sejour sejour;
	private Date date;
	
	private double prixTotal;

	public FactureSejour(int num, Sejour sejour, Date date, double prixTotal) {
		super();
		this.num = num;
		this.sejour = sejour;
		this.date = date;
		this.prixTotal = prixTotal;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public int getNum() {
		return num;
	}

	public Sejour getSejour() {
		return sejour;
	}

	public Date getDate() {
		return date;
	}	
}
