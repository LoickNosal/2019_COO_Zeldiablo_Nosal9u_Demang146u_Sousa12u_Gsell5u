package moteurJeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

public class Menu extends JPanel {


    public Menu(){
        super();
        setPreferredSize(new Dimension(1200,1200));
        setBackground(Color.WHITE);
        setLayout(null);

        JButton j = new JButton("JOUER");
        j.setBounds(0,700,450,200);
        j.setBackground(Color.gray);
        j.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disparaitre();
            }
        });

        JButton q = new JButton("QUITTER");
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

    public void disparaitre()
    {
        setVisible(false);
    }

    public boolean visible()
    {
        return isVisible();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image menu = null;
        try {
            menu = ImageIO.read(new File(URLDecoder.decode(getClass().getClassLoader().getResource("menu.png").getPath(), "UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(menu, 0,0,this);
    }
}
