package jeu;

import moteurJeu.JeuPerso;

/**
 * Classe qui modélise un monstre 
 * @author Alexandre Sousa
 *
 */
public abstract class Monstre extends Entite{
	private int id;
	private int degat;
	private int portee;
	
	public Monstre(int pId, int pPv, int px, int py, int pDegat, int pPortee, String pNom, Labyrinthe pLab) {
		super(pPv, px, py, pNom, pLab);
		
		if(pDegat < 0) {
			pDegat = 0;
		}
		
		if(pPortee < 0) {
			pPortee = 0;
		}
		
		degat = pDegat;
		portee = pPortee;
		if(!lab.caseTraversable(px, py)) {
			id = -1;
			pv = -1;
			x = -1;
			y = -1;
			degat = -1;
			portee = -1;
			nom = null;
			lab = null;
		}
	}
	
	
	public void attaquer(Aventurier pPerso, int dx, int dy) {
		Aventurier perso = pPerso;
		int distance = (int) Math.sqrt(Math.pow(dx - x, 2) + Math.pow(dy - y, 2));
		if(distance <= portee) {
			perso.subirDegat(degat);
		}
	}

	@Override
	public abstract void seDeplacer(char cardinaux);
	@Override
	public abstract void seDeplacer(Aventurier av);
	
	public int getDegat() {
		return degat;
	}
	
	public int getPortee() {
		return portee;
	}

	
}
