package jeu;

import moteurJeu.JeuPerso;

/**
 * Classe qui modélise un monstre immobile 
 * @author Alexandre Sousa
 *
 */
public class MonstreImmobile extends Entite{
	private int degat;
	
	public MonstreImmobile(int pPv, int px, int py, int pdegat, String pNom, Labyrinthe pLab) {
		super(pPv, px, py, pNom, pLab);
		degat = pdegat;
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
}
