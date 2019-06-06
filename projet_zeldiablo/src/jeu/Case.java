package jeu;

import java.awt.Rectangle;


/**
 * Classe abstraite qui modelise une case
 * @author Nosal Loick
 */
public abstract class Case {
	
	/**
	 * taille d'une case (carre)
	 */
	public static int TAILLE = 60;
	
	/**
	 * Coordonnees x de la case
	 */
	protected int posX;
	/**
	 * Coordonnees y de la case
	 */
	protected int posY;
	/**
	 * indique si on est deja passe sur la case
	 */
	protected boolean estPasseDessus;
	
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	/**
	 * indique si x, y appartient � la case
	 * @param x coordonnee x a tester
	 * @param y coordonnee y a tester
	 * @return boolean qui indique si x,y est dans la case
	 */
	public boolean estDedans(int x, int y) {
		boolean res = false;
		Rectangle r = new Rectangle(posX * TAILLE,posY * TAILLE, TAILLE, TAILLE);
		if (r.contains(x,y)) {
			res = true;
		}
	
		return res;
	}
	
	public boolean getEstPasse() {
		return this.estPasseDessus;
	}
	
	public void setEstPasse() {
		this.estPasseDessus = true;
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
