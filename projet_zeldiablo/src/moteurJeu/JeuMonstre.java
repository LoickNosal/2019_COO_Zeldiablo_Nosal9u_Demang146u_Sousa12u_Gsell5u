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
	private Monstre monstre;
	
	/**
	 * personnage du jeu
	 */
	private Aventurier personnage;
	
	private int compteur_pas;
	private boolean direction;
	
	public JeuMonstre(Monstre m, Aventurier pPersonnage) {
		super();
		this.monstre = m;
		this.personnage = pPersonnage;
	}

	
	@Override
	/**
	 * Les monstres n'utilisent pas de commandes 
	 * envoyées par l'utilisateur
	 */
	public void evoluer(Commande commandeUser) {
		monstre.seDeplacer(personnage);
        monstre.attaquer(personnage);
	}

	@Override
	/**
	 * le jeu ne s'arr�te jamais. Return false
	 */
	public boolean etreFini() {
		return false;
	}

	public Monstre getMonstre() {
		return monstre;
	}
	
	public Aventurier getPersonnage() {
		return personnage;
	}

	

}