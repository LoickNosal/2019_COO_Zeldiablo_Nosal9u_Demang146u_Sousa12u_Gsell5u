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
     * vitesse de l'entite
     */
    protected int vitesse;
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
     * pv max de l'entite
     */
    protected int pvMax;
    /**
     * Le mode invulnerable pour eviter de deceder directement
     */
    protected boolean invulnerable;
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
        this.pvMax = p;
        this.invulnerable = false;
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
    
    public int getVitesse() {
		return vitesse;
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
                case 'N': this.y-=vitesse; break;
                case 'E': this.x+=vitesse; break;
                case 'S': this.y+=vitesse; break;
                case 'O': this.x-=vitesse; break;
            }
		}
    }

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
        if(!invulnerable)
    	    pv -= pDegat;
    	if(pv < 0) {
    		pv = 0;
    	}
    	mort();
    }
    
    /**
     * pour heal le joueur
     * @param heal vie que l'entite prend
     */
    public void soigner(int heal) {
    	pv += heal;
    	if(this.pv > this.pvMax) {
    		this.pv = pvMax;
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
    
    public int getPvMax() {
    	return this.pvMax;
    }

    public double distanceEntite(Entite ent) {
        double d = Math.sqrt(Math.pow((this.x-ent.x),2)+Math.pow((this.y-ent.y),2));
        return d;

    }

    public double distanceItem(Item ent) {
        double d = Math.sqrt(Math.pow((this.x-ent.getPosX()),2)+Math.pow((this.y-ent.getPosY()),2));
        return d;

    }

    public void setInvulnerable(boolean a)
    {
        this.invulnerable = a;
    }
}
