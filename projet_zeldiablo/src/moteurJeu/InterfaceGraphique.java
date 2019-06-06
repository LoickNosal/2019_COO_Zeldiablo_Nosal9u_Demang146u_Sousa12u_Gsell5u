package moteurJeu;

import javax.swing.*;
import java.awt.*;


/**
 * cree une interface graphique avec son controleur et son afficheur
 * @author Graou
 *
 */
public class InterfaceGraphique  {

	/**
	 * Le Panel qui affiche le menu de départ
	 */
	public Menu menu;

	/**
	 * le Panel lie a la JFrame
	 */
	private PanelDessin panel;
	
	/**
	 * le controleur lie a la JFrame
	 */
	private Controleur controleur;
	
	/**
	 * la construction de l'interface grpahique
	 * - construit la JFrame
	 * - construit les Attributs
	 * 
	 * @param afficheurUtil l'afficheur a utiliser dans le moteur
	 * 
	 */
	public InterfaceGraphique(DessinJeu afficheurUtil,int x,int y)
	{
		//creation JFrame
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		// creation panel
		JPanel principal = new JPanel();
		this.panel=new PanelDessin(x, y,afficheurUtil);


		principal.setLayout(null);
		this.panel.setBounds(0,0,900,900);
		principal.setPreferredSize(new Dimension(900,900));

		this.menu = new Menu(null);
		menu.setBounds(0,0,900,900);

		principal.add(menu);
		principal.add(this.panel);

		f.setContentPane(principal);
		//ajout du controleur
		Controleur controlleurGraph=new Controleur();
		this.controleur=controlleurGraph;
		principal.addKeyListener(controlleurGraph);
		principal.addMouseListener(controlleurGraph);
		//recuperation du focus
		f.pack();
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();

		f.setVisible(true);
	}
	
	
	/**
	 * retourne le controleur de l'affichage construit
	 * @return
	 */
	public Controleur getControleur() {
		return controleur;
	}

	/**
	 * demande la mise a jour du dessin
	 */
	public void dessiner() {
		this.panel.dessinerJeu();	
	}
	
}
