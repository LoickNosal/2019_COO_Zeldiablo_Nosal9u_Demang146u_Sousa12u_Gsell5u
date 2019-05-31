abstract public class Entite{

    private int pv;
    private int x;
    private int y;
    private String nom;

    public Entite(int p, int x, int y, String n)
    {
        this.pv = p;
        this.x = x;
        this.y = y;
        this.n = nom;
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

    public void seDeplacer(char cardinaux)
    {
        switch(cardinaux)
        {
            case 'N': this.y-- break;
            case 'E': this.x++ break;
            case 'S': this.y++ break;
            case 'O': this.x-- break;
        }
    }
}