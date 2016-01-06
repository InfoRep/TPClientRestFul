package client.model;

import org.json.JSONObject;

public class TypeEmplacement {
	private int code;
	private String lib; //30 caracteres
	private float tarif;
	
	public TypeEmplacement(int code)
	{
		this.code =  code;
		this.lib = null;
		this.tarif = 0;
	}
	
	public TypeEmplacement(int code, String lib, float tarif) {
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

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}

	public int getCode() {
		return code;
	}
	
	public static TypeEmplacement createFromJSON(JSONObject json) throws Exception
	{
		return new TypeEmplacement(
				(Integer)json.get("num"), 
				(String)json.get("lib"), 
				(Float)json.get("tarif")
		);
	}
}
