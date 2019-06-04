package moteurJeu;

import jeu.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

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

			sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("sprite.png").getPath()));
			mur = ImageIO.read(new File(getClass().getClassLoader().getResource("wall.png").getPath()));
			casevide = ImageIO.read(new File(getClass().getClassLoader().getResource("sol.png").getPath()));
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
					g.drawImage(casevide, i*80, j*80, 80,80,null);
				}
				else{
					g.drawImage(mur, i*80, j*80, 80,80,null);
				}
			}
		}

		g.drawImage(perso_droite[0], jeuEnCours.getPerso().getX()-28, jeuEnCours.getPerso().getY()-40, 60,80,null);


		g.dispose();
	}
	
	

}
