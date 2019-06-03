package jeu;

import java.awt.Rectangle;

/*
 * Classe abstraite qui mod�le une case
 * @author Nosal Lo�ck
 */
public abstract class Case {
	
	protected final static int taille = 80;
	
	/**
	 * Coordonn�es x de la case
	 */
	protected int posX;
	/**
	 * Coordonn�es y de la case
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
