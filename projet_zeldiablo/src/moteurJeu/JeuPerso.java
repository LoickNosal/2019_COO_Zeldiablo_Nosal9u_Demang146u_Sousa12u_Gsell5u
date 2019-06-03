package moteurJeu;

import jeu.Aventurier;

/**
 * représente le jeu Zeldiablo
 * @author Loïck
 *
 */
public class JeuPerso implements Jeu{
	
	/**
	 * personnage du jeu
	 */
	private Aventurier personnage;
	
	public JeuPerso(Aventurier av) {
		super();
		this.personnage = av;
	}

	@Override
	/**
	 * modifie la position (x,y) du personnage en fonction
	 * des valeurs des attributs gauche,droite,haut,bas
	 */
	public void evoluer(Commande commandeUser) {
		if (commandeUser.bas == true) {
			this.personnage.seDeplacer('S');
		}else if (commandeUser.droite == true) {
			this.personnage.seDeplacer('E');
		}else if (commandeUser.gauche == true) {
			this.personnage.seDeplacer('O');
		}else if (commandeUser.haut == true) {
			this.personnage.seDeplacer('N');
		}
	}

	@Override
	/**
	 * le jeu ne s'arrête jamais. Return false
	 */
	public boolean etreFini() {
		return false;
	}

	public Aventurier getPerso() {
		return personnage;
	}

}
