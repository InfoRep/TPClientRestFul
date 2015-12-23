package client.model;

import java.util.Date;

public class Sejour {
	private int num;
	
	private Date dateDeb;
	private Date dateFin;
	private int nbPersonnes;
	
	private Client client;
	private Emplacement emplacement;
	
	
	
	public Sejour(int num, Date dateDeb, Date dateFin, int nbPersonnes, Client client, Emplacement emplacement) {
		super();
		this.num = num;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.nbPersonnes = nbPersonnes;
		this.client = client;
		this.emplacement = emplacement;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbPersonnes() {
		return nbPersonnes;
	}

	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Emplacement getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Emplacement emplacement) {
		this.emplacement = emplacement;
	}

	public int getNum() {
		return num;
	}
}
