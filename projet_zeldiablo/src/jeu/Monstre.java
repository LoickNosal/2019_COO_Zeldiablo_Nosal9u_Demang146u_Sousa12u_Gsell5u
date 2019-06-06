package jeu;

/**
 * Classe qui modelise un monstre
 * @author Alexandre Sousa
 *
 */
public abstract class Monstre extends Entite {

	/**
	 * id du monstre unique
	 */
	protected int id;
	/**
	 * degat du monstre
	 */
	protected int degat;
	/**
	 * portee du monstre
	 */
	protected int portee;
	/**
	 * la cible du monstre
	 */
	protected Aventurier cible;

	/**
	 * Constructeur qui crée un monstre
	 * @param pPv pv du monstre
	 * @param px abscisse du monstre
	 * @param py ordonnée du mostre
	 * @param pDegat dégat du monstre
	 * @param pPortee portée du monstre
	 * @param pNom nom du monstre
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

	/**
	 * gere le comportement du monstre (deplacement/attaque)
	 */
	public abstract void comportement();

	/**
	 *permet d'attaquer la cible
	 */
	public boolean attaquer() {
		int distance = (int) Math.sqrt(Math.pow(cible.getX() - x, 2) + Math.pow(cible.getY() - y, 2));
		if(distance <= portee) {
			cible.subirDegat(degat);
			return true;
		}
		return false;
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
				return new MonstreImmobile(50, posX,posY, 10, 50, "Alexandre");
			case 2:
				return new MonstreAleatoire(50, posX, posY, 5, 50, "Paul");
			case 3:
				return new MonstreSuivi(50, posX, posY, 5, 50, "Loick");
            case 4:
                return new Boss(100, posX, posY, 5, 50, "Louis");
			default:
				return null;
		}
	}

}
