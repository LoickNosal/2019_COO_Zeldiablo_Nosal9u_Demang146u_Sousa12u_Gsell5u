package jeu;

/**
 * Classe qui modélise un monstre qui bouge aléatoirement
 * @author Alexandre Sousa
 *
 */
public class MonstreAleatoire extends Monstre {

	/** 
	 * direction choisie aleatoirement 
	 */
	protected char deplacement;
	/** 
	 * timer pour changer de direction
	 */
	protected int timer;

	/**
	 * constructeur du monstre aleatoire
	 * @param pPv 
	 * @param px
	 * @param py
	 * @param pDegat
	 * @param pPortee
	 * @param pNom
	 */
	public MonstreAleatoire(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pDegat, pPortee, pNom);
		id = 2;
		deplacement = randomDir();
		timer = (int) (Math.random() * 100);
	}


	/**
	 * fait bouger le monstre dans sa direction aleatoire,
	 * change de direction si il touche un osbtacle ou si le timer est fini
	 */
	@Override
	public void comportement() {
		timer--;
    	boolean bouger = seDeplacer(deplacement);
    	if (!bouger || timer <= 0) {
			deplacement = randomDir();
			timer = (int) (Math.random() * 100);
		}
		attaquer();
	}

	/**
	 * choisi une direction aleatoirement
	 * @return un point cardinal
	 */
	protected char randomDir() {
		switch ((int) (Math.random() * 4)) {
			case 0:
				return 'N';
			case 1:
				return 'E';
			case 2:
				return 'S';
			case 3:
			default:
				return 'O';
		}
	}

}
