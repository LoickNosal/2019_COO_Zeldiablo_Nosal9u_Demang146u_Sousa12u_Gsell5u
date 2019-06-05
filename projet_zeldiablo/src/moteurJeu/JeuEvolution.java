package moteurJeu;

import java.util.ArrayList;

import jeu.Aventurier;
import jeu.Item;
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
	private Aventurier aventurier;
	private Labyrinthe labyrinthe;
	private ArrayList<Monstre> monstres;
	private ArrayList<Item> items;


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
		if(commandeUser.espace == true){
			for (Monstre m: monstres) {
				if(getAventurier().distance(m)<50)
					m.subirDegat(5);
			}
		}

		if(marche == false) {
			this.compteur_pas = 0;
		}
		else {
			this.compteur_pas++;
			if(compteur_pas>15)
				compteur_pas = 0;
		}
		
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
			m.seDeplacer(aventurier);
			m.attaquer(aventurier);
		}

		monstres.removeIf(n -> (n.getPv() <= 0));
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
		aventurier = av;
	}

	public void setLabyrinthe(Labyrinthe l) {
		labyrinthe = l;
	}

	public void setMonstres(ArrayList<Monstre> m) {
		monstres = m;
	}
	
	public void setItems(ArrayList<Item> it) {
		this.items = it;
	}
	public ArrayList<Item> gettItems() {
		return this.items;
	}



}
