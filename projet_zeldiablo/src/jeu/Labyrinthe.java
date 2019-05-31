package jeu;

import src.jeu.Case;

public class Labyrinthe {

    private static final int TAILLE_MIN = 5;
    private static final int TAILLE_MAX = 5;

    private Case[][] cases;

    public Labyrinthe(int x, int y) {
        if (x < TAILLE_MIN)
            x = TAILLE_MIN;
        if (y < TAILLE_MIN)
            y = TAILLE_MIN;

        if (x > TAILLE_MAX)
            x = TAILLE_MAX;
        if (y > TAILLE_MAX)
            y = TAILLE_MAX;

        cases = new Case[x][y];

        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                cases[i][j] = new CaseVide(i, j);
            }
        }
    }
}
