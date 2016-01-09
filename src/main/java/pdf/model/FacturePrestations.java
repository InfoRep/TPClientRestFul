package pdf.model;

import java.util.Date;
import java.util.List;

import client.model.Activite;
import client.model.Sejour;

public class FacturePrestations {
	private int num;
	private Sejour sejour;
	private Date date;
	
	private List<Activite> activites;
	
	private double prixTotal;


	public FacturePrestations(int num, Sejour sejour, Date date, List<Activite> activites, double prixTotal) {
		super();
		this.num = num;
		this.sejour = sejour;
		this.date = date;
		this.activites = activites;
		this.prixTotal = prixTotal;
	}
	
	

	public double getPrixTotal() {
		return prixTotal;
	}



	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Activite> getActivites() {
		return activites;
	}

	public void setActivites(List<Activite> activites) {
		this.activites = activites;
	}

	public int getNum() {
		return num;
	}

	public Sejour getSejour() {
		return sejour;
	}
	
	
	
}
