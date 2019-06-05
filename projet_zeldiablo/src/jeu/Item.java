package jeu;

/**
 * modelise les objets du jeu
 * @author Lo�ck
 *
 */
public abstract class Item {
	
	/**
	 * position x de l'objet
	 */
	protected int posX;
	/**
	 * position y de l'objet
	 */
	protected int posY;
	/**
	 * indique si l'item est ramasse
	 */
	protected boolean ramasse;
	
	/**
	 * constructeur
	 * @param x position x de l'item
	 * @param y position y de l'item
	 */
	public Item(int x, int y ) {
		
		if (x<0) {
			x = 0;
		}
		if (y<0) {
			y = 0;
		}
		this.posX = x;
		this.posY = y;
		this.ramasse = false;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public boolean getRamasse() {
		return this.ramasse;
	}
	
	/**
	 * indique que l'objet est ramasse
	 */
	public void setRamasse() {
		this.ramasse = true;
	}
	
	/**
	 * renvoit un int qui correspond a l'item (0 : potion de vie, ...)
	 * @return int correspond a l'item
	 */
	public abstract int typeItem();
}