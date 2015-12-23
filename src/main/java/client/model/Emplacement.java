package client.model;

public class Emplacement {
	private int num;
	private float surface;
	private int nbPersMax;
	
	private TypeEmplacement type;

	public Emplacement(int num, float surface, int nbPersMax, TypeEmplacement type) {
		super();
		this.num = num;
		this.surface = surface;
		this.nbPersMax = nbPersMax;
		this.type = type;
	}

	public float getSurface() {
		return surface;
	}

	public void setSurface(float surface) {
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
}
