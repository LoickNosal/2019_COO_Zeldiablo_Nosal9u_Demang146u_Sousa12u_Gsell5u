package moteurJeu;

import java.util.ArrayList;

import jeu.Aventurier;
import jeu.Labyrinthe;
import jeu.MonstreImmobile;

/**
 * represente le jeu Zeldiablo
 * @author Loick
 *
 */
public class JeuPerso implements Jeu{

	private int compteur_pas;
	private boolean direction;
	/**
	 * personnage du jeu
	 */
	private Aventurier personnage;
	
	public JeuPerso(Aventurier av) {
		super();
		this.personnage = av;
		this.compteur_pas = 0;
		this.direction = true;
	}

	@Override
	/**
	 * modifie la position (x,y) du personnage en fonction
	 * des valeurs des attributs gauche,droite,haut,bas
	 */
	public void evoluer(Commande commandeUser) {
		boolean marche = false;

		if (commandeUser.bas == true) {
			this.personnage.seDeplacer('S');
			marche = true;
		}
		if (commandeUser.droite == true) {
			this.personnage.seDeplacer('E');
			marche = true;
			this.direction = true;
		}
		if (commandeUser.gauche == true) {
			this.personnage.seDeplacer('O');
			marche = true;
			this.direction = false;
		}
		if (commandeUser.haut == true) {
			this.personnage.seDeplacer('N');
			marche = true;
		}

		if(marche == false) {
			this.compteur_pas = 0;
		}
		else {
			this.compteur_pas++;
			if(compteur_pas>30)
				compteur_pas = 0;
		}
	}

	@Override
	/**
	 * le jeu ne s'arr�te jamais. Return false
	 */
	public boolean etreFini() {
		return false;
	}

	public boolean isDirection() {
		return direction;
	}

	public int getCompteur_pas() {
		return compteur_pas;
	}

	public Aventurier getPerso() {
		return personnage;
	}

}
