package jeu;

/**
 * Classe qui modélise un monstre
 * @author Alexandre Sousa
 *
 */
public abstract class Monstre extends Entite {
	protected int id;
	protected int degat;
	protected int portee;
	private int pvMax;

	public Monstre(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pNom);

		if(pDegat < 0) {
			pDegat = 0;
		}

		if(pPortee < 0) {
			pPortee = 0;
		}
		degat = pDegat;
		portee = pPortee;
		this.pvMax = pPv;
		this.vitesse = 3;

	}
	
	public int getPvMax() {
    	return this.pvMax;
    }
	
	public boolean peutAvancer(int posX, int posY) {
		boolean res = false;
			if (this.lab.estSurUnObstacle(posX,posY) == false) {
				res = true;
			}
		return res;

	}
	
	public abstract void comportement(Aventurier pPerso);


	public void attaquer(Aventurier pPerso) {
		Aventurier perso = pPerso;
		int distance = (int) Math.sqrt(Math.pow(pPerso.getX() - x, 2) + Math.pow(pPerso.getY() - y, 2));
		Math.abs(distance);
		if(distance <= portee) {
			perso.subirDegat(degat);
		}
	}

	public int getDegat() {
		return degat;
	}

	public int getPortee() {
		return portee;
	}

	public int getId() {
		return id;
	}


	/**
	 * creer un monstre a partir d'un ID
	 *
	 * @param id id du monstre a creer
	 * @return le monstre correspondant a l'ID
	 */
	public static Monstre creerMonstreParID(int id, int posX, int posY) {
		switch (id) {
			case 1:
				return new MonstreImmobile(50, posX,posY, 1, 50, "m1");
			case 2:
				return new MonstreAleatoire(50, posX, posY, 1, 50, "m2");
			case 3:
				return new MonstreSuivi(50, posX, posY, 1, 50, "m3");
			default:
				return null;
		}
	}

}
