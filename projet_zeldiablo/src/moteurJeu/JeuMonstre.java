package moteurJeu;

import java.util.ArrayList;

import jeu.Monstre;
import jeu.Aventurier;
import jeu.Labyrinthe;

/**
 * Classe qui représente les actions des Monstres
 * @author Alexandre Sousa
 *
 */
public class JeuMonstre implements Jeu{
	
	/**
	 * monstre du jeu
	 */
	private ArrayList<Monstre> monstres;
	
	/**
	 * personnage du jeu
	 */
	private Aventurier personnage;
	
	private int compteur_pas;
	private boolean direction;
	
	public JeuMonstre(Aventurier pPersonnage) {
		super();
		this.personnage = pPersonnage;
		this.monstres = personnage.getLab().getMonstres();
	}

	
	@Override
	/**
	 * Les monstres n'utilisent pas de commandes 
	 * envoyées par l'utilisateur
	 */
	public void evoluer(Commande commandeUser) {
		for(Monstre m : monstres) {
			m.seDeplacer(personnage);
	        m.attaquer(personnage);
		}
	}

	@Override
	/**
	 * le jeu ne s'arr�te jamais. Return false
	 */
	public boolean etreFini() {
		return false;
	}

	public ArrayList<Monstre> getMonstres() {
		return monstres;
	}
	
	public Aventurier getPersonnage() {
		return personnage;
	}

	

}