package client.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

public class Sejour {
	private int num;
	
	private Date dateDeb;
	private Date dateFin;
	private int nbPersonnes;
	
	private Client client;
	private Emplacement emplacement;
	
	public Sejour()
	{
		this.num = 0;
		this.dateDeb = null;
		this.dateFin = null;
		this.nbPersonnes = 0;
		this.client = null;
		this.emplacement = null;
	}
	
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
	
	public static Sejour createFromJSON(JSONObject json) throws Exception
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.FRANCE);

		return new Sejour(
				(Integer)json.get("numSej"), 
				format.parse((String)json.get("datedebSej")),
				format.parse((String)json.get("dateFinSej")),
				(Integer)json.get("nbPersonnes"),
				Client.createFromJSON((JSONObject)json.get("client")),
				Emplacement.createFromJSON((JSONObject)json.get("emplacement"))
		);
	}
}
