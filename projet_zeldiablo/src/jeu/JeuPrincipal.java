package jeu;

import org.json.simple.*;
import org.json.simple.parser.*;

import moteurJeu.DessinPerso;
import moteurJeu.JeuPerso;
import moteurJeu.MoteurGraphique;

import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

/**
 * modelise le jeu principal
 * @author Louis Demange
 */
public class JeuPrincipal {

    public Labyrinthe labyrinthe;
    public Aventurier aventurier;
    public int level;


    /**
     * construit le modèle du jeu
     */
    public JeuPrincipal() {
        level = 1;
        chargerLVL(level);
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

		MonstreImmobile m = new MonstreImmobile(1, 10,1,1, 50, 10 , "lol", labyrinthe);
		labyrinthe.getMonstres().add(m);
    }


    /**
     * charge un niveau a partir du numero du niveau
     * @param lvl numero du niveau
     */
    private void chargerLVL(int lvl) {
        try {
            JSONObject json;
            switch (lvl) {
                case 1:
                    json = chargerJSON("lvl1.json");
                    this.labyrinthe = decoderJSON(json);
                    break;
                case 2:
                    json = chargerJSON("lvl2.json");
                    this.labyrinthe = decoderJSON(json);
                    break;
                case 3:
                    json = chargerJSON("lvl3.json");
                    this.labyrinthe = decoderJSON(json);
                    break;
                default:
                    this.labyrinthe = new Labyrinthe();
            }
        } catch (ParseException | IOException e) {
            this.labyrinthe = new Labyrinthe();
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
        String path = URLDecoder.decode(Objects.requireNonNull(getClass().getClassLoader().getResource(fichier)).getPath(), StandardCharsets.UTF_8);
        Object obj = parser.parse(new FileReader(path));
        return (JSONObject) obj;
    }


    /**
     * construit un labyrinthe a partir d'un JSON
     * @param json fichier JSON comtenant les informations du niveau
     * @return le labyrinthe correspondant au fichier JSON
     */
    private Labyrinthe decoderJSON(JSONObject json) {
        JSONArray jsonModeleLaby = (JSONArray) json.get("labyrinthe");
        String[] modeleLabyrinthe = new String[jsonModeleLaby.size()];
        for (int i = 0; i < modeleLabyrinthe.length; i++) {
            modeleLabyrinthe[i] = (String) jsonModeleLaby.get(i);
        }


        JSONArray jsonMonstres = (JSONArray) json.get("monstres");
        ArrayList<Monstre> monstres = new ArrayList<Monstre>(jsonMonstres.size());
        for (int i = 0; i < jsonMonstres.size(); i++) {
            JSONObject jsonM = (JSONObject) jsonMonstres.get(i);
            System.out.println(jsonM);
            //int id = Integer.valueOf((String ) jsonM.get("id"));
            //System.out.println(id);
        }


        return new Labyrinthe(modeleLabyrinthe);
    }


    /**
     * retourne le niveau actuel
     * @return le niveau
     */
    public int getLVL() {
        return level;
    }

}
