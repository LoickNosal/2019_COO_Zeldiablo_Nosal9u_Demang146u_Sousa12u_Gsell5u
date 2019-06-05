package jeu;

/**
 * modelise une potion de vie
 * @author Lo�ck
 *
 */
public class PotionVie extends Item{

	public PotionVie(int x, int y) {
		super(x, y);
	}

	@Override
	public int typeItem() {
		return 0;
	}
	

}
