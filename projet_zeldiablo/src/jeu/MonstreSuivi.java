package jeu;

/**
 * Classe qui modélise un monstre se dirigeant vers l'aventurier
 * @author Alexandre Sousa
 *
 */
public class MonstreSuivi extends Monstre {

	/** fait en sort que le monstre de deplace de case en case */
	protected int compteurPas = 0;
	/** caractere du deplacement en cours */
	protected char deplacementEnCours = ' ';

	/**
	 * Constructeur
	 * @param pPv pv du monstre
	 * @param px abscisse du monstre
	 * @param py ordonnée du mostre
	 * @param pDegat dégat du monstre
	 * @param pPortee portée du monstre
	 * @param pNom nom du monstre
	 */
	public MonstreSuivi(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		this.id = 3;
	}

	/**
	 * determine les déplacement du monstres la position du personnage
	 * le monstre peut se bloquer dans les murs et attaque l'aventurier en cible s'il
	 * est a portée
	 */
	@Override
	public void comportement() {

    	if(compteurPas == 0) {
    		Labyrinthe lab = this.getLab();
        	int pxc = cible.getX()/ Case.TAILLE;
        	int pyc = cible.getY()/ Case.TAILLE;
        	int px = x / Case.TAILLE;
        	int py = y / Case.TAILLE;
        	int distance = (int) distanceEntite(pxc, pyc, px, py);
        	int futurePosX = px;
        	int futurePosY = py;
        	int futureDistance = (int) distanceEntite(pxc, pyc, futurePosX, futurePosY);

        	futurePosX += 1;
        	futureDistance = (int) distanceEntite(pxc, pyc, futurePosX, futurePosY);
        	if(futureDistance >= distance) { // TEST EST
        		futurePosX -= 1;
        		futurePosY += 1;
        		futureDistance = (int) distanceEntite(pxc, pyc, futurePosX, futurePosY);
            	if(futureDistance >= distance) { // TEST SUD
            		futurePosY -= 1;
            		futurePosX -= 1;
            		futureDistance = (int) distanceEntite(pxc, pyc, futurePosX, futurePosY);
                	if(futureDistance >= distance ) { // TEST OUEST
                		futurePosX += 1;
                		futurePosY -= 1;
                		futureDistance = (int) distanceEntite(pxc, pyc, futurePosX, futurePosY);
                    	if(futureDistance >= distance) { // TEST NORD
                    		futurePosY += 1;
                    	}
                    	else if(lab.caseTraversable(futurePosX, futurePosY)) {
                    		deplacementEnCours = 'N';
                    	}
                	}
                	else if(lab.caseTraversable(futurePosX, futurePosY)) {
                		deplacementEnCours = 'O';
                	}
            	}
            	else if(lab.caseTraversable(futurePosX, futurePosY)) {
            		deplacementEnCours = 'S';
            	}
        	}
        	else if(lab.caseTraversable(futurePosX, futurePosY)) {
        			deplacementEnCours = 'E';
        	}
    	}

    	seDeplacer(deplacementEnCours);
    	compteurPas += 1;
    	if(compteurPas == Case.TAILLE/vitesse) {
    		compteurPas = 0;
    	}
    	attaquer();
	}

}
