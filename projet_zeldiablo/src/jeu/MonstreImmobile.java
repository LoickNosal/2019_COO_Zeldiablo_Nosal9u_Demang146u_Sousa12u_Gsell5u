package jeu;

/**
 * Classe qui modelise un monstre qui ne bouge pas
 * @author Alexandre Sousa
 *
 */
public class MonstreImmobile extends Monstre{

	/**
	 * Constructeur
	 * @param pPv pv du monstre
	 * @param px abscisse du monstre
	 * @param py ordonnée du mostre
	 * @param pDegat dégat du monstre
	 * @param pPortee portée du monstre
	 * @param pNom nom du monstre
	 */
	public MonstreImmobile( int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		this.id = 1;
	}

	/**
	 * le monstre attaque si l'aventurier en cible est a portée
	 */
	@Override
	public void comportement() {
		this.attaquer();
	}



}
