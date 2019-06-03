package jeu;

/**
 *Modelis un Aventurier
 */
public class Aventurier extends Entite {
	
	/**
     * labyrinthe dans lequel est l'aventurier
     */
    protected Labyrinthe lab;

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

}