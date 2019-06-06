package jeu;

/**
 * case qui represente une mine
 * @author Loick
 *
 */
public class CaseMine extends Case{

	/**
	 * constructeur
	 * @param x pos x
	 * @param y pos y
	 */
	public CaseMine(int x, int y) {
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

	@Override
	public int typeCase() {
		return 4;
	}
	
	public String toString() {
		return "m";
	}

}
