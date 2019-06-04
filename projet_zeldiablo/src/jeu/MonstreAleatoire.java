package jeu;

public class MonstreAleatoire extends Monstre{

	public MonstreAleatoire(int pId, int pPv, int px, int py, int pDegat, int pPortee, String pNom, Labyrinthe pLab) {
		super(pId, pPv, px, py, pDegat, pPortee, pNom, pLab);
	}

	@Override
	public void seDeplacer(char cardinaux) {
		// vide
		
	}

	@Override
	public void seDeplacer(Aventurier av) {
		int futureposX = 0;
    	int futureposY = 0;
    	int aleatoire = (int)(Math.random() * ((3) + 1));
    	
    	switch(aleatoire)
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
    		switch(aleatoire)
            {
                case 0: this.y-=3; break;
                case 1: this.x+=3; break;
                case 2: this.y+=3; break;
                case 3: this.x-=3; break;
            }
		}
		
	}

}
