package moteurJeu;

import java.util.ArrayList;

import jeu.Aventurier;
import jeu.Labyrinthe;
import jeu.Monstre;

/**
 * represente le jeu Zeldiablo
 * @author Loick
 *
 */
public class JeuPerso implements Jeu{

	private int compteur_pas;
	private boolean direction;
	private boolean fini;
	/**
	 * personnage du jeu
	 */
	private Aventurier personnage;
	
	public JeuPerso(Aventurier av) {
		super();
		this.personnage = av;
		this.compteur_pas = 0;
		this.direction = true;
		this.fini = false;
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
			if(compteur_pas>15)
				compteur_pas = 0;
		}
		//affiche les pv du joueur
		//System.out.println(this.personnage.getPv());
		//si le joueur entre dans la porte
		if(this.personnage.getLab().typeCase(this.personnage.getX()/DessinPerso.TAILLE_CASE,this.personnage.getY()/DessinPerso.TAILLE_CASE) == 2) {
				this.fini = true;
		}else if(this.personnage.getLab().typeCase(this.personnage.getX()/DessinPerso.TAILLE_CASE,this.personnage.getY()/DessinPerso.TAILLE_CASE) == 3) {
				this.personnage.subirDegat(1);
		}
	}

	@Override
	/**
	 * le jeu ne s'arrete jamais. Return false
	 */
	public boolean etreFini() {
		return this.fini;
	}

	public void setFini(boolean a) {
		this.fini = a;
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
