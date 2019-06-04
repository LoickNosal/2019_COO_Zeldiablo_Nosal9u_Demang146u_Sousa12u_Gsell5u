package jeu;

/**
 * Classe qui modélise un monstre
 * @author Alexandre Sousa
 *
 */
public abstract class Monstre extends Entite {
	protected int id;
	protected int degat;
	protected int portee;
	private int pvMax;

	public Monstre(int pPv, int px, int py, int pDegat, int pPortee, String pNom) {
		super(pPv, px, py, pNom);

		if(pDegat < 0) {
			pDegat = 0;
		}

		if(pPortee < 0) {
			pPortee = 0;
		}
		degat = pDegat;
		portee = pPortee;
		this.pvMax = pPv;

	}
	
	public int getPvMax() {
    	return this.pvMax;
    }
	
	public boolean peutAvancer(int posX, int posY) {
		boolean res = false;
			if (this.lab.estSurUnObstacle(posX,posY) == false) {
				res = true;
			}
		return res;

	}


	public void attaquer(Aventurier pPerso) {
		Aventurier perso = pPerso;
		int distance = (int) Math.sqrt(Math.pow(pPerso.getX() - x, 2) + Math.pow(pPerso.getY() - y, 2));
		if(distance < 0) {
			distance *= -1;
		}
		if(distance <= portee) {
			perso.subirDegat(degat);
		}
	}

	public void avancer(int a) {
		int futureposX = 0;
    	int futureposY = 0;

		switch(a)
        {
            case 0:
            futureposY = this.y-10;
            futureposX = this.x;
            break;

            case 1:
            futureposX = this.x+10;
            futureposY = this.y;
            break;

            case 2:
            futureposY = this.y+10;
            futureposX = this.x;
            break;

            case 3:
            futureposX = this.x-10;
            futureposY = this.y;
            break;
        }


    	if (peutAvancer(futureposX,futureposY) == true) {
    		switch(a)
            {
                case 0: this.y-=3; break;
                case 1: this.x+=3; break;
                case 2: this.y+=3; break;
                case 3: this.x-=3; break;
            }
		}
	}

	@Override
	public abstract void seDeplacer(char cardinaux);
	@Override
	public abstract void seDeplacer(Aventurier av);

	public int getDegat() {
		return degat;
	}

	public int getPortee() {
		return portee;
	}

	public int getId() {
		return id;
	}


	/**
	 * creer un monstre a partir d'un ID
	 *
	 * @param id id du monstre a creer
	 * @return le monstre correspondant a l'ID
	 */
	public static Monstre creerMonstreParID(int id) {
		switch (id) {
			case 1:
				//return new MonstreImmobile();
			case 2:
				//return new MonstreAleatoire();
			case 3:
				//return new MonstreSuivi();
			default:
				return null;
		}
	}

}
