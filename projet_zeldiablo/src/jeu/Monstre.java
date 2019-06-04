package jeu;

/**
 * Classe qui modélise un monstre 
 * @author Alexandre Sousa
 *
 */
public abstract class Monstre extends Entite{
	private int id; // 1: Monstre immobile, 2: Monstre aleatoire, 3: Monstre suivi 
	private int degat;
	private int portee;
	
	public Monstre(int pId, int pPv, int px, int py, int pDegat, int pPortee, String pNom, Labyrinthe pLab) {
		super(pPv, px, py, pNom, pLab);
		
		if(pDegat < 0) {
			pDegat = 0;
		}
		
		if(pPortee < 0) {
			pPortee = 0;
		}
		
		degat = pDegat;
		portee = pPortee;
		if(!lab.caseTraversable(px, py)) {
			id = -1;
			pv = -1;
			x = -1;
			y = -1;
			degat = -1;
			portee = -1;
			nom = null;
			lab = null;
		}
	}
	
	
	public void attaquer(Aventurier pPerso) {
		Aventurier perso = pPerso;
		int distance = (int) Math.sqrt(Math.pow(pPerso.getX() - x, 2) + Math.pow(pPerso.getY() - y, 2));
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

	public int getId() { return id; }

	
}
