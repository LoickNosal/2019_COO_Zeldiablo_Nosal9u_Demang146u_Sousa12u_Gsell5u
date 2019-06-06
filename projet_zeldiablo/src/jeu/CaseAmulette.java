package jeu;

/**
 * represente la case de fin du jeu
 * @author Loick
 *
 */
public class CaseAmulette extends Case{

	
	/**
	 * Constructeur
	 * @param x pos x
	 * @param y pos y
	 */
	public CaseAmulette(int x, int y) {
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
		return 5;
	}
	
	public String toString() {
		return "a";
	}
	
	
	

}
