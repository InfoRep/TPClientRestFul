package client.model;

import org.json.JSONObject;

public class Client {
	private int num;
	private String adrRue;
	private String cp;
	private String nom;
	private String numPiece;
	private String piece;
	private String ville;
	
	public Client()
	{
		this.num = 0;
		this.adrRue = "";
		this.cp = "";
		this.nom = "";
		this.numPiece = "";
		this.piece = "";
		this.ville = "";
	}
	
	public Client(int num)
	{
		super();
		this.num = num;
	}
	
	public Client(int num, String adrRue, String cp, String nom, String numPiece, String piece, String ville) {
		super();
		this.num = num;
		this.adrRue = adrRue;
		this.cp = cp;
		this.nom = nom;
		this.numPiece = numPiece;
		this.piece = piece;
		this.ville = ville;
	}

	public String getAdrRue() {
		return adrRue;
	}

	public void setAdrRue(String adrRue) {
		this.adrRue = adrRue;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNum() {
		return num;
	}
	
	public static Client createFromJSON(JSONObject json) throws Exception
	{
		return new Client(
				(Integer)json.get("numCli"), 
				(String)json.get("adrRueCli"), 
				(String)json.get("cpCli"), 
				(String)json.get("nomCli"), 
				(String)json.get("numPieceCli"), 
				(String)json.get("pieceCli"), 
				(String)json.get("villeCli")
		);
	}
}
