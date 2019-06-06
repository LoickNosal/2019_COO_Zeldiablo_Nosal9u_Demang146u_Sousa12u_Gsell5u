package jeu;

import java.awt.Rectangle;

import moteurJeu.DessinPerso;
import org.junit.Test;

/*
 * Classe abstraite qui mod�le une case
 * @author Nosal Lo�ck
 */
public abstract class Case {
	
	/**
	 * taille d'une case (carr�)
	 */
	public static int TAILLE = 60;
	
	/**
	 * Coordonn�es x de la case
	 */
	protected int posX;
	/**
	 * Coordonn�es y de la case
	 */
	protected int posY;
	/*
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
