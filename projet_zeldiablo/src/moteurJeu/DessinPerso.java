package moteurJeu;

import jeu.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Classe qui dessine le jeu selon la taille de la fenetre
 * @author Alexandre Sousa/Paul Gsell/Nosal Loick
 *
 */
public class DessinPerso implements DessinJeu{

	/**
	 * Permet l'affichage fluide de l'animation du personnage
	 */
	private int compteur;
	/**
	 * Permet l'affichage fluide de l'animation du boss
	 */
	private int compteurBoss;
	/**
	 * Permet au Boss d'avoir une animation d'attaque
	 */
	private boolean modeAttaqueBoss;
	/**
	 * Permet d'avoir une variable pour le saut du personnage, pour la distance du saut;
	 */
	private int saut;
	/**
	 * Rattachement à JeuEvolution pour faire evoluer les images avec le jeu
	 */
	private JeuEvolution jeuEvolution;
	/**
	 * Image du mur
	 */
	private Image mur;
	/**
	 * Image de la porte
	 */
	private Image porte;
	/**
	 * Image de la case vide
	 */
	private Image casevide;
	/**
	 * Image de la case piege
	 */
	private Image casePiege;
	/**
	 * Image du squelette su personnage
	 */
	private Image mort;
	/**
	 * Image de la potion de vie
	 */
	private Image potion;
	/**
	 * Image de la case mine
	 */
	private Image caseMine;
	/**
	 * Image de la case mine desactivée
	 */
	private Image caseMineDesac;
	/**
	 * Image de l'amulette
	 */
	private Image amulette;
	/**
	 * Image de la potion de force
	 */
	private Image potionForce;
	/**
	 * Image du message Bravo
	 */
	private Image bravo;

	/**
	 * Tableau des sprites du perso qui marche vers la droite
	 */
	private Image[] perso_droite;
	/**
	 * Tableau des sprites du perso qui marche vers la gacuhe
	 */
	private Image[] perso_gauche;
	/**
	 * Tableau des sprites du slime vert
	 */
	private Image[] slime_vert;
	/**
	 * Tableau des sprites du slime rouge
	 */
	private Image[] slime_rouge;
	/**
	 * Tableau des sprites du slime violet
	 */
	private Image[] slime_violet;
	/**
	 * Tableau des sprites de l'epee
	 */
	private Image[] epee;
	/**
	 * Tableau des sprites du reaper qui attaque à droite
	 */
	private Image[] grim_a_d;
	/**
	 * Tableau des sprites du reaper qui attaque à gauche
	 */
	private Image[] grim_a_g;
	/**
	 * Tableau des sprites du reaper qui bouge pas en direction de la droite
	 */
	private Image[] grim_i_d;
	/**
	 * Tableau des sprites du reaper qui bouge pas en direction de la gauche
	 */
	private Image[] grim_i_g;

	public static int TAILLE_CASE = Case.TAILLE;

