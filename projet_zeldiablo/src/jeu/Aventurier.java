package jeu;

import java.awt.Point;
import java.util.ArrayList;

import moteurJeu.DessinPerso;


/**
 *Modelise un Aventurier
 */
public class Aventurier extends Entite {
	
	/**
	 * permet de savoir si l'aventurier attaque
	 */
	private boolean attaque;
	/**
	 * indique si le joueur saute
	 */
	private boolean saut;
	/**
	 * degat du joueur
	 */
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

    /**
     * methode qui permet d'attaquer un monstre
     * @param m monstre a attaquer
     */
	public void attaquer(Monstre m)
    {
        if(vivant && this.distanceEntite(m)<80) {
            m.subirDegat(this.degat);
        }
    }
	/**
	 * renvoit si le joueur est en train d'attaquer
	 * @return si le joueur attaque : true
	 */
    public boolean getAttaque() {
        return attaque;
    }
    
    /**
     * permet de definir si le joueur attaque
     * @param attaque defini si le joueur attaque
     */
    public void setAttaque(boolean attaque) {
        this.attaque = attaque;
    }
    
    /**
     * indique si le joueur saute
     * @return si le joueur saute : true
     */
    public boolean getSaut() {
        return saut;
    }

    /**
     * defini si le joueur saute
     * @param saut defini si le joueur saute
     */
    public void setSaut(boolean saut) {
        this.saut = saut;
    }
   
    /**
     * getter des degats
     * @return degats
     */
    public int getDegat() {
    	return this.degat;
    }
    
    /**
     * augmente les degats du joueur (potion de force)
     */
    public void augmenterDegat() {
    	this.degat += 5;
    	if (this.degat > 30) {
			this.degat = 30;
		}
    }

    public void resetDegat() {
        this.degat = 10;
    }
    

    /**
     * permet de faire revivre le joueur
     */
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