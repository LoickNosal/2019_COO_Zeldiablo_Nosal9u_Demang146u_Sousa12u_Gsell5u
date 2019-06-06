package jeu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Classe qui modelise un monstre se dirigeant vers l'aventurier
 * @author Alexandre Sousa/Demange Louis
 *
 */
public class Boss extends Monstre {
	
	/**
	 * indique la position x et y  de la cible
	 */
	protected int cibleX, cibleY;
	/**
	 * 
	 */
	protected Deque<Character> suiteDeDeplacement;
	/**
	 * caractere du deplacement
	 */
	protected char deplacementEnCours;
	/**
	 * 
	 */
	protected int compteurPas, pasParCase;
	/** direction pour le sprite, true=droite */
	protected boolean direction;
    /** attaque pour le sprite, true=attaque en cours */
	protected boolean attaque;

	public Boss(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		id = 4;
		cibleX = px / Case.TAILLE;
		cibleY = py / Case.TAILLE;
		suiteDeDeplacement = new ArrayDeque<>();
		deplacementEnCours = ' ';
		direction = true;
		pasParCase = Case.TAILLE / vitesse;
		compteurPas = 0;
		this.vitesse = 6;
		this.degat = 8;
	}

	public void comportement() {


        // realise le deplacement en cours
		if (compteurPas > 0) {
            seDeplacer(deplacementEnCours);
            compteurPas--;
        // sinon prepare le prochain deplacement
		} else {

            //System.out.println("This: " + x % Case.TAILLE + " " + y % Case.TAILLE);

            boolean cibleBouger = calculPosCible();
            if (cibleBouger) {
                //System.out.println("Cible: " + cibleX + " " + cibleY);
                calculerChemin();
            }

            if (suiteDeDeplacement.size() > 0) {
                deplacementEnCours = suiteDeDeplacement.pollFirst();
                compteurPas = pasParCase;
                //System.out.println("#" + deplacementEnCours);
                if (deplacementEnCours == 'E')
                    direction = true;
                else if (deplacementEnCours == 'O')
                    direction = false;
            }

        }

		attaque = attaquer();
	}


    /**
     * cherche les deplacements possible depuis une certaine position dans le labyrinthe
     * @param posX coordonnée x a tester en pixel
     * @param posY coordonnée y a tester en pixel
     * @return une liste de deplacement possible
     */
	protected ArrayList<Character> deplacementPossible(int posX, int posY) {
	    ArrayList<Character> possible = new ArrayList<Character>();
        if (peutAvancer(posX, posY - Case.TAILLE)) {
            possible.add('N');
        }
        if (peutAvancer(posX + Case.TAILLE, posY)) {
            possible.add('E');
        }
        if (peutAvancer(posX, posY + Case.TAILLE)) {
            possible.add('S');
        }
        if (peutAvancer( posX - Case.TAILLE, posY)) {
            possible.add('O');
        }
        return possible;
    }


    /**
     * calcul la position de la cible sur la grille et dit si elle a bougé
     * @return vrai si la cible a bougé
     */
    protected boolean calculPosCible() {
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
     * calcul le chemin pour rejoindre la cible,
     * @return liste de deplacement pour atteindre la cible
     */
    protected void calculerChemin() {
        //System.out.println("Cible a bouger, recalculer chemin");
        suiteDeDeplacement.clear();
        /*int i = x;
        int j = y;
        for (int n = 0; n < 8; n++) { // recalcul une suite de 8 deplacement possible depuis la position actuel
            char d = deplacementPossible(i, j).get((int) (Math.random() * deplacementPossible(i, j).size()));
            suiteDeDeplacement.add(d);
            int[] ij = getPosApres(i, j, d);
            i = ij[0];
            j = ij[1];
        }*/

        int[][] empreinte = lab.getEmpreinte();
        /*for (int j = 0; j < empreinte[0].length; j++) {
            for (int i = 0; i < empreinte.length; i++) {
                System.out.print(empreinte[i][j]);
            }
            System.out.println();
        }*/

        lee(empreinte, x / Case.TAILLE, y / Case.TAILLE, cibleX, cibleY);

    }


    /**
     * TODO implemntation de l'algorithme de Lee
     * @param laby empreinte du labyrinthe
     * @param startX position x de depart
     * @param startY position y de depart
     * @param cibleX position x d'arrivée
     * @param cibleY position y d'arrivée
     */
    private void lee(int[][] laby, int startX, int startY, int cibleX, int cibleY) {

        //System.out.println("Start: " + startX + " " + startY);
        //System.out.println("Cible: " + cibleX + " " + cibleY);

        laby[startX][startY] = 0;

        int[] dx = {0, 1, 0, -1}; // these arrays will help you travel in the 4 directions more easily
        int[] dy = {-1, 0, 1, 0};


        Deque<Integer> X = new ArrayDeque<>(); // the queues used to get the positions in the matrix
        Deque<Integer> Y = new ArrayDeque<>();
        X.add(startX); //initialize the queues with the start position
        Y.add(startY);

        boolean trouver = false;
        int distance = 0;
        int x, y, cx, cy;
        while(!trouver && !X.isEmpty()) // while there are still positions in the queue
        {
            x = X.getFirst(); // set the current position
            y = Y.getFirst();
            for(int i = 0; i < 4; i++)
            {
                cx = x + dx[i]; // travel in an adiacent cell from the current position
                cy = y + dy[i];
                if(laby[cx][cy] == -1) // test si la position est valide
                {
                    X.add(cx); // ajoute la position valide
                    Y.add(cy);
                    laby[cx][cy] = laby[x][y] + 1; // marque comme visité

                    if (cx == cibleX && cy == cibleY) { // si la cible est atteite
                        trouver = true;
                        distance = laby[cx][cy];
                        break;
                    }
                }

            }

            X.removeFirst(); // eliminate the first position, as you have no more use for it
            Y.removeFirst();

        }

        //printLaby(laby);
        //System.out.println("Lee distance: " + distance + " " + trouver);

        retraceChemin(laby, cibleX, cibleY);

    }


    protected void retraceChemin(int[][] laby, int startX, int startY) {
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

        for (char c: suiteDeDeplacement) {
            //System.out.print(c + " ");
        }

    }


    protected void printLaby(int[][] laby) {
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
            //System.out.println();
        }
    }


    /**
     * calcul la position apres un deplacement a partir des coordonnée dans le labyrinthe
     * @param posX position x de depart
     * @param posY position y de depart
     * @param deplacement point cardinal
     * @return la position apres se deplacement
     */
    protected int[] getPosApres(int posX, int posY, char deplacement) {
        int[] posApres = {posX, posY};
        switch (deplacement) {
            case 'N':
                posApres[1] = posY - Case.TAILLE;
                break;
            case 'E':
                posApres[0] = posX + Case.TAILLE;
                break;
            case 'S':
                posApres[1] = posY + Case.TAILLE;
                break;
            case 'O':
                posApres[0] = posX - Case.TAILLE;
                break;
        }
        return posApres;
    }

    public boolean getDirection() {
        return direction;
    }

    public boolean getAttaque() {
        return attaque;
    }
}
