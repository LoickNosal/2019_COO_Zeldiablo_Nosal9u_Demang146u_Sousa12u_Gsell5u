package jeu;
/**
 * represente une potion de force pour augmenter la force du joueur
 * @author Loick
 *
 */
public class PotionForce extends Item{

	/**
	 * constructeur
	 * @param x pos x
	 * @param y pos y
	 */
	public PotionForce(int x, int y) {
		super(x, y);
		this.id = 2;
	}

	@Override
	public int typeItem() {
		return 2;
	}

}
