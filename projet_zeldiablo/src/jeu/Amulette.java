package jeu;

/**
 * represente l'amulette pour finir le jeu
 * @author Loick
 *
 */
public class Amulette extends Item{

	/**
	 * constructeur 
	 * @param x position x 
	 * @param y position y
	 */
	public Amulette(int x, int y) {
		super(x, y);
		this.id = 1;
	}

	@Override
	public int typeItem() {
		return 1;
	}

}
