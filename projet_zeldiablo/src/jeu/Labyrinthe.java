package jeu;

/**
 * @author Louis Demange
 * Modélise le labyrinthe
 */
public class Labyrinthe {

    private static final int TAILLE_MIN = 5;
    private static final int TAILLE_MAX = 15;
    private Case[][] cases;

    /**
     * Creation d'un labyrinthe vide
     * @param x largeur
     * @param y hauteur
     */
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

    /**
     * getter
     * @return largeur
     */
    public int getLargeur() {
        return cases.length;
    }

    /**
     * getter
     * @return hauteur
     */
    public int getHeuteur() {
        return cases[0].length;
    }

}
