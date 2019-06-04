package jeu;

public class MonstreImmobile extends Monstre{

	public MonstreImmobile(int pId, int pPv, int px, int py, int pDegat, int pPortee, String pNom, Labyrinthe pLab) {
		super(pId, pPv, px, py, pDegat, pPortee, pNom, pLab);
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
