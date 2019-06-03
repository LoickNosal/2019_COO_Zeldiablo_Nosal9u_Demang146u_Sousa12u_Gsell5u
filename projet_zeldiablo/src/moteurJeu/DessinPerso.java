package moteurJeu;

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
	
	public DessinPerso(JeuPerso pjeuEnCours) {
		jeuEnCours = pjeuEnCours;
	}
	
	@Override
	public void dessiner(BufferedImage image) {

		Graphics2D g = (Graphics2D) image.getGraphics();

		Image sprite;
		try{
			sprite = ImageIO.read(new File("ressources/sprite.PNG"));
		}catch (Exception e){
			System.out.println("Probleme avec l'image");
			e.printStackTrace();
			sprite = null;
		}

		if(sprite != null ){
			g.drawImage(sprite, jeuEnCours.getPerso().getX(), jeuEnCours.getPerso().getY(), 61,78,null);
		}else{
			g.setColor(Color.red);
			g.fillRect(jeuEnCours.getPerso().getX(), jeuEnCours.getPerso().getY(), 61, 78);
		}



		g.dispose();
	}
	
	

}
