package jeu;

import java.awt.Rectangle;

/*
 * Classe abstraite qui modèle une case
 * @author Nosal Loïck
 */
public abstract class Case {
	
	protected final static int taille = 80;
	
	/**
	 * Coordonnées x de la case
	 */
	protected int posX;
	/**
	 * Coordonnées y de la case
	 */
	protected int posY;
	
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	
	public boolean estDedans(int x, int y) {
		boolean res = false;
		Rectangle r = new Rectangle(x,y,taille,taille);
		if (r.contains(x,y)) {
			res = true;
		}
		return res;
	}
	
	public abstract boolean peutTraverser();

	

}
