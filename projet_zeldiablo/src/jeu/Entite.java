package jeu;

/**
 * Modelise une entite (personnage, monstre)
 *
 */
abstract public class Entite{

	/**
	 * point de vie de l'entite
	 */
    protected int pv;
    /**
     * position x de l'entite
     */
    protected int x;
    /**
     * position y de l'entite
     */
    protected int y;
    /**
     * nom de l'entite
     */
    protected String nom;
    /**
     * labyrinthe dans lequel est l'entite
     */
    protected Labyrinthe lab;
    /**
     * indique si l'entite est vivante
     */
    protected boolean vivant;

    /**
     * Le constructeur de la classe Entite
     * @param p Les Points de Vie
     * @param x La position X
     * @param y La position Y
     * @param n Son nom
     */
    public Entite(int p, int x, int y, String n)
    {
        if(p<= 0){
            this.pv = 1;
        }
        else{
            this.pv = p;
        }

        this.x = x;
        this.y = y;

        if(n == null || n.equals("")){
            this.nom = "Entite";
        }
        else{
            this.nom = n;
        }
        
        vivant = true;

    }

    public boolean getVivant() {
    	return vivant;
    }
    
    public void setLabyrinthe(Labyrinthe l) {
    	if (l != null) {
    		this.lab = l;
		}
    	
    }
    
    public Labyrinthe getLab() {
		return lab;
	}
    
    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Fonction seDeplacer : on met les char des points cardinaux pour choisir la position dans laquelle l'Entite va avancer
     * @param cardinaux Les points cardinaux
     */
    public abstract void seDeplacer(char cardinaux);
    
    public abstract void seDeplacer(Aventurier av);

    /**
     * permet de detecter si le joueur entre en collision avec un obstacle
     * @return true si le joueur peut traverser
     */
    public abstract boolean peutAvancer(int posX, int posY);
    
    /**
     * le joueur subit des degats
     * @param pDegat degat que l'entite prend
     */
    public void subirDegat(int pDegat) {
    	pv -= pDegat;
    	if(pv < 0) {
    		pv = 0;
    	}
    	mort();
    }
    
    /**
     * indique si le l'entite est mort
     */
    public void mort() {
    	if(pv == 0) {
    		vivant = false;
    	}
    }
}
