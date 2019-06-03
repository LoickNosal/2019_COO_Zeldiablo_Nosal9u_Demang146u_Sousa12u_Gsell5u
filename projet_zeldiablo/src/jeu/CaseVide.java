package jeu;

/**
 * @author Nosal Loïck
 * Classe qui modélise une Case Vide
 */
public class CaseVide extends Case{
	
	
	/**
	 * Constructeur de base d'une case Vide
	 * @param x coordonnées x de la case Vide
	 * @param y coordonnées y de la case Vide
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

	public String toString() {
		return "-";
	}

	@Override
	public boolean peutTraverser() {
		return true;
	}

}
