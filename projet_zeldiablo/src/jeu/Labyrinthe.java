package jeu;

import java.util.ArrayList;

/**
 * @author Louis Demange
 * Modelise le labyrinthe
 */
public class Labyrinthe {

    private final int TAILLE_MIN = 5;
    private final int TAILLE_MAX = 15;
    private Case[][] cases;
    private Aventurier aventurier;
    private ArrayList<MonstreImmobile> mi;

    /**
     * Creation d'un Labyrinthe par defaut
     */
    public Labyrinthe() {
        defaut();
        mi = new ArrayList<MonstreImmobile>();
    }

    /**
     * Creation d'un Labyrinthe custom
     * @param modele chaine de caractere qui defini le modele du labyrinthes
     */
    public Labyrinthe(String modele) {
        String[] lignes = modele.trim().split("\n");

        boolean valide = valideModele(modele);
        if (!valide) {
            defaut();
            return;
        }

        int x = lignes[0].length();
        int y = lignes.length;
        cases = new Case[x][y];

        for (int j = 0; j < y; j++) {
            String ligne = lignes[j];
            for (int i = 0; i < x; i++) {
                switch (ligne.charAt(i)) {
                    case '-':
                        cases[i][j] = new CaseVide(i, j);
                        break;
                    case 'x':
                        cases[i][j] = new Mur(i, j);
                        break;
                }
            }
        }
        mi = new ArrayList<MonstreImmobile>();
    }

    /**
     * indique si une case est traversable ou non
     * @param x abscisse de la case
     * @param y ordonnée de la case
     * @return
     */
    public boolean caseTraversable(int x, int y) {
        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur())
            return false;
        return cases[x][y].peutTraverser();
    }
    
    /**
     * indique si les position posX,posY est sur un obstacle du labyrinthe
     * @param posX position x a tester
     * @param posY position y a tester 
     * @return true si est sur un obstacle
     */
   public boolean estSurUnObstacle(int posX,int posY) {
	   boolean res = false;
	   for (int i = 0; i < this.getHauteur(); i++) {
		for (int j = 0; j < this.getLargeur(); j++) {
			if (cases[i][j].estDedans(posX, posY) && cases[i][j].peutTraverser() == false) {
				res = true;
				return res;
			}
		}
	}
	   return res;
   }

    public String toString() {
        String res = "";
        for (int j = 0; j < cases[0].length; j++) {
            for (int i = 0; i < cases.length; i++) {
                res += cases[i][j].toString();
            }
            res += "\n";
        }
        return res;
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
    public int getHauteur() {
        return cases[0].length;
    }
    
    public ArrayList<MonstreImmobile> getMi() {
		return mi;
	}

    /**
     * valide le modele
     * @param modele chaine de caractere qui defini le modele du labyrinthes
     * @return vrai si le modele est valide, faux sinon
     */
    private boolean valideModele(String modele) {
        if (modele == null)
            return false;

        String[] lignes = modele.trim().split("\n");

        int y = lignes.length;
        int x = lignes[0].length();
        for (int i = 1; i < y; i++) {
            if (lignes[i].length() != x)
                return false;
        }

        if (x < TAILLE_MIN || x > TAILLE_MAX || y < TAILLE_MIN || y > TAILLE_MAX)
            return false;

        return true;
    }

    private void defaut() {
        cases = new Case[10][10];
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    cases[i][j] = new Mur(i, j);
                } else {
                    cases[i][j] = new CaseVide(i, j);
                }
            }
        }
    }

}
