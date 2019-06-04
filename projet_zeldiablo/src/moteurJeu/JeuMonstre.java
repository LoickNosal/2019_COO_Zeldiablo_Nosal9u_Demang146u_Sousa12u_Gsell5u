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
	private Aventurier aventurier;


	public JeuMonstre(ArrayList<Monstre> m) {
		super();
		this.monstres = m;
	}

	
	@Override
	/**
	 * Les monstres n'utilisent pas de commandes 
	 * envoyées par l'utilisateur
	 */
	public void evoluer(Commande commandeUser) {
		for(Monstre m : monstres) {
			m.seDeplacer(null);
	        m.attaquer(aventurier);
		}
	}

	public void setAventurier(Aventurier av) {
		this.aventurier = av;
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


}