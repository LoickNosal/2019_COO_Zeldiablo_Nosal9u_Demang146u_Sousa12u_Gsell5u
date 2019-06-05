package jeu;

/**
 * represente un piege
 * @author Loick
 *
 */
public class CasePiege extends Case{
	
	
	
	public CasePiege(int x, int y) {
		if (x < 0) {
		x = 0;
		}
		if (y < 0) {
		y = 0;
		}
		this.posX = x;
		this.posY = y;
		this.estPasseDessus = false;
	}

	@Override
	public boolean peutTraverser() {
		return true;
	}
	
	public String toString() {
		return "t";
	}

	@Override
	public int typeCase() {
		return 3;
	}

}
