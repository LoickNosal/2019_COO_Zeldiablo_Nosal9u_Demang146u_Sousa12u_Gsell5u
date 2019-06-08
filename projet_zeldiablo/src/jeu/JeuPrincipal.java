package jeu;

import org.json.simple.*;
import org.json.simple.parser.*;

import moteurJeu.DessinPerso;
import moteurJeu.JeuEvolution;
import moteurJeu.MoteurGraphique;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Démarre le jeu et charge les differents niveaux
 *
 * @author Louis Demange
 */
public class JeuPrincipal {

	/** le jeu en cours */
    private JeuEvolution jeuEvolution;
    /** l'aventurier du jeu */
    private Aventurier aventurier;
    /** le level du jeu (etage du laybrinthe) */
    private int level;
    /** permet de changer le level de depart (debug) */
    private static int levelDebut = 1;


    /**
     * construit le modele du jeu
     */
    public JeuPrincipal() {
        aventurier = new Aventurier(50, 100, 100, "Aventurier");
        jeuEvolution = new JeuEvolution(aventurier, this);
        jeuEvolution.restart();
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
            JSONObject json = chargerJSON("/maps/lvl" + lvl +".json");
            decoderJSON(json);
        } catch (ParseException e) {
            System.out.printf("fichier \"lvl%d.json\" corrompu", lvl);
        } catch (IOException e) {
            System.out.printf("Chargement du fichier \"lvl%d.json\" impossible", lvl);
        } catch (NullPointerException e) {
            System.out.printf("fichier \"lvl%d.json\" introuvable\n", lvl);
            e.printStackTrace();
        }
    }


    /**
     * charge un JSON a partir d'un fichier json
     * @param fichier nom du fichier JSON
     * @return le fichier JSON
     * @throws IOException    erreur dans l'url du fichier
     * @throws ParseException erreur avec le JSON
     */
    private JSONObject chargerJSON(String fichier) throws IOException, ParseException, NullPointerException {
        JSONParser parser = new JSONParser();
        InputStream in = getClass().getResourceAsStream(fichier);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return (JSONObject) parser.parse(reader);
    }


    /**
     * decode le JSON et changer le niveau de JeuEvoluer
     * @param json fichier JSON comtenant les informations du niveau
     */
    private void decoderJSON(JSONObject json) {
        // titre
        String titre = (String) json.get("titre");

        // labyrinthe
        JSONArray jsonModeleLaby = (JSONArray) json.get("labyrinthe");
        String[] modeleLabyrinthe = new String[jsonModeleLaby.size()];
        for (int i = 0; i < modeleLabyrinthe.length; i++) {
            modeleLabyrinthe[i] = (String) jsonModeleLaby.get(i);
        }
        Labyrinthe labyrinthe = new Labyrinthe(modeleLabyrinthe);

        // spawn position
        int spawnX = ((Long) ((JSONArray) json.get("spawnPos")).get(0)).intValue();
        int spawnY = ((Long) ((JSONArray) json.get("spawnPos")).get(1)).intValue();
        spawnX = spawnX * Case.TAILLE + Case.TAILLE / 2;
        spawnY = spawnY * Case.TAILLE + Case.TAILLE / 2;
        aventurier.setPositon(spawnX, spawnY);

        // monstres
        JSONArray jsonMonstres = (JSONArray) json.get("monstres");
        ArrayList<Monstre> monstres = null;
        if (jsonMonstres != null) {
            monstres = new ArrayList<Monstre>(jsonMonstres.size());
            for (int i = 0; i < jsonMonstres.size(); i++) {
                JSONObject jsonM = (JSONObject) jsonMonstres.get(i);
                int id = ((Long) jsonM.get("id")).intValue();
                int posX = ((Long) ((JSONArray) jsonM.get("pos")).get(0)).intValue();
                int posY = ((Long) ((JSONArray) jsonM.get("pos")).get(1)).intValue();
                posX = posX * Case.TAILLE + Case.TAILLE / 2;
                posY = (int) (posY * Case.TAILLE + Case.TAILLE * 0.7);
                Monstre m = Monstre.creerMonstreParID(id, posX, posY);
                m.setLabyrinthe(labyrinthe);
                m.setCible(aventurier);
                monstres.add(m);
            }
        }

        // items
        JSONArray jsonItems = (JSONArray) json.get("items");
        ArrayList<Item> items = null;
        if (jsonItems != null) {
            items = new ArrayList<Item>(jsonItems.size());
            for (int j = 0; j < jsonItems.size(); j++) {
                JSONObject jsonI = (JSONObject) jsonItems.get(j);
                int id = ((Long) jsonI.get("id")).intValue();
                int posX = ((Long) ((JSONArray) jsonI.get("pos")).get(0)).intValue();
                int posY = ((Long) ((JSONArray) jsonI.get("pos")).get(1)).intValue();
                Item i = Item.creerItemParID(id, posX, posY);
                items.add(i);
            }
        }

        jeuEvolution.changeNiveau(titre, labyrinthe, monstres, items);
    }

    /**
     * permet de recharger le premier niveau (redemarrage jeu)
     */
    public void restart() {
        level = levelDebut;
        chargerLVL(level);
    }

    /**
     * permet de charger le prochain niveau)
     */
    public void chargerLVLSuivant() {
        level++;
        chargerLVL(level);
    }
    
    /**
     * Main du jeu : appelle le jeu principal
     * @param args
     */
    public static void main(String[] args) {
        new JeuPrincipal();
    }


}
