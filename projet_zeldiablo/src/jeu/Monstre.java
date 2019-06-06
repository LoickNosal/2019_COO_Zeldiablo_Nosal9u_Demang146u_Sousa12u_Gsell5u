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
	protected int pvMax;
	protected Aventurier cible;

	/**
	 * Constructeur qui modélise un monstre
	 * @param pPv
	 * @param px
	 * @param py
	 * @param pDegat
	 * @param pPortee
	 * @param pNom
	 */
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
	
	public abstract void comportement();


	public void attaquer() {
		int distance = (int) Math.sqrt(Math.pow(cible.getX() - x, 2) + Math.pow(cible.getY() - y, 2));
		//Math.abs(distance);
		if(distance <= portee) {
			cible.subirDegat(degat);
		}
	}

	/**
	 * Override pour contraindre le deplacement des monstres et faire en sort qu'il ne se colle pas les murs
	 * @param cardinaux les points cardinaux
	 * @return vrai si le deplacement a reussi
	 */
	@Override
	public boolean seDeplacer(char cardinaux) {
		boolean bouger = false;
		switch(cardinaux) {
			case 'N':
				if (peutAvancer(x, (int) (y - vitesse - Case.TAILLE * 0.4))) {
					y -= vitesse;
					bouger = true;
				}
				break;
			case 'E':
				if (peutAvancer((int) (x + vitesse + Case.TAILLE * 0.4), y)) {
					x += vitesse;
					bouger = true;
				}
				break;
			case 'S':
				if (peutAvancer(x, y + vitesse)) {
					y += vitesse;
					bouger = true;
				}
				break;
			case 'O':
				if (peutAvancer((int) (x - vitesse - Case.TAILLE * 0.4), y)) {
					x -= vitesse;
					bouger = true;
				}
				break;
		}
		return bouger;
	}


	public int getId() {
		return id;
	}
	
	public int getDegat() {
		return degat;
	}

	public int getPortee() {
		return portee;
	}

	public int getPvMax() {
    	return this.pvMax;
    }
	
	public Aventurier getCible() {
		return cible;
	}
	
	public void setCible(Aventurier av) {
		cible = av;
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
				return new MonstreImmobile(50, posX,posY, 10, 50, "m1");
			case 2:
				return new MonstreAleatoire(50, posX, posY, 5, 50, "m2");
			case 3:
				return new MonstreSuivi(50, posX, posY, 5, 50, "m3");
            case 4:
                return new Boss(50, posX, posY, 5, 50, "Boss");
			default:
				return null;
		}
	}

}
