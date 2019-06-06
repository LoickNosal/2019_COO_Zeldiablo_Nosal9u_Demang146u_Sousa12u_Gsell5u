package jeu;

/**
 * Classe qui modélise un monstre se dirigeant vers l'aventurier
 * @author Alexandre Sousa
 *
 */
public class MonstreSuivi extends Monstre{

	/**
	 * pour savoir quand le monstre se deplace d'une case entiere
	 */
	private int compteur_pas = 0;
	/**
	 * direction du monstre
	 */
	private char direction = 'd';

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

    	if(compteur_pas == 0) {
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
                    		direction = 'N';
                    	}
                	}
                	else if(lab.caseTraversable(futurePosX, futurePosY)) {
                		direction = 'O';
                	}
            	}
            	else if(lab.caseTraversable(futurePosX, futurePosY)) {
            		direction = 'S';
            	}
        	}
        	else if(lab.caseTraversable(futurePosX, futurePosY)) {
        			direction = 'E';
        	}
    	}

    	seDeplacer(direction);
    	compteur_pas += 1;
    	if(compteur_pas == Case.TAILLE/vitesse) {
    		compteur_pas = 0;
    	}
    	attaquer();
	}

}
