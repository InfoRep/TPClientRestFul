package client.model;

public class Sport {
	private int code;
	private String libelle; //10 caractères
	private String uniteTps; //10 caractères
	private float tarif;
	
	public Sport(int code, String libelle, String uniteTps, float tarif) {
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

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}

	public int getCode() {
		return code;
	}
}
