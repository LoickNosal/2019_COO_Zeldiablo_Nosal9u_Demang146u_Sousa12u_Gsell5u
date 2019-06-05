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
	private JeuEvolution jeuEvolution;

	private Image mur;
	private Image porte;
	private Image casevide;
	private Image casePiege;

	private Image[] perso_droite;
	private Image[] perso_gauche;
	private Image[] slime_vert;
	private Image[] slime_rouge;
	private Image[] slime_violet;

	public static int TAILLE_CASE = Case.TAILLE;

	public DessinPerso(JeuEvolution jP) {
		this.jeuEvolution = jP;
		this.compteur = 0;
		this.perso_droite = new Image[4];
		this.perso_gauche = new Image[4];
		this.slime_rouge = new Image[2];
		this.slime_vert = new Image[2];
		this.slime_violet = new Image[2];


		try{
				
			//slimes
			slime_rouge[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("sa1.gif").getPath(), "UTF-8")));
			slime_rouge[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("sa2.gif").getPath(), "UTF-8")));
			slime_vert[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("s1.gif").getPath(), "UTF-8")));
			slime_vert[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("s2.gif").getPath(), "UTF-8")));
			slime_violet[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("sf1.gif").getPath(), "UTF-8")));
			slime_violet[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("sf2.gif").getPath(), "UTF-8")));
			
			//personnage
			perso_droite[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d1.gif").getPath(), "UTF-8")));
			perso_droite[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d2.gif").getPath(), "UTF-8")));
			perso_droite[2] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d3.gif").getPath(), "UTF-8")));
			perso_droite[3] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/d4.gif").getPath(), "UTF-8")));
			perso_gauche[0] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g1.gif").getPath(), "UTF-8")));
			perso_gauche[1] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g2.gif").getPath(), "UTF-8")));
			perso_gauche[2] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g3.gif").getPath(), "UTF-8")));
			perso_gauche[3] = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("spriteperso/g4.gif").getPath(), "UTF-8")));
			
			//cases
			mur = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("wall.png").getPath(), "UTF-8")));
			porte = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("porte.png").getPath(), "UTF-8")));
			casevide = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("sol.png").getPath(), "UTF-8")));
			casePiege = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("piege.png").getPath(), "UTF-8")));
		
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

		for(int i = 0; i < jeuEvolution.getAventurier().getLab().getHauteur(); i++){
			for(int j = 0; j< jeuEvolution.getAventurier().getLab().getLargeur(); j++){
				switch(jeuEvolution.getAventurier().getLab().typeCase(i, j)) {
				case 0 :
					g.drawImage(casevide, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					break;
				case 1:
					g.drawImage(mur, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					break;
				case 2:
					g.drawImage(porte, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					break;
				case 3:
					if (jeuEvolution.getAventurier().getLab().testerPiege(i, j)) {
						g.drawImage(casePiege, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					}else {
						g.drawImage(casevide, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					}
					break;
				default:
					g.drawImage(casevide, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					break;
				}
			}
		}

		if(compteur > 59)
			compteur =0;
		for (Monstre m: jeuEvolution.getMonstres()) {
			switch(m.getId())
			{
				case 1 :
					g.drawImage(slime_vert[compteur/30], m.getX()-36, m.getY()-50,73,55, null);
					break;
				case 2 :

					g.drawImage(slime_violet[compteur/30], m.getX()-36, m.getY()-50,73,55, null);
					break;
				case 3:

					g.drawImage(slime_rouge[compteur/30], m.getX()-36, m.getY()-50,73,55, null);
					break;
			}



		}


		if(jeuEvolution.getDirection()){
			g.drawImage(perso_droite[jeuEvolution.getCompteurPas()/5], jeuEvolution.getAventurier().getX()-20, jeuEvolution.getAventurier().getY()-50, 45,60,null);
		}
		else{
			g.drawImage(perso_gauche[jeuEvolution.getCompteurPas()/5], jeuEvolution.getAventurier().getX()-20, jeuEvolution.getAventurier().getY()-50, 45,60,null);
		}

		
		this.gestionVie(g);
		
		g.dispose();
	}
	
	
	
	public void gestionVie(Graphics2D g) {
		//deux barre de vie : une rouge et une verte pour les entites
		
		//barre des monstres
		for (Monstre m : jeuEvolution.getMonstres()) {
			int pvMax =  m.getPvMax();
			int pvCourant = m.getPv();
			//contour
			g.setColor(Color.black);
			g.fillRect(m.getX()-TAILLE_CASE/3-2, m.getY()-TAILLE_CASE-2, pvMax+4, 7+4);
			//barre de vie rouge
			g.setColor(Color.red);
			g.fillRect(m.getX()-TAILLE_CASE/3, m.getY()-TAILLE_CASE, pvMax, 7);
			//barre de vie verte
			g.setColor(Color.green);
			g.fillRect(m.getX()-TAILLE_CASE/3, m.getY()-TAILLE_CASE,pvCourant , 7);
		}
		//barre du joueur
		int pvMax = this.jeuEvolution.getAventurier().getPvMax();
		int pvCourant = this.jeuEvolution.getAventurier().getPv();
		//contour
		g.setColor(Color.black);
		g.fillRect(this.jeuEvolution.getAventurier().getX()-TAILLE_CASE/3-2, this.jeuEvolution.getAventurier().getY()-TAILLE_CASE-2, pvMax+4, 7+4);
		//barre de vie rouge
		g.setColor(Color.red);
		g.fillRect(this.jeuEvolution.getAventurier().getX()-TAILLE_CASE/3, this.jeuEvolution.getAventurier().getY()-TAILLE_CASE, pvMax, 7);
		//barre de vie verte
		g.setColor(Color.green);
		g.fillRect(this.jeuEvolution.getAventurier().getX()-TAILLE_CASE/3, this.jeuEvolution.getAventurier().getY()-TAILLE_CASE,pvCourant , 7);
		if (this.jeuEvolution.getAventurier().getVivant() == false) {
			g.setFont(new Font(null, Font.PLAIN, 100)); 
			g.setColor(Color.black);
			g.drawString("GAME OVER", 150, 350);
		}
		
	}



}
