package jeu;
/**
 * @author Nosal Lo�ck
 * Classe qui mod�lise un mur
 */
public class Mur extends Case {
	
	/**
	 * Constructeur de base d'un Mur
	 * @param x coordonn�es x du mur
	 * @param y coordonn�es y du mur
	 */
	public Mur(int x, int y) {
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		this.posX = x;
		this.posY = y;
	}

	public String toString() {
		return "x";
	}

}
