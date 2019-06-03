package jeu;

abstract public class Entite{

    protected int pv;
    protected int x;
    protected int y;
    protected String nom;
    protected Labyrinthe lab;

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
    public void seDeplacer(char cardinaux)
    {
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
                case 'N': this.y-=10; break;
                case 'E': this.x+=10; break;
                case 'S': this.y+=10; break;
                case 'O': this.x-=10; break;
            }
		}

    }

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
}
