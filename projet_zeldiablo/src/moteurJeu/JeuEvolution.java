package moteurJeu;

import java.util.ArrayList;

import jeu.Aventurier;
import jeu.Item;
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

	/**
	 * Permet de gerer les sprites de l'aventurier
	 */
	private int compteurPas;
	/**
	 * Empeche le joueur de spammer l'attaque
	 */
	private int compteurAttaque;
	/**
	 * Empeche le joueur de mourir instantanement sur un monstre
	 */
	private int compteurInvulnerabilite;
	/**
	 * Permet de gerer le sprite de l'aventurier, le sens de sa marche
	 */
	private boolean direction;
	/**
	 * Permet de finir le jeu lorsqu'il passe à true
	 */
	private boolean fini;

	/** aventurier */
	private Aventurier aventurier;
	/** labyrinthe du niveau en cours */
	private Labyrinthe labyrinthe;
	/** liste de monstres dans le labyrinthe en cours */
	private ArrayList<Monstre> monstres;
	private ArrayList<Item> items;

	/**
	 * construit un jeu avec un aventurier
	 * @param av aventurier en train de jouer
	 */
	public JeuEvolution(Aventurier av) {
		this.aventurier = av;
		this.compteurPas = 0;
		this.compteurAttaque = 0;
		this.compteurInvulnerabilite = 0;
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
			compteurAttaque ++;
			if(compteurAttaque <2)
				for (Monstre m: monstres) {
					aventurier.attaquer(m);
				}
		}else{
			if(compteurAttaque >=2)
				compteurAttaque ++;
			if(compteurAttaque >20)
				compteurAttaque = 0;
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
			
		}else if(this.aventurier.getLab().typeCase(this.aventurier.getX()/DessinPerso.TAILLE_CASE,this.aventurier.getY()/DessinPerso.TAILLE_CASE) == 3) {
			
		int px = this.aventurier.getX();
		int py = this.aventurier.getY();

		//affiche les pv du joueur
		//System.out.println(this.personnage.getPv());
		//si le joueur entre dans la porte
		if(this.aventurier.getLab().typeCase(px/DessinPerso.TAILLE_CASE,py/DessinPerso.TAILLE_CASE) == 2) {
				this.fini = true;
				new JeuPrincipal();
		}else if(this.aventurier.getLab().typeCase(px/DessinPerso.TAILLE_CASE,py/DessinPerso.TAILLE_CASE) == 3) {
				this.aventurier.subirDegat(1);
				this.aventurier.getLab().activerPiege(px/DessinPerso.TAILLE_CASE, py/DessinPerso.TAILLE_CASE);

		}

		//ITEMS
		for (Item i : this.items) {
			if (i.peutRamasse(px, py)) {
				if (i.typeItem() == 0) { //Potion de vie
					if (this.aventurier.getPv() != this.aventurier.getPvMax()) { //si le perso n'a pas tout ses pv
						i.setRamasse();
						this.aventurier.subirDegat(-30); //heal le personnage de 30 pv
					}
				}
			}
		}

		// MONSTERS
		for(Monstre m : monstres) {
			m.comportement();
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
		if (l != null) {
			labyrinthe = l;
			aventurier.setLabyrinthe(l);
		}
	}

	public void setMonstres(ArrayList<Monstre> m) {
		if (m != null)
			monstres = m;
		else
			monstres = new ArrayList<Monstre>();
	}

	public void setItems(ArrayList<Item> it) {
		this.items = it;
	}
	public ArrayList<Item> gettItems() {
		return this.items;
	}



}
