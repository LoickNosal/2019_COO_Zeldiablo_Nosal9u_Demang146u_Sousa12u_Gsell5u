package jeu;

/**
 * represente un piege
 * @author Loïck
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
	}

	@Override
	public boolean peutTraverser() {
		return true;
	}

	@Override
	public int typeCase() {
		return 3;
	}

}
