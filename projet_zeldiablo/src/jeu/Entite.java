package jeu;

abstract public class Entite{

    private int pv;
    private int x;
    private int y;
    private String nom;

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
        switch(cardinaux)
        {
            case 'N': this.y-=2; break;
            case 'E': this.x+=2; break;
            case 'S': this.y+=2; break;
            case 'O': this.x-=2; break;
        }
    }
}