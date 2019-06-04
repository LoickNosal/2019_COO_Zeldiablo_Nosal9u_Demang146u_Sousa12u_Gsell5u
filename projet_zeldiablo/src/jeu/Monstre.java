package jeu;

import moteurJeu.JeuPerso;

/**
 * Classe qui modélise un monstre immobile 
 * @author Alexandre Sousa
 *
 */
public class Monstre extends Entite{
	private int degat;
	
	public Monstre(int pPv, int px, int py, int pDegat, String pNom, Labyrinthe pLab) {
		super(pPv, px, py, pNom, pLab);
		
		if(pDegat < 0) {
			pDegat = 0;
		}
		degat = pDegat;
		if(!lab.caseTraversable(px, py)) {
			pv = -1;
			x = -1;
			y = -1;
			degat = -1;
			nom = null;
			lab = null;
		}
	}
	
	public void attaquer(Aventurier pPerso, int dx, int dy) {
		Aventurier perso = pPerso;
		int distance = (int) Math.sqrt(Math.pow(dx - x, 2) + Math.pow(dy - y, 2));
		if(distance <= 50) {
			perso.subirDegat(degat);
		}
	}

	@Override
	public void seDeplacer(char cardinaux) {
		
	}
	
	public int getDegat() {
		return degat;
	}
}
