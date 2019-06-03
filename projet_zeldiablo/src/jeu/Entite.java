package jeu;

abstract public class Entite{

    protected int pv;
    protected int x;
    protected int y;
    protected String nom;
    protected Labyrinthe lab;
    protected boolean vivant;

    /**
     * Le constructeur de la classe Entite
     * @param p Les Points de Vie
     * @param x La position X
     * @param y La position Y
     * @param n Son nom
     */
    public Entite(int p, int x, int y, String n, Labyrinthe l)
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
        if(l != null ) {
        	this.lab = l;
        }else {
        	this.lab = new Labyrinthe();
        }
        
        vivant = true;

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

    /**
     * permet de detecter si le joueur entre en collision avec un obstacle
     * @return true si le joueur peut traverser
     */
    public boolean peutAvancer(int posX, int posY) {
		boolean res = false;
			if (this.lab.estSurUnObstacle(posX,posY) == false) {
				res = true;
			}
		return res;

    }
    
    public void subirDegat(int pDegat) {
    	pv -= pDegat;
    	if(pv < 0) {
    		pv = 0;
    	}
    	mort();
    }
    
    public void mort() {
    	if(pv == 0) {
    		vivant = false;
    	}
    }
}
