package jeu;

import java.awt.Point;
import java.util.ArrayList;

import moteurJeu.DessinPerso;


/**
 *Modelis un Aventurier
 */
public class Aventurier extends Entite {
	
	private boolean attaque;
	private boolean saut;
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
        this.attaque=false;
        this.saut=false;
    }

	public void attaquer(Monstre m)
    {
        if(vivant && this.distanceEntite(m)<80) {
            m.subirDegat(10);
        }
    }

    public boolean getAttaque() {
        return attaque;
    }

    public void setAttaque(boolean attaque) {
        this.attaque = attaque;
    }

    public boolean getSaut() {
        return saut;
    }

    public void setSaut(boolean saut) {
        this.saut = saut;
    }

    public void revivre()
    {
        this.pv=pvMax;
        this.vivant = true;
    }
}