package jeu;

import java.util.ArrayList;
/**
 * modelise le jeu principal
 *
 */
public class JeuPrincipal {

    public Labyrinthe lab;
    public Aventurier av;

    public static final int level = 1;

    public JeuPrincipal() {
        this.lab = new Labyrinthe(
                "xxxxxxxxxx\n" +
                "x-x------x\n" +
                "x-xxxxxx-x\n" +
                "x-x------x\n" +
                "x-x--xx--x\n" +
                "x-x--xx--x\n" +
                "x-x------x\n" +
                "x-x------x\n" +
                "x--------x\n" +
                "xxxxxxxxxx\n");
        this.av = new Aventurier(10, 100,100, "Aventurier", this.lab);
    }

}
