package moteurJeu;

import java.util.ArrayList;

import jeu.Aventurier;
import jeu.JeuPrincipal;
import jeu.Labyrinthe;
import jeu.Monstre;

/**
 * represente le jeu en cours
 *
 * @author Loick
 * @author Louis Demange
 */
public class JeuEvolution implements Jeu {

	private int compteur_pas;
	private int compteur_attaque;

	private boolean direction;
	private boolean fini;

	/** aventurier */
	private Aventurier aventurier;
	/** labyrinthe du niveau en cours */
	private Labyrinthe labyrinthe;
	/** liste de monstres dans le labyrinthe en cours */
	private ArrayList<Monstre> monstres;

	/**
	 * construit un jeu avec un aventurier
	 * @param av aventurier en train de jouer
	 */
	public JeuEvolution(Aventurier av) {
		this.aventurier = av;
		this.compteurPas = 0;
		this.direction = true;
		this.fini = false;
	}

	@Override
	/**
	 * modifie la position (x,y) du personnage en fonction
	 * des valeurs des attributs gauche,droite,haut,bas
	 */
	public void evoluer(Commande commandeUser) {
		//PERSO
		boolean marche = false;

		if (commandeUser.bas) {
			this.aventurier.seDeplacer('S');
			marche = true;
		}
		if (commandeUser.droite) {
			this.aventurier.seDeplacer('E');
			marche = true;
			this.direction = true;
		}
		if (commandeUser.gauche) {
			this.aventurier.seDeplacer('O');
			marche = true;
			this.direction = false;
		}
		if (commandeUser.haut) {
			this.aventurier.seDeplacer('N');
			marche = true;
		}
		if(commandeUser.espace){
			compteur_attaque ++;
			if(compteur_attaque <2)
				for (Monstre m: monstres) {
					aventurier.attaquer(m);
				}
		}else{
			if(compteur_attaque >=2)
				compteur_attaque ++;
			if(compteur_attaque >20)
				compteur_attaque = 0;
		}

		if(marche == false) {
			this.compteurPas = 0;
		}
		else {
			this.compteurPas++;
			if(compteurPas>15)
				compteurPas = 0;
		}


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

		monstres.removeIf(n -> (n.getPv() <= 0));
	}



	@Override
	public boolean etreFini() {
		return this.fini;
	}

	public void setFini(boolean a) {
		this.fini = a;
	}

	public boolean getDirection() {
		return direction;
	}

	public int getCompteurPas() {
		return compteurPas;
	}


	public void changeNiveau(Labyrinthe l, ArrayList<Monstre> m) {
		setLabyrinthe(l);
		setMonstres(m);
		compteurPas = 0;
		direction = true;
		fini = false;
	}



	public Aventurier getAventurier() {
		return aventurier;
	}

	public Labyrinthe getLabyrinthe() {
		return labyrinthe;
	}

	public ArrayList<Monstre> getMonstres() {
		return monstres;
	}

	public void setAventurier(Aventurier av) {
		if (av != null)
			aventurier = av;
	}

	public void setLabyrinthe(Labyrinthe l) {
		if (l != null)
			labyrinthe = l;
	}

	public void setMonstres(ArrayList<Monstre> m) {
		if (m != null)
			monstres = m;
		else
			monstres = new ArrayList<Monstre>();
	}



}
