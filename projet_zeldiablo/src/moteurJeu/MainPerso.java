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
 *
 * @author Lo�ck
 */
public class MainPerso {

    static Thread t;

    public static void main(String[] args) {

        JFrame fenetre = new JFrame("ZelDiablo");
        fenetre.setSize(400, 300);

        JPanel principal = new JPanel();
        principal.setPreferredSize(new Dimension(300, 200));
        principal.setLayout(new BorderLayout());

        JPanel haut = new JPanel();
        JPanel milieu = new JPanel();
        JPanel bas = new JPanel();

        JButton jouer = new JButton("Jouer");
        JButton quitter = new JButton("Quitter");

        jouer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                t = new Thread(){
                    public void run(){
                        JeuPrincipal j = new JeuPrincipal();
                    }
                };
                t.start();


            }
        });


        quitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        milieu.add(jouer);
        milieu.add(quitter);

        principal.add(haut, BorderLayout.NORTH);
        principal.add(milieu, BorderLayout.CENTER);
        principal.add(bas, BorderLayout.SOUTH);


        fenetre.setContentPane(principal);
        fenetre.pack();
        fenetre.setPreferredSize(new Dimension(800, 800));
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.requestFocusInWindow();
        fenetre.setVisible(true);


    }

}
