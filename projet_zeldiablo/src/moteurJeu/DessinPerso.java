package moteurJeu;

import java.awt.image.BufferedImage;

/**
 * Classe qui dessine le jeu selon la taille de la fenetre
 * @author Alexandre Sousa
 *
 */
public class DessinPerso implements DessinJeu{

	private JeuPerso jeuEnCours;
	
	public DessinPerso(JeuPerso pjeuEnCours) {
		jeuEnCours = pjeuEnCours;
	}
	
	@Override
	public void dessiner(BufferedImage image) {
		int x = jeuEnCours.getX();
		int y = jeuEnCours.getY();
		
	}
	
	

}
