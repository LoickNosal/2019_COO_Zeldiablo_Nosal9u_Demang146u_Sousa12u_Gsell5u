package jeu;

import java.awt.Rectangle;

/*
 * Classe abstraite qui modèle une case
 * @author Nosal Loïck
 */
public abstract class Case {
	
	/**
	 * taille d'une case (carré)
	 */
	protected final static int taille = 60;
	
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
	/**
	 * indique si x, y appartient à la case
	 * @param x coordonnee x a tester
	 * @param y coordonnee y a tester
	 * @return boolean qui indique si x,y est dans la case
	 */
	public boolean estDedans(int x, int y) {
		boolean res = false;
		Rectangle r = new Rectangle(posX*taille,posY*taille,taille,taille);
		if (r.contains(x,y)) {
			res = true;
		}
		
		return res;
	}
	
	/**
	 * permet de savoir si une case peut etre traverse
	 * @return boolean qui indique si la case peut etre traverse
	 */
	public abstract boolean peutTraverser();
	
		/**
		* renvoit un int en fonction du type de case
		* renvoit 0 : case vide
		* renvoit 1 : mur
		* renvoit 2 : sortie du labyrinthe
		* @return int en fonction du type de case
		*/
		public abstract int typeCase();
	

	

}
