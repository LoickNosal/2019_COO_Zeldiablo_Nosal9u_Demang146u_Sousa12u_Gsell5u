package moteurJeu;

import java.util.ArrayList;

import jeu.Aventurier;
import jeu.JeuPrincipal;
import jeu.Labyrinthe;
import jeu.Monstre;

/**
 * represente le jeu Zeldiablo
 * @author Loick
 *
 */
public class JeuEvolution implements Jeu {

	private int compteur_pas;
	private boolean direction;
	private boolean fini;
	/**
	 * personnage du jeu
	 */
	public Aventurier aventurier;
	public Labyrinthe labyrinthe;
	public ArrayList<Monstre> monstres;


	public JeuEvolution(Aventurier av) {
		this.aventurier = av;
		this.compteur_pas = 0;
		this.direction = true;
		this.fini = false;
		//this.jeu = j;
	}

	@Override
	/**
	 * modifie la position (x,y) du personnage en fonction
	 * des valeurs des attributs gauche,droite,haut,bas
	 */
	public void evoluer(Commande commandeUser) {
		//PERSO
		boolean marche = false;

		if (commandeUser.bas == true) {
			this.aventurier.seDeplacer('S');
			marche = true;
		}
		if (commandeUser.droite == true) {
			this.aventurier.seDeplacer('E');
			marche = true;
			this.direction = true;
		}
		if (commandeUser.gauche == true) {
			this.aventurier.seDeplacer('O');
			marche = true;
			this.direction = false;
		}
		if (commandeUser.haut == true) {
			this.aventurier.seDeplacer('N');
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
		if(this.aventurier.getLab().typeCase(this.aventurier.getX()/DessinPerso.TAILLE_CASE,this.aventurier.getY()/DessinPerso.TAILLE_CASE) == 2) {
				this.fini = true;
				new JeuPrincipal();
		}else if(this.aventurier.getLab().typeCase(this.aventurier.getX()/DessinPerso.TAILLE_CASE,this.aventurier.getY()/DessinPerso.TAILLE_CASE) == 3) {
				this.aventurier.subirDegat(1);
				this.aventurier.getLab().activerPiege(this.aventurier.getX()/DessinPerso.TAILLE_CASE, this.aventurier.getY()/DessinPerso.TAILLE_CASE);
				
		}

		// MONSTERS
		for(Monstre m : monstres) {
			m.seDeplacer(aventurier);
			m.attaquer(aventurier);
		}
	}

	public void setMonstres(ArrayList<Monstre> m) {
		monstres = m;
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
		return aventurier;
	}

	

}
