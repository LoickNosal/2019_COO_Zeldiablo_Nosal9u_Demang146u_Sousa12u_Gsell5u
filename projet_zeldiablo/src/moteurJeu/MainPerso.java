package moteurJeu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jeu.JeuPrincipal;
import javax.swing.*;
import java.awt.*;

/**
 * Main du moteur graphique
 * @author Lo�ck
 *
 */
public class MainPerso {
	

	
	public static void main(String[] args) {
		
		JFrame fenetre = new JFrame("ZelDiablo");
		fenetre.setSize(800,800);

		JPanel principal = new JPanel();
		principal.setPreferredSize(new Dimension(800,800));
		principal.setLayout(new BorderLayout());

		JPanel haut = new JPanel();
		JPanel milieu = new JPanel();
		JPanel bas = new JPanel();

		JButton jouer = new JButton("Jouer");
		JButton quitter = new JButton("Quitter");

		jouer.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// creation du jeu particulier et de son afficheur
			JeuPrincipal j = new JeuPrincipal();
			
			// lorsque le jeu est fini
			System.exit(1);
		}
		});
		
		
		quitter.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		System.exit(1);

		}
		});

		milieu.add(jouer);
		milieu.add(quitter);

		principal.add(haut,BorderLayout.NORTH);
		principal.add(milieu,BorderLayout.CENTER);
		principal.add(bas,BorderLayout.SOUTH);



		fenetre.setContentPane(principal);
		fenetre.pack();
		fenetre.setPreferredSize(new Dimension(800,800));
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		fenetre.requestFocusInWindow();
		fenetre.setVisible(true);


		


	}

}
