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
	
	public void attaquer(JeuPerso jeuPerso) {
		Aventurier perso = jeuPerso.getPerso();
		if(x - perso.x == 1) // A FAIRE
		perso.subirDegat(degat);
	}
}
