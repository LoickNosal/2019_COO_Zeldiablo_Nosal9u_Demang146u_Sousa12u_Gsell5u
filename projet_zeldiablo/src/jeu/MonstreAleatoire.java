package jeu;

public class MonstreAleatoire extends Monstre{

	public MonstreAleatoire(int pPv, int px, int py, int pDegat, int pPortee, String pNom, Labyrinthe pLab) {
		super(2, pPv, px, py, pDegat, pPortee, pNom, pLab);
	}

	@Override
	public void seDeplacer(char cardinaux) {
		// vide
		
	}

	@Override
	public void seDeplacer(Aventurier av) {
		int futureposX = 0;
    	int futureposY = 0;
    	int aleatoire = (int)(Math.random() * ((3) + 1));
    	
    	avancer(aleatoire);
		
	}

}
