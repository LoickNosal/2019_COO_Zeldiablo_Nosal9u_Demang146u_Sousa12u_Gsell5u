package moteurJeu;

import java.util.ArrayList;

import jeu.Aventurier;
import jeu.Labyrinthe;
import jeu.MonstreImmobile;

/**
 * repr�sente le jeu Zeldiablo
 * @author Lo�ck
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
		ArrayList<MonstreImmobile> monstres;
		if (commandeUser.bas == true) {
			this.personnage.seDeplacer('S');
		}
		if (commandeUser.droite == true) {
			this.personnage.seDeplacer('E');
		}
		if (commandeUser.gauche == true) {
			this.personnage.seDeplacer('O');
		}
		if (commandeUser.haut == true) {
			this.personnage.seDeplacer('N');
		}
		for(MonstreImmobile monstre : monstres) {
			monstre.attaquer(this);
		}
	}

	@Override
	/**
	 * le jeu ne s'arr�te jamais. Return false
	 */
	public boolean etreFini() {
		return false;
	}

	public Aventurier getPerso() {
		return personnage;
	}

}
