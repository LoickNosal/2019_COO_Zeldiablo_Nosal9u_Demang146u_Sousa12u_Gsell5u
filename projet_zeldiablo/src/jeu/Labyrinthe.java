package jeu;

/**
 * Modelise le labyrinthe
 * @author Louis Demange / Loick Nosal
 */
public class Labyrinthe {

    private final int TAILLE_MIN = 5;
    private final int TAILLE_MAX = 15;
    private Case[][] cases;
    private int[][] empreinte;


    /**
     * Creation d'un Labyrinthe par defaut
     */
    public Labyrinthe() {
        defautLabyrinthe();
    }


    /**
     * Creation d'un Labyrinthe custom
     *
     * @param modele tableau de chaine de caractere qui defini le modele du labyrinthes
     */
    public Labyrinthe(String[] modele) {
        boolean valide = valideModele(modele);
        if (!valide) {
            defautLabyrinthe();
            return;
        }

        int hauteur = modele.length;
        int largeur = modele[0].split(" ").length;
        cases = new Case[largeur][hauteur];

        for (int j = 0; j < hauteur; j++) {
            String[] ligne = modele[j].split(" ");
            for (int i = 0; i < largeur; i++) {
                switch (ligne[i]) {
                    case "-":
                        cases[i][j] = new CaseVide(i, j);
                        break;
                    case "X":
                        cases[i][j] = new Mur(i, j);
                        break;
                    case "p":
                    	cases[i][j] = new CaseSortie(i, j);
                        break;
                    case "t":
                    	cases[i][j] = new CasePiege(i,j);
                    	break;
                    case "m":
                    	cases[i][j] = new CaseMine(i,j);
                    	break;
                    default:
                        System.out.println("Caractere inconnue: '" + ligne[i] + "'");
                        cases[i][j] = new CaseVide(i, j);
                }
            }
        }
        genereEmpreinte();
    }


    /**
     * indique si une case est traversable ou non
     *
     * @param x abscisse de la case
     * @param y ordonnée de la case
     * @return vrai si la case est traversable
     */
    public boolean caseTraversable(int x, int y) {
        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur())
            return false;
        return cases[x][y].peutTraverser();
    }


    /**
     * indique si les positions x, y sont sur un obstacle du labyrinthe
     *
     * @param posX coordonnée x a tester en pixel
     * @param posY coordonnée y a tester en pixel
     * @return vrai si la case est sur un obstacle
     */
    public boolean estSurUnObstacle(int posX, int posY) {
        int x = posX / Case.TAILLE;
        int y = posY / Case.TAILLE;
        return !cases[x][y].peutTraverser();
    }

    /**
     * permet d'activer un piege, de dire qu'on est passe sur une case
     * @param x pos x a activer
     * @param y pos y a activer
     */
    public void activerPiege(int x, int y) {
    	if (x< 0) {
			x = 0;
		}
    	if (y < 0) {
			y = 0;
		}
    	cases[x][y].setEstPasse();
		
    }
    
    /**
     * permet de savoir si on est passe sur une case
     * (tester les pieges)
     * @param x a tester
     * @param y a tester
     * @return true si le piege est deja active
     */
    public boolean testerPiege(int x, int y) {
    	if (x< 0) {
			x = 0;
		}
    	if (y < 0) {
			y = 0;
		}

    	return cases[x][y].getEstPasse();

    }

    /**
     * renvoit le type de la case (vide,mur,piege,mine)
     * @param x pos x 
     * @param y pos y 
     * @return type de la case
     */
    public int typeCase(int x, int y) {
        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur())
            return -1;
        return cases[x][y].typeCase();
    }


    /**
     * information sur le labyrinthe
     */
    public String toString() {
        String res = "";
        for (int j = 0; j < getHauteur(); j++) {
            for (int i = 0; i < getLargeur(); i++) {
                res += cases[i][j].toString();
            }
            res += "\n";
        }
        return res;
    }


    /**
     * getter
     *
     * @return largeur
     */
    public int getLargeur() {
        return cases.length;
    }


    /**
     * getter
     *
     * @return hauteur
     */
    public int getHauteur() {
        return cases[0].length;
    }


    /**
     * valide le modele
     *
     * @param modele chaine de caractere qui defini le modele du labyrinthes
     * @return vrai si le modele est valide, faux sinon
     */
    private boolean valideModele(String[] modele) {
        if (modele == null)
            return false;

        int y = modele.length;
        int x = modele[0].split(" ").length;
        for (int i = 1; i < y; i++) {
            if (modele[i].split(" ").length != x)
                return false;
        }

        if (x < TAILLE_MIN || x > TAILLE_MAX || y < TAILLE_MIN || y > TAILLE_MAX)
            return false;

        return true;
    }


    /**
     * initialise le labyrinthe par defaut
     */
    private void defautLabyrinthe() {
        cases = new Case[15][15];
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 15; i++) {
                if (i == 0 || i == 14 || j == 0 || j == 14) {
                    cases[i][j] = new Mur(i, j);
                } else {
                    cases[i][j] = new CaseVide(i, j);
                }
            }
        }
    }


    /**
     * calcule une version simplifier du labyrinthe ou les cases sont numero pour l'IA
     * -1: case vide, -2: mur
     * Utiliser pour l'algorithme de Lee
     */
    private void genereEmpreinte() {
        empreinte = new int[getLargeur()][getHauteur()];
        for (int j = 0; j < getHauteur(); j++) {
            for (int i = 0; i < getLargeur(); i++) {
                if (cases[i][j].peutTraverser())
                    empreinte[i][j] = -1;
                else
                    empreinte[i][j] = -2;
            }
        }
    }


    /**
     * retourne un clone de l'empreinte du labyrinthe pour l'IA
     * @return clone de l'empreinte
     */
    public int[][] getEmpreinte() {
        int[][] clone = new int[getLargeur()][];
        for (int i = 0; i < clone.length; i++) {
            clone[i] = empreinte[i].clone();
        }
        return clone;
    }

}
