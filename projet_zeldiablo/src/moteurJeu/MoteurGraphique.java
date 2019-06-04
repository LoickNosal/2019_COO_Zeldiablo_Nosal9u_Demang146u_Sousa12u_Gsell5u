package moteurJeu;

import java.util.ArrayList;

/**
 * classe MoteurGraphique represente un moteur de jeu generique.
 * 
 * On lui passe un jeu et un afficheur et il permet d'executer un jeu.
 */
public class MoteurGraphique {

	/**
	 * le jeu a executer
	 */
	private ArrayList<Jeu> jeu;

	/**
	 * l'interface graphique
	 */
	private InterfaceGraphique gui;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private DessinJeu dessin;

	/**
	 * construit un moteur
	 * 
	 * @param pJeu
	 *            jeu a lancer
	 * @param pAffiche
	 *            afficheur a utiliser
	 */
	public MoteurGraphique(ArrayList<Jeu> pJeu, DessinJeu pAffiche) {
		// creation du jeu
		this.jeu = pJeu;
		this.dessin = pAffiche;
	}

	/**
	 * permet de lancer le jeu
	 */
	public void lancerJeu(int width, int height) throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new InterfaceGraphique(this.dessin,width,height);
		Controleur controle = this.gui.getControleur();
		//menu cherche si il est affiche ou pas


		//En attente de la sortie du menu
		while(true) {
			// boucle de jeu

			while (!this.jeu.get(0).etreFini() && !this.gui.menu.isVisible()) {
				// demande controle utilisateur
				Commande c = controle.getCommande();
				// fait evoluer le jeu
				for (Jeu jeuX : jeu) {
					jeuX.evoluer(c);
				}
				// affiche le jeu
				this.gui.dessiner();
				// met en attente
				Thread.sleep(10);
			}
			Thread.sleep(100);

			if(this.jeu.get(0).etreFini())
			{
				this.gui.menu.setVisible(true);
				this.jeu.get(0).setFini(false);
			}
		}


	}

}
