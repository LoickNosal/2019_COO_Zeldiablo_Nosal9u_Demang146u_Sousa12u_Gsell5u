package jeu;

/**
 * Classe qui modélise un monstre qui bouge aléatoirement
 * @author Alexandre Sousa
 *
 */
public class MonstreAleatoire extends Monstre{

	public MonstreAleatoire(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		id = 2;
	}

	@Override
	public void seDeplacer(char cardinaux) {
		// vide
		
	}

	@Override
	public void seDeplacer(Aventurier av) {
    	int aleatoire = (int)(Math.random() * ((3) + 1));
    	
    	avancer(aleatoire);
		
	}

}
