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
	private JeuEvolution jeuEvolution;

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
	 * @param jE
	 *            jeu a lancer
	 * @param pAffiche
	 *            afficheur a utiliser
	 */
	public MoteurGraphique(JeuEvolution jE, DessinJeu pAffiche) {
		// creation du jeu
		this.jeuEvolution = jE;
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
		this.gui.menu.ajouterJeu(jeuEvolution);

		//En attente de la sortie du menu
		while(true) {
			// boucle de jeu

			while (!this.jeuEvolution.etreFini() && !this.gui.menu.isVisible()) {
				// demande controle utilisateur
				Commande c = controle.getCommande();
				// fait evoluer le jeu

				jeuEvolution.evoluer(c);
				// affiche le jeu
				this.gui.dessiner();
				// met en attente
				Thread.sleep(10);

			}
			this.jeuEvolution.setFini(false);
			this.gui.menu.setVisible(true);
			Thread.sleep(100);
		}


	}

}
