package moteurJeu;

import java.util.ArrayList;

import jeu.*;

/**
 * represente le jeu en cours
 *
 * @author Loick Nosal / Paul Gsell / Louis Demange
 */
public class JeuEvolution implements Jeu {

	/**
	 * Permet de gerer les sprites de l'aventurier
	 */
	private int compteurPas;
	/**
	 * Permet de mettre une fenetre de felicitation
	 */
	private int compteurWin;
	/**
	 * Empeche le joueur de spammer l'attaque
	 */
	private int compteurAttaque;
	/**
	 * Empeche le joueur de mourir instantanement sur un monstre
	 */
	private int compteurInvulnerabilite;
	/**
	 * Empeche le joueur de sauter tout le temps
	 */
	private int compteurSaut;
	/**
	 * Donne du temps pendant le game over
	 */
	private int compteurMort;
	/**
	 * Permet de gerer le sprite de l'aventurier, le sens de sa marche
	 */
	private boolean direction;
	/**
	 * Permet de finir le jeu lorsqu'il passe à true
	 */
	private boolean fini;
	/**
	 * A mettre sur true pour gagner la partie
	 */
	private boolean aGagner;

	/** aventurier */
	private Aventurier aventurier;
	/** labyrinthe du niveau en cours */
	private Labyrinthe labyrinthe;
	/** liste de monstres dans le labyrinthe en cours */
	private ArrayList<Monstre> monstres;
	/** liste d'items present dans le labyrinthe en cours */
	private ArrayList<Item> items;

	/** reference au JeuPrincipal pour recharger un niveau */
	private JeuPrincipal jeuPrincipal;

	/**
	 * construit un jeu avec un aventurier
	 * @param av aventurier en train de jouer
	 */
	public JeuEvolution(Aventurier av, JeuPrincipal jP) {
		this.jeuPrincipal = jP;
		this.aventurier = av;
		this.compteurPas = 0;
		this.compteurAttaque = 0;
		this.compteurInvulnerabilite = 0;
		this.compteurSaut = 0;
		this.direction = true;
		this.fini = false;

		this.items=new ArrayList<Item>();
	}

