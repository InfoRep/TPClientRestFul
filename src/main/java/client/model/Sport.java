package client.model;

import org.json.JSONObject;

public class Sport {
	private int code;
	private String libelle; //10 caractères
	private String uniteTps; //10 caractères
	private double tarif;
	
	public Sport(int code, String libelle, String uniteTps, double tarif) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.uniteTps = uniteTps;
		this.tarif = tarif;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getUniteTps() {
		return uniteTps;
	}

	public void setUniteTps(String uniteTps) {
		this.uniteTps = uniteTps;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public int getCode() {
		return code;
	}
	
	public static Sport createFromJSON(JSONObject json) throws Exception
	{
		return new Sport(
				(Integer)json.get("codeSport"), 
				(String)json.get("libelleSport"), 
				(String)json.get("uniteTpsSport"),
				(Double)json.get("tarifUnite")
		);
	}
}
