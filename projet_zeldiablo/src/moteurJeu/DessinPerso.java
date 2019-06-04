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

	private JeuPerso jeuEnCours;
	private Labyrinthe lab;

	private Image mur;
	private Image casevide;

	private Image[] perso_droite;
	private Image[] perso_gauche;
	
	public DessinPerso(JeuPerso pjeuEnCours, Labyrinthe l) {
		this.jeuEnCours = pjeuEnCours;
		this.lab = l;

		this.perso_droite = new Image[4];
		this.perso_gauche = new Image[4];

		try{
			

			perso_droite[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d1.gif").getPath(), "UTF-8")));
			perso_droite[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d2.gif").getPath(), "UTF-8")));
			perso_droite[2] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d3.gif").getPath(), "UTF-8")));
			perso_droite[3] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d4.gif").getPath(), "UTF-8")));
			perso_gauche[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g1.gif").getPath(), "UTF-8")));
			perso_gauche[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g2.gif").getPath(), "UTF-8")));
			perso_gauche[2] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g3.gif").getPath(), "UTF-8")));
			perso_gauche[3] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g4.gif").getPath(), "UTF-8")));
			mur = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("wall.png").getPath(), "UTF-8")));
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

		Graphics2D g = (Graphics2D) image.getGraphics();

		for(int i = 0; i < lab.getHauteur(); i++){
			for(int j = 0; j< lab.getLargeur(); j++){
				if(lab.caseTraversable(i,j)) {
					g.drawImage(casevide, i*60, j*60, 60,60,null);
				}
				else{
					g.drawImage(mur, i*60, j*60, 60,60,null);
				}
			}
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
