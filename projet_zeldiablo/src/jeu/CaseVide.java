package jeu;

/**
 * @author Nosal Lo�ck
 * Classe qui mod�lise une Case Vide
 */
public class CaseVide extends Case{
	
	
	/**
	 * Constructeur de base d'une case Vide
	 * @param x coordonn�es x de la case Vide
	 * @param y coordonn�es y de la case Vide
	 */
	public CaseVide(int x, int y) {
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		this.posX = x;
		this.posY = y;
	}

}
