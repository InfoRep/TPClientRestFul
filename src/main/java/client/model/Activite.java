package client.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

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
	
	public static Activite createFromJSON(JSONObject json) throws Exception
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.FRANCE);
		
		return new Activite(
				(Integer)json.get("id"), 
				Sport.createFromJSON((JSONObject)json.get("sport")), 
				format.parse((String)json.get("dateJour")),
				(Integer)json.get("nbloc"),
				new Sejour((Integer)json.get("sejour"))
		);
	}
}
