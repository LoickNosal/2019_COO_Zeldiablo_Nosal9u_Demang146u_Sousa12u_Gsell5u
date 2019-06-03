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
	
	public DessinPerso(JeuPerso pjeuEnCours, Labyrinthe l) {
		this.jeuEnCours = pjeuEnCours;
		this.lab = l;
	}
	
	@Override
	public void dessiner(BufferedImage image) {

		Graphics2D g = (Graphics2D) image.getGraphics();

		Image sprite;
		Image mur;
		Image casevide;
		try{
			sprite = ImageIO.read(new File("ressources/sprite.PNG"));
			mur = ImageIO.read(new File("ressources/wall.PNG"));
			casevide = ImageIO.read(new File("ressources/sol.PNG"));
		}catch (Exception e){
			System.out.println("Probleme avec l'image");
			e.printStackTrace();
			sprite = null;
			mur = null;
			casevide = null;
		}

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

		g.drawImage(sprite, jeuEnCours.getPerso().getX()-46, jeuEnCours.getPerso().getY()-52, 61,78,null);



		g.dispose();
	}
	
	

}
