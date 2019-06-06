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
	private int degat;
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
        this.degat = 10;
    }

	public void attaquer(Monstre m)
    {
        if(vivant && this.distanceEntite(m)<80) {
            m.subirDegat(this.degat);
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
   
    public int getDegat() {
    	return this.degat;
    }
    
    public void augmenterDegat() {
    	this.degat += 10;
    	if (this.degat > 30) {
			this.degat = 30;
		}
    }
    


    public void revivre()
    {
        this.pv=pvMax;
        this.vivant = true;
    }



    /**
     * Override pour contraindre le deplacement de l'aventurier plus loin des murs
     * @param cardinaux les points cardinaux
     * @return vrai si le deplacement a reussi
     */
    @Override
    public boolean seDeplacer(char cardinaux) {


        //System.out.println(x % Case.TAILLE + " " + y % Case.TAILLE);


        boolean bouger = false;
        switch(cardinaux) {
            case 'N':
                if (peutAvancer(x, (int) (y - vitesse - Case.TAILLE * 0.2))) {
                    y -= vitesse;
                    bouger = true;
                }
                break;
            case 'E':
                if (peutAvancer((int) (x + vitesse + Case.TAILLE * 0.1), y)) {
                    x += vitesse;
                    bouger = true;
                }
                break;
            case 'S':
                if (peutAvancer(x, y + vitesse + 5)) {
                    y += vitesse;
                    bouger = true;
                }
                break;
            case 'O':
                if (peutAvancer((int) (x - vitesse - Case.TAILLE * 0.1), y)) {
                    x -= vitesse;
                    bouger = true;
                }
                break;
        }
        return bouger;
    }

}