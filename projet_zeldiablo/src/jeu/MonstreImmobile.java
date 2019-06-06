package jeu;

/**
 * Classe qui modelise un monstre qui ne bouge pas
 * @author Alexandre Sousa
 *
 */
public class MonstreImmobile extends Monstre{

	
	/**
	 * constructeur du monstre immobile
	 * @param pPv
	 * @param px
	 * @param py
	 * @param pDegat
	 * @param pPortee
	 * @param pNom
	 */
	public MonstreImmobile( int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		this.id = 1;
	}

	@Override
	public void comportement() {
		this.attaquer();
	}

	

}
