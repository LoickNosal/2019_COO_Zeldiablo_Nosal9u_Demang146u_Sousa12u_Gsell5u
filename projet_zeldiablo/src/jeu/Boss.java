package jeu;

import java.util.*;

/**
 * Classe qui modelise un monstre se dirigeant vers l'aventurier
 *
 * @author Louis Demange
 */
public class Boss extends MonstreSuivi {
	
	/** indique la position x et y  de la cible */
	private int cibleX, cibleY;
	/** liste de deplacement pour atteindre la cible */
	private Deque<Character> suiteDeDeplacement;
	/** direction pour le sprite, true=droite */
	private boolean direction;
    /** attaque pour le sprite, true=attaque en cours */
	private boolean attaque;

	public Boss(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		id = 4;
		cibleX = px / Case.TAILLE;
		cibleY = py / Case.TAILLE;
		suiteDeDeplacement = new ArrayDeque<>();
		deplacementEnCours = ' ';
		direction = false;
		compteurPas = 0;
		this.vitesse = 5;
		this.degat = 8;
	}

	public void comportement() {
        // realise le deplacement en cours
		if (compteurPas > 0) {
            seDeplacer(deplacementEnCours);
            compteurPas--;

        // sinon charge le prochain deplacement
		} else {
		    // recalcul le chemin si la cible a bouge
            boolean cibleBouger = calculPosCible();
            if (cibleBouger)
                calculerChemin();

            if (suiteDeDeplacement.size() > 0) {
                deplacementEnCours = suiteDeDeplacement.pollFirst();
                compteurPas = Case.TAILLE / vitesse;
                // pour le sprite
                if (deplacementEnCours == 'E')
                    direction = true;
                else if (deplacementEnCours == 'O')
                    direction = false;
            }
        }
		attaque = attaquer();
	}


    /**
     * calcul la position de la cible sur la grille et indique si elle a bougé
     * @return vrai si la cible a bougé
     */
    private boolean calculPosCible() {
	    boolean bouger = false;
        if (cible.getX() / Case.TAILLE != cibleX) {
            cibleX = cible.getX() / Case.TAILLE;
            bouger = true;
        }
        if (cible.getY() / Case.TAILLE != cibleY) {
            cibleY = cible.getY() / Case.TAILLE;
            bouger = true;
        }
        return bouger;
    }


    /**
     * calcul le chemin pour rejoindre la cible
     */
    private void calculerChemin() {
        suiteDeDeplacement.clear();
        int[][] empreinte = lab.getEmpreinte();
        lee(empreinte, x / Case.TAILLE, y / Case.TAILLE, cibleX, cibleY);
    }


    /**
     * calcul du plus cour chemin avec l'algorithme de Lee
     * @param laby empreinte du labyrinthe
     * @param startX position x de depart
     * @param startY position y de depart
     * @param cibleX position x d'arrivée
     * @param cibleY position y d'arrivée
     */
    private void lee(int[][] laby, int startX, int startY, int cibleX, int cibleY) {

        //System.out.println("Start: " + startX + " " + startY);
        //System.out.println("Cible: " + cibleX + " " + cibleY);

        laby[startX][startY] = 0; // initialise la premiere distance a 0

        //int[] dx = {0, 1, 0, -1}; // these arrays will help you travel in the 4 directions more easily
        //int[] dy = {-1, 0, 1, 0};
        ArrayList<Integer[]> dir = new ArrayList<>(); // liste de deplacements possibles
        dir.add(new Integer[] {0,-1});
        dir.add(new Integer[] {1, 0});
        dir.add(new Integer[] {0, 1});
        dir.add(new Integer[] {-1,0});


        Deque<Integer> X = new ArrayDeque<>(); // liste de position a explorer
        Deque<Integer> Y = new ArrayDeque<>();
        X.add(startX); // initialise avec la position de depart
        Y.add(startY);

        boolean trouver = false;
        int x, y, vx, vy;
        while(!trouver && !X.isEmpty()) { // stop si il n'y a plus de position a tester ou si la cible a ete atteinte
            // initialise la position
            x = X.getFirst();
            y = Y.getFirst();
            // melange l'ordre d'exploration des deplacements
            Collections.shuffle(dir);
            for(int i = 0; i < 4; i++) {
                // initialise la position voisine
                vx = x + dir.get(i)[0];
                vy = y + dir.get(i)[1];

                if(laby[vx][vy] == -1) { // test si la position est valide
                    X.add(vx); // ajoute la position a la liste de positions a explorer
                    Y.add(vy);
                    // marque la distance sur la case
                    laby[vx][vy] = laby[x][y] + 1;
                    if (vx == cibleX && vy == cibleY) { // si la cible est atteite
                        trouver = true;
                        break;
                    }
                }
            }
            X.removeFirst(); // supprime la position qui vient d'etre testé
            Y.removeFirst();
        }
        //printEmpreinte(laby);
        //System.out.println("Lee distance: " + laby[cibleX][cibleY] + " " + trouver);
        if (trouver) retraceChemin(laby, cibleX, cibleY);
    }


    /**
     * retrace le plus cours chemin a partir des chemin explorer par l'algorithme de Lee
     * @param laby empreinte avec distance calculé
     * @param startX x de depart
     * @param startY y de depart
     */
    private void retraceChemin(int[][] laby, int startX, int startY) {
        int d = laby[startX][startY];
        int x = startX;
        int y = startY;
        while (laby[x][y] > 0) {
            d--;
            if (laby[x][y - 1] == d) {
                suiteDeDeplacement.addFirst('S');
                y--;
            } else if (laby[x + 1][y] == d) {
                suiteDeDeplacement.addFirst('O');
                x++;
            } else if (laby[x][y + 1] == d) {
                suiteDeDeplacement.addFirst('N');
                y++;
            } else if (laby[x - 1][y] == d) {
                suiteDeDeplacement.addFirst('E');
                x--;
            } else {
                System.out.print("Probleme");
            }
        }

//        for (char c: suiteDeDeplacement) {
//            System.out.print(c + " ");
//        }

    }


    protected void printEmpreinte(int[][] laby) {
        System.out.println();
        for (int j = 0; j < laby[0].length; j++) {
            for (int i = 0; i < laby.length; i++) {
                int c = laby[i][j];
                if (c == -2)
                    System.out.print("  X");
                else if (c == -1)
                    System.out.print("  -");
                else if (c < 10)
                    System.out.print("  " + c);
                else
                    System.out.print(" " + c);
            }
            System.out.println();
        }
    }


    public boolean getDirection() {
        return direction;
    }

    public boolean getAttaque() {
        return attaque;
    }
}
