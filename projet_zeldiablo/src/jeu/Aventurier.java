package jeu;

import java.awt.Point;
import java.util.ArrayList;


/**
 *Modelis un Aventurier
 */
public class Aventurier extends Entite {
    /**
     * Le constructeur de la classe Aventurier, qui herite du constructeur de Entite
     * @param p Les points de vie
     * @param x La position X
     * @param y La position Y
     * @param n Son nom
     */
    public Aventurier(int p, int x, int y, String n,Labyrinthe l)
    {
        super(p,x,y,n,l);
    }
    
    @Override
    public void seDeplacer(char cardinaux) {
    	int futureposX = 0;
    	int futureposY = 0;
    	
    	switch(cardinaux)
        {
            case 'N': 
            futureposY = this.y-10; 
            futureposX = this.x;
            break;
            
            case 'E': 
            futureposX = this.x+10;
            futureposY = this.y;
            break;
            
            case 'S': 
            futureposY = this.y+10;
            futureposX = this.x;
            break;
            
            case 'O': 
            futureposX = this.x-10;
            futureposY = this.y;
            break;
        }
    	
    	
    	if (peutAvancer(futureposX,futureposY) == true) {
    		switch(cardinaux)
            {
                case 'N': this.y-=5; break;
                case 'E': this.x+=5; break;
                case 'S': this.y+=5; break;
                case 'O': this.x-=5; break;
            }
		}
	}

	@Override
	public void seDeplacer(Aventurier av) {
		// vide
		
	}
}