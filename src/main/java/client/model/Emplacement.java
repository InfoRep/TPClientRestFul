package client.model;

import org.json.JSONObject;

public class Emplacement {
	private int num;
	private double surface;
	private int nbPersMax;
	
	private TypeEmplacement type;

	public Emplacement(int num)
	{
		this.num = num;
		this.surface = 0;
		this.nbPersMax = 0;
		this.type = null;
	}
	
	public Emplacement(int num, double surface, int nbPersMax, TypeEmplacement type) {
		this.num = num;
		this.surface = surface;
		this.nbPersMax = nbPersMax;
		this.type = type;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public int getNbPersMax() {
		return nbPersMax;
	}

	public void setNbPersMax(int nbPersMax) {
		this.nbPersMax = nbPersMax;
	}

	public TypeEmplacement getType() {
		return type;
	}

	public void setType(TypeEmplacement type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}
	
	public static Emplacement createFromJSON(JSONObject json) throws Exception
	{
		return new Emplacement(
				(Integer)json.get("numEmpl"), 
				(Double)json.get("surfaceEmpl"), 
				(Integer)json.get("nbPersMaxEmpl"), 
				TypeEmplacement.createFromJSON((JSONObject)json.get("typeEmplacement"))
		);
	}
}
