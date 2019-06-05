package jeu;

import java.awt.Point;
import java.util.ArrayList;

import moteurJeu.DessinPerso;


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
    public Aventurier(int p, int x, int y, String n)
    {
        super(p,x,y,n);
        this.vitesse = 5;
    }
    
	
	
	
	

	public void attaquer(Monstre m)
    {
        if(vivant && this.distanceEntite(m)<70) {
            m.subirDegat(10);
        }
    }
}