	@Override
	/**
	 * modifie la position (x,y) du personnage en fonction
	 * des valeurs des attributs gauche,droite,haut,bas
	 */
	public void evoluer(Commande commandeUser) {

		//PERSONNAGE MOUVEMENT
		boolean marche = false;
		int pvActuel = aventurier.getPv();

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

		//ATTAQUER
		if(commandeUser.attaque){
			compteurAttaque ++;
			aventurier.setAttaque(true);
			if(compteurAttaque <2)
				for (Monstre m: monstres) {
					aventurier.attaquer(m);
				}
		}else{
			if(compteurAttaque >=2)
				compteurAttaque ++;
			if(compteurAttaque >20) {
				compteurAttaque = 0;
				aventurier.setAttaque(false);
			}
		}

		//ECHAP
		if(commandeUser.echap)
		{
			aventurier.subirDegat(100);
			if(this.aventurier.getVivant() ==false){
				this.compteurMort++;
				if(compteurMort>300){
					setFini(true);
					this.compteurMort =0;
					aventurier.revivre();
				}
			}
		}

		//SAUT
		if(commandeUser.saut) {
			if(compteurSaut == 0)
				aventurier.setSaut(true);
		}
		if(aventurier.getSaut()==true)
			compteurSaut =1;
		if(compteurSaut>=1)
			compteurSaut++;
		if(compteurSaut>50)
			compteurSaut =0;

		//FRAMERATE DU SPRITE AVENTURIER
		if(marche == false) {
			this.compteurPas = 0;
		}
		else {
			this.compteurPas++;
			if(compteurPas>15)
				compteurPas = 0;
		}
			

		//CHANGEMENT DE NIVEAU
		//affiche les pv du joueur
		//System.out.println(this.personnage.getPv());
		//si le joueur entre dans la porte
		
		int px = this.aventurier.getX();
		int py = this.aventurier.getY();
		
		if(this.aventurier.getLab().typeCase(px/DessinPerso.TAILLE_CASE,py/DessinPerso.TAILLE_CASE) == 2 && this.monstres.isEmpty()) {
				jeuPrincipal.chargerLVLSuivant();
		}else if(this.aventurier.getLab().typeCase(px/DessinPerso.TAILLE_CASE,py/DessinPerso.TAILLE_CASE) == 3) {
				this.aventurier.subirDegat(10);
				this.aventurier.getLab().activerPiege(px/DessinPerso.TAILLE_CASE, py/DessinPerso.TAILLE_CASE);
		}else if(this.aventurier.getLab().typeCase(px/DessinPerso.TAILLE_CASE,py/DessinPerso.TAILLE_CASE) == 4) {
			if (this.aventurier.getLab().testerPiege(px/DessinPerso.TAILLE_CASE, py/DessinPerso.TAILLE_CASE) == false) {
				this.aventurier.subirDegat(25);
				this.aventurier.getLab().activerPiege(px/DessinPerso.TAILLE_CASE, py/DessinPerso.TAILLE_CASE);
			}
			
			
		}
		if(this.aventurier.getVivant() ==false){
			this.compteurMort++;
			if(compteurMort>300){
				setFini(true);
				this.compteurMort =0;
				aventurier.revivre();
			}

		}


		//ITEMS
		for (Item i : this.items) {
			if (i.peutRamasse(px/DessinPerso.TAILLE_CASE, py/DessinPerso.TAILLE_CASE)) {
				
				switch (i.typeItem()) {
				case 0://Potion de vie
					
					if (this.aventurier.getPv() != this.aventurier.getPvMax() && i.getRamasse() != true) { //si le perso n'a pas tout ses pv
						i.setRamasse();
						this.aventurier.soigner(30); //heal le personnage de 30 pv
					}
					break;
				case 1: //Amulette
					i.setRamasse();
					this.aGagner = true;
					aventurier.revivre();
					break;
				case 2: //Potion de Force
					 if (this.aventurier.getDegat() <= 30 && i.getRamasse() != true) { //si le perso n'a pas sa force max
						i.setRamasse();
						this.aventurier.augmenterDegat(); //augmente la force du personnage
					}
					break;
				default:
					break;
				}
			}
		}
		
		//supprimer les objets si ils sont ramasses
		items.removeIf(n -> (n.getRamasse() == true));

		// MONSTERS
		for(Monstre m : monstres) {
			m.comportement();
		}
		
		//supprime les monstres si ils sont morts
		monstres.removeIf(n -> (n.getPv() <= 0));
		

		//INVULNERABILITE
		if(pvActuel != aventurier.getPv() || aventurier.getSaut()){
			compteurInvulnerabilite +=2;
			aventurier.setInvulnerable(true);
		}
		if(compteurInvulnerabilite >= 1 && !aventurier.getSaut())
			compteurInvulnerabilite ++;
		if(compteurInvulnerabilite>50){
			compteurInvulnerabilite=0;
			aventurier.setInvulnerable(false);
			aventurier.setSaut(false);
		}

		//GAGNER
		if(this.aGagner)
			compteurWin++;
		if(compteurWin>300){
			compteurWin=0;
			this.aGagner=false;
			this.fini = true;
		}
	}

	@Override
	public boolean etreFini() {
		return this.fini;
	}

	public void setFini(boolean a) {
		this.fini = a;
	}
	
	public boolean aGagner() {
		return aGagner;
	}

	public boolean getDirection() {
		return direction;
	}

	public int getCompteurPas() {
		return compteurPas;
	}

	/**
	 * permet de changer de niveau et reset certains attributs
	 * @param l labyrinthe de l'etage
	 * @param m liste de monstre de l'etage
	 * @param it liste d'item de l'etage
	 */
	public void changeNiveau(Labyrinthe l, ArrayList<Monstre> m, ArrayList<Item> it) {
		setLabyrinthe(l);
		setMonstres(m);
		setItems(it);
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

	public ArrayList<Item> getItems() {
		return this.items;
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
		if (it != null)
			items = it;
		else
			items = new ArrayList<Item>();
	}


	/**
	 * recharge le premier niveau
	 */
	public void restart() {
		aventurier.resetDegat();
		jeuPrincipal.restart();
	}

}
