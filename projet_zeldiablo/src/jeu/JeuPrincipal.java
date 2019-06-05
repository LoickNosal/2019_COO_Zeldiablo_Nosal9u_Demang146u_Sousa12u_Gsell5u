package jeu;

import org.json.simple.*;
import org.json.simple.parser.*;

import moteurJeu.DessinPerso;
import moteurJeu.Jeu;
import moteurJeu.JeuMonstre;
import moteurJeu.JeuEvolution;
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
    public Aventurier aventurier; //pas utile
    public int level;


    /**
     * construit le modèle du jeu
     */
    public JeuPrincipal() {
        this.level = 4;
        chargerLVL(level);
        this.aventurier = new Aventurier(50, 100, 100, "Aventurier");
        this.aventurier.setLabyrinthe(this.labyrinthe);
        ArrayList<Jeu> jeu = new ArrayList<Jeu>();
        JeuEvolution jeuperso = new JeuEvolution(this.aventurier);


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
        JeuMonstre jeumonstre = new JeuMonstre(monstres);
        jeumonstre.setAventurier(this.aventurier);

        jeu.add(jeuperso);
        jeu.add(jeumonstre);
		DessinPerso dp = new DessinPerso(jeuperso, jeumonstre);

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
                case 4:
                	json = chargerJSON("lvl4.json");
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
        String path = URLDecoder.decode(Objects.requireNonNull(getClass().getClassLoader().getResource(fichier)).getPath(), "UTF-8");
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
            int id = ((Long) jsonM.get("id")).intValue();

            int posX = ((Long) ((JSONArray) jsonM.get("pos")).get(0)).intValue();
            int posY = ((Long) ((JSONArray) jsonM.get("pos")).get(1)).intValue();
            posX = posX * Case.TAILLE + Case.TAILLE / 2;
            posY = posY * Case.TAILLE + Case.TAILLE / 2;
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




    public static void main(String[] args) {
        new JeuPrincipal();
    }


}
