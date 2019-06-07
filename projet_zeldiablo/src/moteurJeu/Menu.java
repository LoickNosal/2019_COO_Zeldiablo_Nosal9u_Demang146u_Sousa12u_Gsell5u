package moteurJeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URLDecoder;

public class Menu extends JPanel {
	
	/**
	 * le jeu du menu
	 */
    private JeuEvolution jeu;

    /**
     * constructeur du menus
     * @param jeuEv jeu du menu
     */
    public Menu(JeuEvolution jeuEv){
        super();
        this.jeu = jeuEv;
        setPreferredSize(new Dimension(1200,1200));
        setBackground(Color.WHITE);
        setLayout(null);

        JButton j = new JButton("JOUER");
        j.setFont(new Font(null, Font.PLAIN, 30));
        j.setBounds(0,700,450,200);
        j.setBackground(Color.gray);
        j.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disparaitre();
                jeu.restart();
                jeu.getAventurier().revivre();

            }
        });

        JButton q = new JButton("QUITTER");
        q.setFont(new Font(null, Font.PLAIN, 30));
        q.setBounds(450,700,450,200);
        q.setBackground(Color.gray);
        q.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(q);
        add(j);

    }
    /**
     * permet de faire disparaitre le menu
     */
    public void disparaitre()
    {
        setVisible(false);
    }
    /**
     * permet de faire apparaitre le menu
     * @return
     */
    public boolean visible()
    {
        return isVisible();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image menu = null;
        try {
            menu = ImageIO.read(getClass().getResourceAsStream("/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(menu, 0,0,this);
    }
    
    /**
     * ajoute le jeu au menu
     * @param j permet d'ajouter le jeu
     */
    public void ajouterJeu(JeuEvolution j)
    {
        this.jeu = j;
    }
}
