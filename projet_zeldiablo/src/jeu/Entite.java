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
    	if (peutAvancer() == true) {
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
    public boolean peutAvancer() {
		boolean res = false;
		for (int i = 0; i < this.lab.getHauteur(); i++) {
			for (int j = 0; j < this.lab.getLargeur(); j++) {
				if (this.x >= this.lab.getCases()[i][j].getPosX() && this.x <= this.lab.getCases()[i][j].getPosX() + 80
						&& this.y >= this.lab.getCases()[i][j].getPosY() && this.y <= this.lab.getCases()[i][j].getPosX() + 80) {
					if (this.lab.getCases()[i][j].peutTraverser() == true) {
						res = true;
					}
				}
			}
		}
		return res;

    }
}
