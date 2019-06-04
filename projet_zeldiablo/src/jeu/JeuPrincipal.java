package jeu;

import org.json.simple.*;
import org.json.simple.parser.*;

import moteurJeu.DessinPerso;
import moteurJeu.JeuPerso;
import moteurJeu.MoteurGraphique;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * modelise le jeu principal
 */
public class JeuPrincipal {

    public Labyrinthe labyrinthe;
    public Aventurier aventurier;
    public static final int level = 1;


    public JeuPrincipal() {
        chargerLVL(3);
        this.aventurier = new Aventurier(10, 100, 100, "Aventurier", this.labyrinthe);
        JeuPerso jeu = new JeuPerso(this.aventurier);
		DessinPerso dp = new DessinPerso(jeu, this.labyrinthe);

		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(jeu, dp);

		// lance la boucle de jeu qui tourne jusque la fin du jeu
		try {
			moteur.lancerJeu(900, 900);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    private void chargerLVL(int lvl) {
        try {
            JSONParser parser = new JSONParser();
            String ressourcesPath = URLDecoder.decode(getClass().getClassLoader().getResource("./").getPath(), "UTF-8");
            Object obj;
            JSONObject json;

            switch (lvl) {
                case 1:
                    obj = parser.parse(new FileReader(ressourcesPath + "lvl1.json"));
                    json = (JSONObject) obj;
                    this.labyrinthe = chargerLabyrinthe(json);
                    break;
                case 2:
                    obj = parser.parse(new FileReader(ressourcesPath + "lvl2.json"));
                    json = (JSONObject) obj;
                    this.labyrinthe = chargerLabyrinthe(json);
                    break;
                case 3:
                	obj = parser.parse(new FileReader(ressourcesPath + "lvl3.json"));
                    json = (JSONObject) obj;
                    
                    this.labyrinthe = chargerLabyrinthe(json);
                   
                    break;
                default:
                    this.labyrinthe = new Labyrinthe();
            }

        } catch (ParseException | IOException e) {
            this.labyrinthe = new Labyrinthe();
        }
    }


    private Labyrinthe chargerLabyrinthe(JSONObject json) {
        JSONArray jsonArray = (JSONArray) json.get("labyrinthe");
        String[] modeleLabyrinthe = new String[jsonArray.size()];

        for (int i = 0; i < modeleLabyrinthe.length; i++) {
            modeleLabyrinthe[i] = (String) jsonArray.get(i);

        }

        return new Labyrinthe(modeleLabyrinthe);
    }

}
