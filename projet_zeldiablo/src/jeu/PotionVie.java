package jeu;

/**
 * modelise une potion de vie
 * @author Loick
 *
 */
public class PotionVie extends Item{

	/**
	 * constructeur
	 * @param x pos x
	 * @param y pos y 
	 */
	public PotionVie(int x, int y) {
		super(x, y);
		this.id = 0;
	}

	@Override
	public int typeItem() {
		return 0;
	}
	

}
