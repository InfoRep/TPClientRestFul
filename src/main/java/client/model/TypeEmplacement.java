package client.model;

import org.json.JSONObject;

public class TypeEmplacement {
	private int code;
	private String lib; //30 caracteres
	private double tarif;
	
	public TypeEmplacement(int code)
	{
		this.code =  code;
		this.lib = null;
		this.tarif = 0;
	}
	
	public TypeEmplacement(int code, String lib, double tarif) {
		super();
		this.code = code;
		this.lib = lib;
		this.tarif = tarif;
	}

	public String getLib() {
		return lib;
	}

	public void setLib(String lib) {
		this.lib = lib;
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
	
	public static TypeEmplacement createFromJSON(JSONObject json) throws Exception
	{
		return new TypeEmplacement(
				(Integer)json.get("codeTypeE"), 
				(String)json.get("libtypepl"), 
				(Double)json.get("tariftypepl")
		);
	}
}
