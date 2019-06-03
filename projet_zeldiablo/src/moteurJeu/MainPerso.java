package moteurJeu;

import java.util.Scanner;

/**
 * Main du moteur graphique
 * @author Loïck
 *
 */
public class MainPerso {
	
	public static void main(String[] args) {
		
		// creation du jeu particulier et de son afficheur
		JeuPerso jeu = new JeuPerso();
		DessinPerso dp = new DessinPerso(jeu);
		
		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(jeu, dp);
		
		// lance la boucle de jeu qui tourne jusque la fin du jeu
		try {
			moteur.lancerJeu(600, 600);
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
