package jeu;
/**
 * @author Nosal Loïck
 * Classe qui modélise un mur
 */
public class Mur extends Case {
	
	/**
	 * Constructeur de base d'un Mur
	 * @param x coordonnées x du mur
	 * @param y coordonnées y du mur
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

	@Override
	public boolean peutTraverser() {
		return false;
	}

}