	public DessinPerso(JeuEvolution jP) {
		this.jeuEvolution = jP;
		this.compteur = 0;
		this.compteurBoss = 0;
		this.perso_droite = new Image[4];
		this.perso_gauche = new Image[4];
		this.slime_rouge = new Image[2];
		this.slime_vert = new Image[2];
		this.slime_violet = new Image[2];
		this.grim_a_d = new Image[14];
		this.grim_a_g = new Image[14];
		this.grim_i_d = new Image[14];
		this.grim_i_g = new Image[14];
		this.epee = new Image[2];

		try{
				
			//slimes
            slime_rouge[0] = ImageIO.read(getClass().getResourceAsStream("/sa1.gif"));
			slime_rouge[1] = ImageIO.read(getClass().getResourceAsStream("/sa2.gif"));
			slime_vert[0] = ImageIO.read(getClass().getResourceAsStream("/s1.gif"));
			slime_vert[1] = ImageIO.read(getClass().getResourceAsStream("/s2.gif"));
			slime_violet[0] = ImageIO.read(getClass().getResourceAsStream("/sf1.gif"));
			slime_violet[1] = ImageIO.read(getClass().getResourceAsStream("/sf2.gif"));
			
			//personnage
			perso_droite[0] = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d1.gif"));
			perso_droite[1] = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d2.gif"));
			perso_droite[2] = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d3.gif"));
			perso_droite[3] = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d4.gif"));
			perso_gauche[0] = ImageIO.read(getClass().getResourceAsStream("/spriteperso/g1.gif"));
			perso_gauche[1] = ImageIO.read(getClass().getResourceAsStream("/spriteperso/g2.gif"));
			perso_gauche[2] = ImageIO.read(getClass().getResourceAsStream("/spriteperso/g3.gif"));
			perso_gauche[3] = ImageIO.read(getClass().getResourceAsStream("/spriteperso/g4.gif"));
			mort = ImageIO.read(getClass().getResourceAsStream("/spriteperso/mort.png"));
			//cases
			mur = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
			porte = ImageIO.read(getClass().getResourceAsStream("/porte.png"));
			casevide = ImageIO.read(getClass().getResourceAsStream("/sol.png"));
			casePiege = ImageIO.read(getClass().getResourceAsStream("/piege.png"));
			caseMine = ImageIO.read(getClass().getResourceAsStream("/mine.png"));
			caseMineDesac = ImageIO.read(getClass().getResourceAsStream("/mineDesac.png"));
			//epee
			epee[0] = ImageIO.read(getClass().getResourceAsStream("/epeed.png"));
			epee[1] = ImageIO.read(getClass().getResourceAsStream("/epeeg.png"));
			//potion
			potion = ImageIO.read(getClass().getResourceAsStream("/potionVie.png"));
			amulette = ImageIO.read(getClass().getResourceAsStream("/amulette.png"));
			//reaper
			initialiserReaper();
			//potionforce
			potionForce = ImageIO.read(getClass().getResourceAsStream("/potionForce.png"));
			//gagner
			bravo = ImageIO.read(getClass().getResourceAsStream("/bravo.gif"));
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
				case 4:
					if (jeuEvolution.getAventurier().getLab().testerPiege(i, j)) {
						g.drawImage(caseMineDesac, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
					}else {
						g.drawImage(caseMine, i*TAILLE_CASE, j*TAILLE_CASE, TAILLE_CASE,TAILLE_CASE,null);
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

		for (Item i:jeuEvolution.getItems()) {
			switch(i.getId()){
				case 0 : g.drawImage(potion, i.getPosX()* Case.TAILLE+5, i.getPosY()*Case.TAILLE+5, 50,50,null);
					break;
				case 1 : g.drawImage(amulette, i.getPosX()* Case.TAILLE+10, i.getPosY()*Case.TAILLE+10, 30,30,null);
					break;
				case 2 : g.drawImage(potionForce, i.getPosX()* Case.TAILLE+5, i.getPosY()*Case.TAILLE+5, 50,50,null);
					break;
			}
		}

		//BOSS
		this.compteurBoss+=2;
		if(this.compteurBoss>130){
			this.compteurBoss=0;
			this.modeAttaqueBoss=false;
		}

		for (Monstre m : jeuEvolution.getMonstres()) {
			if(m.getId()==4) {
				int posX = m.getX();
				int posY = m.getY();
				if(((Boss)m).getAttaque() && !this.modeAttaqueBoss){
					this.modeAttaqueBoss = true;
					this.compteurBoss = 0;
				}
				if(((Boss)m).getDirection()&&!this.modeAttaqueBoss){
					g.drawImage(grim_i_d[compteurBoss/13], posX-32, posY-80, 100,100,null);
				}
				if(!((Boss)m).getDirection()&&!this.modeAttaqueBoss){
					g.drawImage(grim_i_g[compteurBoss/13], posX-66, posY-80, 100,100,null);
				}
				if(((Boss)m).getDirection()&&this.modeAttaqueBoss){
					g.drawImage(grim_a_d[compteurBoss/13], posX-88, posY-96, 150,120,null);
				}
				if(!((Boss)m).getDirection()&&this.modeAttaqueBoss){
					g.drawImage(grim_a_g[compteurBoss/13], posX-88, posY-96, 150,120,null);
				}


			}
		}

		if(jeuEvolution.getAventurier().getVivant())
		{
			if(jeuEvolution.getAventurier().getSaut()){
				saut = -20;
				g.setColor(new Color(0,0,0,120));
				g.fillOval(jeuEvolution.getAventurier().getX()-16,jeuEvolution.getAventurier().getY()-10,35,20);
			}else{
				saut = 0;
			}
			if(jeuEvolution.getDirection()){
				g.drawImage(perso_droite[jeuEvolution.getCompteurPas()/5], jeuEvolution.getAventurier().getX()-20, jeuEvolution.getAventurier().getY()-50+saut, 45,60,null);
				if(jeuEvolution.getAventurier().getAttaque())
					g.drawImage(epee[0], jeuEvolution.getAventurier().getX()-10, jeuEvolution.getAventurier().getY()-40+saut, 60,60,null);
			}
			else{
				g.drawImage(perso_gauche[jeuEvolution.getCompteurPas()/5], jeuEvolution.getAventurier().getX()-20, jeuEvolution.getAventurier().getY()-50+saut, 45,60,null);
				if(jeuEvolution.getAventurier().getAttaque())
					g.drawImage(epee[1], jeuEvolution.getAventurier().getX()-45, jeuEvolution.getAventurier().getY()-40+saut, 60,60,null);
			}
		}else
		{
			g.drawImage(mort, jeuEvolution.getAventurier().getX()-20, jeuEvolution.getAventurier().getY()-50, 45,60,null);
		}

		this.gestionVie(g);


		
		
		//affichage de l'etage du labyrinthe
		g.setFont(new Font(null, Font.BOLD, 40)); 
		g.setColor(Color.black);
		String texte = this.jeuEvolution.titreLVL;
		//permet de centrer le texte
		this.centrerTexte(g, texte, 900,60, g.getFont());

		//quand le joueur gagne la partie
		if(jeuEvolution.aGagner()){
			g.setFont(new Font(null, Font.PLAIN, 100));
			g.setColor(new Color(0,0,0,120));
			g.fill(new Rectangle(0,0,900,900));
			g.setColor(Color.YELLOW);
			g.drawImage(bravo, 0,0,null);

		}
		
		g.dispose();
	}
	
	
	
	public void gestionVie(Graphics2D g) {
		//deux barre de vie : une rouge et une verte pour les entites
		
		//barre des monstres
		for (Monstre m : jeuEvolution.getMonstres()) {
			int pvMax =  m.getPvMax();
			int pvCourant = m.getPv();
			int x = m.getX() - pvMax / 2;
			int y = m.getY() - Case.TAILLE;
			if (m.getId() == 4) y -= 25;
			//contour
			g.setColor(Color.black);
			g.fillRect(x - 2, y - 2, pvMax + 4, 7 + 4);
			//barre de vie rouge
			g.setColor(Color.red);
			g.fillRect(x, y, pvMax, 7);
			//barre de vie verte
			g.setColor(Color.green);
			g.fillRect(x, y, pvCourant , 7);
		}
		//barre du joueur
		int pvMax = this.jeuEvolution.getAventurier().getPvMax();
		int pvCourant = this.jeuEvolution.getAventurier().getPv();
		//contour
		g.setColor(Color.black);
		g.fillRect(this.jeuEvolution.getAventurier().getX()-TAILLE_CASE/3-2, this.jeuEvolution.getAventurier().getY()-TAILLE_CASE-2+saut, pvMax+4, 7+4);
		//barre de vie rouge
		g.setColor(Color.red);
		g.fillRect(this.jeuEvolution.getAventurier().getX()-TAILLE_CASE/3, this.jeuEvolution.getAventurier().getY()-TAILLE_CASE+saut, pvMax, 7);
		//barre de vie verte
		g.setColor(Color.green);
		g.fillRect(this.jeuEvolution.getAventurier().getX()-TAILLE_CASE/3, this.jeuEvolution.getAventurier().getY()-TAILLE_CASE+saut,pvCourant , 7);
		if (this.jeuEvolution.getAventurier().getVivant() == false) {
			g.setFont(new Font(null, Font.PLAIN, 100)); 
			g.setColor(new Color(0,0,0,120));
			g.fill(new Rectangle(0,0,900,900));
			g.setColor(Color.WHITE);
			this.centrerTexte(g, "GAME OVER", 900,900, g.getFont());
		}
		
	}
	
	//permet de centrer un texte en fonction de deux coordonnes
	public void centrerTexte(Graphics2D g, String text, int posx, int posy , Font font) {
		
		Rectangle rect = new Rectangle(0,0,posx,posy);
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y);
	}

	public void initialiserReaper()
	{
		try {
			grim_i_d[0] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_00_delay-0.08s.gif"));
			grim_i_d[1] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_01_delay-0.08s.gif"));
			grim_i_d[2] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_02_delay-0.08s.gif"));
			grim_i_d[3] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_03_delay-0.08s.gif"));
			grim_i_d[4] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_04_delay-0.08s.gif"));
			grim_i_d[5] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_05_delay-0.08s.gif"));
			grim_i_d[6] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_06_delay-0.08s.gif"));
			grim_i_d[7] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_07_delay-0.08s.gif"));
			grim_i_d[8] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_08_delay-0.08s.gif"));
			grim_i_d[9] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_09_delay-0.08s.gif"));
			grim_i_d[10] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_10_delay-0.08s.gif"));
			grim_i_d[11] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_11_delay-0.08s.gif"));
			grim_i_d[12] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_12_delay-0.08s.gif"));
			grim_i_d[12] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_d/frame_13_delay-0.08s.gif"));

			grim_i_g[0] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_00_delay-0.08s.gif"));
			grim_i_g[1] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_01_delay-0.08s.gif"));
			grim_i_g[2] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_02_delay-0.08s.gif"));
			grim_i_g[3] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_03_delay-0.08s.gif"));
			grim_i_g[4] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_04_delay-0.08s.gif"));
			grim_i_g[5] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_05_delay-0.08s.gif"));
			grim_i_g[6] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_06_delay-0.08s.gif"));
			grim_i_g[7] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_07_delay-0.08s.gif"));
			grim_i_g[8] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_08_delay-0.08s.gif"));
			grim_i_g[9] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_09_delay-0.08s.gif"));
			grim_i_g[10] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_10_delay-0.08s.gif"));
			grim_i_g[11] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_11_delay-0.08s.gif"));
			grim_i_g[12] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_12_delay-0.08s.gif"));
			grim_i_g[12] = ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_13_delay-0.08s.gif"));

			grim_a_g[0] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_00_delay-0.08s.gif"));
			grim_a_g[1] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_01_delay-0.08s.gif"));
			grim_a_g[2] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_02_delay-0.08s.gif"));
			grim_a_g[3] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_03_delay-0.08s.gif"));
			grim_a_g[4] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_04_delay-0.08s.gif"));
			grim_a_g[5] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_05_delay-0.08s.gif"));
			grim_a_g[6] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_06_delay-0.08s.gif"));
			grim_a_g[7] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_07_delay-0.08s.gif"));
			grim_a_g[8] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_08_delay-0.08s.gif"));
			grim_a_g[9] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_09_delay-0.08s.gif"));
			grim_a_g[10] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_10_delay-0.08s.gif"));
			grim_a_g[11] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_11_delay-0.08s.gif"));
			grim_a_g[12] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_12_delay-0.08s.gif"));
			grim_a_g[12] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_g/frame_13_delay-0.08s.gif"));

			grim_a_d[0] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_00_delay-0.08s.gif"));
			grim_a_d[1] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_01_delay-0.08s.gif"));
			grim_a_d[2] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_02_delay-0.08s.gif"));
			grim_a_d[3] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_03_delay-0.08s.gif"));
			grim_a_d[4] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_04_delay-0.08s.gif"));
			grim_a_d[5] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_05_delay-0.08s.gif"));
			grim_a_d[6] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_06_delay-0.08s.gif"));
			grim_a_d[7] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_07_delay-0.08s.gif"));
			grim_a_d[8] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_08_delay-0.08s.gif"));
			grim_a_d[9] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_09_delay-0.08s.gif"));
			grim_a_d[10] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_10_delay-0.08s.gif"));
			grim_a_d[11] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_11_delay-0.08s.gif"));
			grim_a_d[12] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_12_delay-0.08s.gif"));
			grim_a_d[12] = ImageIO.read(getClass().getResourceAsStream("/reaper/attack_d/frame_13_delay-0.08s.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
