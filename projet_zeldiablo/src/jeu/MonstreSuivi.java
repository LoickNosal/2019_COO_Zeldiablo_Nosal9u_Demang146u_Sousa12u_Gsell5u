package jeu;

/**
 * Classe qui modélise un monstre se dirigeant vers l'aventurier
 * @author Alexandre Sousa
 *
 */
public class MonstreSuivi extends Monstre{

	public MonstreSuivi(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
	}

	public void seDeplacer(char cardinaux) {
		// vide
	}

	public void seDeplacer(Aventurier av) {
    	int direction = 0;
    	int pX = av.getX();
    	int pY = av.getY();
    	int distance = (int) Math.sqrt(Math.pow(pX - x, 2) + Math.pow(pY - y, 2));
    	int futureDistance;
    	int futurePosX = x;
    	int futurePosY = y;
    	// 0: Nord, 1: Est, 2: Sud, 3: Ouest
    	
    	// Est
    	futurePosX += 3;
    	futureDistance = (int) Math.sqrt(Math.pow(pX - futurePosX, 2) + Math.pow(pY - futurePosY, 2));
    	if(futureDistance >= distance) {
    		futurePosX -= 3;
    		futurePosY += 3; // Sud
    	}
    	else {
    		direction = 1;
    	}
    	futureDistance = (int) Math.sqrt(Math.pow(pX - futurePosX, 2) + Math.pow(pY - futurePosY, 2));
    	if(futureDistance >= distance) {
    		futurePosY -= 3;
    		futurePosX -= 3; // Ouest
    	}
    	else {
    		direction = 2;
    	}
    	futureDistance = (int) Math.sqrt(Math.pow(pX - futurePosX, 2) + Math.pow(pY - futurePosY, 2));
    	if(futureDistance >= distance) {
    		futurePosX += 3;
    		futurePosY -= 3; // Nord
    	}
    	else {
    		direction = 3;
    	}
    	futureDistance = (int) Math.sqrt(Math.pow(pX - futurePosX, 2) + Math.pow(pY - futurePosY, 2));
    	if(futureDistance >= distance) {
    		futurePosY += 3;
    	}
    	else {
    		direction = 0;
    	}
    	avancer(direction);
	}
	
}
