package jeu;

/**
 * repr�sente la sortie du labyrinthe
 * @author Lo�ck
 *
 */
public class CaseSortie extends Case {
	
	public CaseSortie(int x, int y) {
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
		return true;
	}

	@Override
	public int typeCase() {
		return 2;
	}
	
	public String toString() {
		return "p";
	}

}
