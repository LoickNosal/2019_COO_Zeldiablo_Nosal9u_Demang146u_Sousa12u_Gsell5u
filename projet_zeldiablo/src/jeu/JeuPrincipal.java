package jeu;

import org.json.simple.*;
import org.json.simple.parser.*;

import moteurJeu.DessinPerso;
import moteurJeu.JeuEvolution;
import moteurJeu.MoteurGraphique;

import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Objects;

/**
 * modelise le jeu principal
 * @author Louis Demange
 */
public class JeuPrincipal {

    private JeuEvolution jeuEvolution;
    private Aventurier aventurier;
    private int level;


    /**
     * construit le modèle du jeu
     */
    public JeuPrincipal() {
        this.level = 1;
        aventurier = new Aventurier(50, 100, 100, "Aventurier");
        jeuEvolution = new JeuEvolution(aventurier, this);

        chargerLVL(level);


        /*
        MonstreImmobile m = new MonstreImmobile(50,  1 * Case.TAILLE + Case.TAILLE / 2, 1 * Case.TAILLE + Case.TAILLE / 2, 1, 50, "lol");
        MonstreAleatoire m2 = new MonstreAleatoire(50, 400, 100, 1, 50, "m2");
        MonstreSuivi m3 = new MonstreSuivi(50, 400, 100, 1, 50, "m3");
        m.setLabyrinthe(this.labyrinthe);
        m2.setLabyrinthe(this.labyrinthe);
        m3.setLabyrinthe(this.labyrinthe);
        ArrayList<Monstre> monstres = new ArrayList<Monstre>();
        monstres.add(m);
        monstres.add(m2);
        monstres.add(m3);
        jeuEvolution.setMonstres(monstres);
        */

		DessinPerso dp = new DessinPerso(jeuEvolution);

		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(jeuEvolution, dp);

		// lance la boucle de jeu qui tourne jusque la fin du jeu
		try {
			moteur.lancerJeu(900, 900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


    }


    /**
     * charge un niveau a partir du numero du niveau
     * @param lvl numero du niveau
     */
    public void chargerLVL(int lvl) {
        try {
            JSONObject json;
            switch (lvl) {
                case 1:
                    json = chargerJSON("lvl1.json");
                    decoderJSON(json);
                    break;
                case 2:
                    json = chargerJSON("lvl2.json");
                    decoderJSON(json);
                    break;
                case 3:
                    json = chargerJSON("lvl3.json");
                    decoderJSON(json);
                    break;
                case 4:
                	json = chargerJSON("lvl4.json");
                    decoderJSON(json);
                    break;
                default:
                    //TODO
            }
        } catch (ParseException | IOException e) {
            //TODO
        }
    }


    /**
     * charge un JSON a partir
     * @param fichier nom du fichier JSON
     * @return le fichier JSON
     * @throws IOException erreur dans l'url du fichier
     * @throws ParseException erreur avec le JSON
     */
    private JSONObject chargerJSON(String fichier) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        String path = URLDecoder.decode(Objects.requireNonNull(getClass().getClassLoader().getResource(fichier)).getPath(), "UTF-8");
        Object obj = parser.parse(new FileReader(path));
        return (JSONObject) obj;
    }


    /**
     * decode le JSON et changer le niveau de JeuEvoluer
     * @param json fichier JSON comtenant les informations du niveau
     */
    private void decoderJSON(JSONObject json) {
        // decode
        JSONArray jsonModeleLaby = (JSONArray) json.get("labyrinthe");
        String[] modeleLabyrinthe = new String[jsonModeleLaby.size()];
        for (int i = 0; i < modeleLabyrinthe.length; i++) {
            modeleLabyrinthe[i] = (String) jsonModeleLaby.get(i);
        }
        Labyrinthe labyrinthe = new Labyrinthe(modeleLabyrinthe);

        JSONArray jsonMonstres = (JSONArray) json.get("monstres");
        ArrayList<Monstre> monstres = new ArrayList<Monstre>(jsonMonstres.size());
        for (int i = 0; i < jsonMonstres.size(); i++) {
            JSONObject jsonM = (JSONObject) jsonMonstres.get(i);
            int id = ((Long) jsonM.get("id")).intValue();

            int posX = ((Long) ((JSONArray) jsonM.get("pos")).get(0)).intValue();
            int posY = ((Long) ((JSONArray) jsonM.get("pos")).get(1)).intValue();
            posX = posX * Case.TAILLE + Case.TAILLE / 2;
            posY = posY * Case.TAILLE + Case.TAILLE / 2;

            Monstre m = Monstre.creerMonstreParID(id, posX, posY);
            m.setLabyrinthe(labyrinthe);
            m.setCible(aventurier);
            monstres.add(m);
        }

        jeuEvolution.changeNiveau(labyrinthe, monstres);
    }


    /**
     * retourne le niveau actuel
     * @return le niveau
     */
    public int getLVL() {
        return level;
    }

    public void chargerLVLSuivant() {
        level++;
        chargerLVL(level);
    }



    public static void main(String[] args) {
        new JeuPrincipal();
    }


}
