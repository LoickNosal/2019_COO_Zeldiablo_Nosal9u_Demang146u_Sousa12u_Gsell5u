package jeu;

import java.util.ArrayList;

public class JeuPrincipal {

    public Labyrinthe lab;
    public Aventurier av;

    public static final int level = 1;

    public JeuPrincipal() {
        this.lab = new Labyrinthe();
        this.av = new Aventurier(10, 100,100, "Aventurier", this.lab);
    }


}
