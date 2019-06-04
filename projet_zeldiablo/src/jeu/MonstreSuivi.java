package jeu;

public class MonstreSuivi extends Monstre{

	public MonstreSuivi(int pId, int pPv, int px, int py, int pDegat, int pPortee, String pNom, Labyrinthe pLab) {
		super(pId, pPv, px, py, pDegat, pPortee, pNom, pLab);
	}

	public void seDeplacer(char cardinaux) {
		// vide
	}

	public void seDeplacer(Aventurier av) {
    	int direction = 1 ;
    	int persoX = av.getX();
    	int persoY = av.getY();
    	// 0: Nord, 1: Est, 2: Sud, 3: Ouest
    	
    	
    	avancer(direction);
	}
	
}
