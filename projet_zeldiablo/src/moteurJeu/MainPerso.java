package moteurJeu;

import java.util.Scanner;

import jeu.JeuPrincipal;

/**
 * Main du moteur graphique
 * @author Lo�ck
 *
 */
public class MainPerso {

	public static void main(String[] args) {

		// creation du jeu particulier et de son afficheur
		JeuPrincipal j = new JeuPrincipal();
		JeuPerso jeu = new JeuPerso(j.aventurier);
		DessinPerso dp = new DessinPerso(jeu, j.labyrinthe);

		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(jeu, dp);

		// lance la boucle de jeu qui tourne jusque la fin du jeu
		try {
			moteur.lancerJeu(800, 800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// lorsque le jeu est fini
		System.out.println("Fin du Jeu - appuyer sur entree");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		System.exit(1);

	}

}
