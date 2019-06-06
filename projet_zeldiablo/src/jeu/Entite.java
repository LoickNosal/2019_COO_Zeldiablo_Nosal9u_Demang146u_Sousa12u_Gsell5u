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
    

    
    /**
     * getter
     * @return retourne vrai si le hero est vivant
     */
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
     * permet de savoir si une entite peut se deplacer sur les ces coordonnées
     * check si l'aventurier avance pas sur une case vide et EST VIVANT
     * @param posX coordonnée x a tester en pixel
     * @param posY coordonnée y a tester en pixel
     * @return true si le joueur peut avancer
     */
    public boolean peutAvancer(int posX, int posY) {
		boolean res = false;
			if (this.lab.estSurUnObstacle(posX,posY) == false && vivant == true) {
				res = true;
			}
		return res;

	}
    
    /**
     * Fonction seDeplacer : on met les char des points cardinaux pour choisir la position dans laquelle l'Entite va avancer
     * selon sa vitesse
     * @param cardinaux Les points cardinaux
     * @return vrai si le replacement a reussi
     */
    public boolean seDeplacer(char cardinaux) {
    	int futureposX = 0;
    	int futureposY = 0;

    	switch(cardinaux)
        {
            case 'N': 
            futureposY = this.y-vitesse;
            futureposX = this.x;
            break;
            
            case 'E': 
            futureposX = this.x+vitesse;
            futureposY = this.y;
            break;
            
            case 'S': 
            futureposY = this.y+vitesse;
            futureposX = this.x;
            break;
            
            case 'O': 
            futureposX = this.x-vitesse;
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
            return true;
		}
    	return false;
    }
    
    /**
     * L'entitee subit des degats
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
     * indique si l'entite est morte
     */
    public void mort() {
    	if(pv == 0) {
    		vivant = false;
    	}
    }
    
    public int getPvMax() {
    	return this.pvMax;
    }

    /**
     * Calcule la distance en vol d'oiseau entre deux entités
     * @param ent Deuxieme entité pour calculer la distance
     * @return la distance
     */
    public double distanceEntite(Entite ent) {
        double d = Math.sqrt(Math.pow((this.x-ent.x),2)+Math.pow((this.y-ent.y),2));
        return d;
    }
    
    /**
     * Calcule la distance en vol d'oiseau entre deux points
     * @param px abscisse du premier point
     * @param py ordonnee du premier point
     * @param dx abscisse du deuxième point
     * @param dy ordonnee du deuxieme point
     * @return la distance
     */
    public double distanceEntite(int px, int py, int dx, int dy) {
		double d = Math.sqrt(Math.pow((px - dx),2)+Math.pow((py - dy),2));
        return d;
    }

    /**
     * Calcule la distance en vol d'oiseau entre deux items
     * @param ent Deuxieme item pour calculer la distance
     * @return
     */
    public double distanceItem(Item ent) {
        double d = Math.sqrt(Math.pow((this.x-ent.getPosX()),2)+Math.pow((this.y-ent.getPosY()),2));
        return d;

    }

    /**
     * Rend l'entitee invulnerable a la methode subirDegat
     * @param vrai si le joueur est invulnerable
     */
    public void setInvulnerable(boolean a)
    {
        this.invulnerable = a;
    }

    /**
     * Modifies la position de l'entité
     * @param x nouvelle abscisse
     * @param y nouvelle ordonnée
     */
    public void setPositon(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
