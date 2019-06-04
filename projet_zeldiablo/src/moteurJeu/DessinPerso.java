package moteurJeu;

import jeu.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLDecoder;

/**
 * Classe qui dessine le jeu selon la taille de la fenetre
 * @author Alexandre Sousa, Paul Gsell
 *
 */
public class DessinPerso implements DessinJeu{

	private int compteur;
	private JeuPerso jeuEnCours;
	private Labyrinthe lab;

	private Image mur;
	private Image porte;
	private Image casevide;

	private Image[] perso_droite;
	private Image[] perso_gauche;
	private Image[] slime_vert;
	private Image[] slime_rouge;
	
	public static int TAILLE_CASE = 60;
	
	public DessinPerso(JeuPerso pjeuEnCours, Labyrinthe l) {
		this.jeuEnCours = pjeuEnCours;
		this.lab = l;
		this.compteur = 0;
		this.perso_droite = new Image[4];
		this.perso_gauche = new Image[4];
		this.slime_rouge = new Image[2];
		this.slime_vert = new Image[2];


		try{
			
			slime_rouge[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("sa1.gif").getPath(), "UTF-8")));
			slime_rouge[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("sa2.gif").getPath(), "UTF-8")));
			slime_vert[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("s1.gif").getPath(), "UTF-8")));
			slime_vert[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("s2.gif").getPath(), "UTF-8")));
			perso_droite[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d1.gif").getPath(), "UTF-8")));
			perso_droite[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d2.gif").getPath(), "UTF-8")));
			perso_droite[2] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d3.gif").getPath(), "UTF-8")));
			perso_droite[3] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d4.gif").getPath(), "UTF-8")));
			perso_gauche[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g1.gif").getPath(), "UTF-8")));
			perso_gauche[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g2.gif").getPath(), "UTF-8")));
			perso_gauche[2] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g3.gif").getPath(), "UTF-8")));
			perso_gauche[3] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g4.gif").getPath(), "UTF-8")));
			mur = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("wall.png").getPath(), "UTF-8")));
			porte = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("porte.png").getPath(), "UTF-8")));
			casevide = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("sol.png").getPath(), "UTF-8")));
		}catch (Exception e){
			System.out.println("Probleme avec l'image");
			e.printStackTrace();
			mur = null;
			casevide = null;
		}
	}
	
	@Override
	public void dessiner(BufferedImage image) {
		compteur++;
		Graphics2D g = (Graphics2D) image.getGraphics();

		for(int i = 0; i < lab.getHauteur(); i++){
			for(int j = 0; j< lab.getLargeur(); j++){
				switch(lab.typeCase(i, j)) {
				case 0 :
					g.drawImage(casevide, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					break;
				case 1: 
					g.drawImage(mur, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					break;
				case 2:
					g.drawImage(porte, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					break;
				default:
					g.drawImage(casevide, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					break;
				}
			}
		}

		if(compteur > 10)
			compteur =0;
		for (Monstre m: lab.getMonstres()) {

				g.drawImage(slime_vert[compteur/10], m.getX(), m.getY(),50,50, null);


		}


		if(jeuEnCours.isDirection()){
			g.drawImage(perso_droite[jeuEnCours.getCompteur_pas()/10], jeuEnCours.getPerso().getX()-20, jeuEnCours.getPerso().getY()-30, 45,60,null);
		}
		else{
			g.drawImage(perso_gauche[jeuEnCours.getCompteur_pas()/10], jeuEnCours.getPerso().getX()-20, jeuEnCours.getPerso().getY()-30, 45,60,null);
		}




		g.dispose();
	}
	
	

}
