package jeu;

/**
 * Classe qui modélise un monstre qui bouge aléatoirement
 * @author Alexandre Sousa
 *
 */
public class MonstreAleatoire extends Monstre{

	public MonstreAleatoire(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		id = 2;
	}


	@Override
	public void comportement(Aventurier pPerso) {
		int aleatoire = (int)(Math.random() * ((3) + 1));
    	char deplacement = 'd';
    	
    	switch(aleatoire) {
    	case 0:
    		deplacement = 'N';
    		break;
    	case 1:
    		deplacement = 'E';
    		break;
    	case 2:
    		deplacement = 'S';
    		break;
    	case 3:
    		deplacement = 'O';
    		break;
    	}
    	
    	seDeplacer(deplacement);
		
	}

}
