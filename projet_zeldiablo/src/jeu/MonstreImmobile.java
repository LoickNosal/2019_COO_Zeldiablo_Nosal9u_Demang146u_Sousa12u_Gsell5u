package jeu;

/**
 * Classe qui modélise un monstre qui ne bouge pas
 * @author Alexandre Sousa
 *
 */
public class MonstreImmobile extends Monstre{

	public MonstreImmobile( int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		id = 1;
	}

	@Override
	public void seDeplacer(char cardinaux) {
		// vide
		
	}

	@Override
	public void seDeplacer(Aventurier av) {
		// Ne se déplace pas
		
	}

}
