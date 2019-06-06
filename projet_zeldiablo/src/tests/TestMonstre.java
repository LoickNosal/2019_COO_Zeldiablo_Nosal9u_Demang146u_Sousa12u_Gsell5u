package tests;

import jeu.Aventurier;
import jeu.Case;
import jeu.Labyrinthe;
import jeu.Monstre;
import jeu.MonstreAleatoire;
import jeu.MonstreImmobile;
import jeu.MonstreSuivi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test pour les Monstres (ajout de test dans les test pour Entite)
 * @author Alexandre Sousa
 */
public class TestMonstre {
	@Test
    public void testConstructeurMonstre() {
		
    	Labyrinthe lab = new Labyrinthe();
    	Monstre m = new MonstreImmobile(10, 5, 5, 3, 50, "Chevalier noir");
    	Monstre m2 = new MonstreAleatoire( 10, 5, 5, -5, 50, "Chevalier noir");
        
        assertEquals("Le constructeur doit fonctionner normalement (DEGAT)", 3, m.getDegat());
        assertEquals("Le constructeur 2 doit fonctionner normalement (DEGAT)", 0, m2.getDegat());
        assertEquals("Il ne devrait pas avor de cible (CIBLE)", null, m.getCible());
    }
	
	@Test
    public void testMonstreSuiviComportementLibre() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 200, 100, "Eoloc");
        MonstreSuivi m = new MonstreSuivi(10, 300, 100, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.comportement();
        
        assertEquals("Le MonstreSuivi aurait du se diriger a gauche vers le personnage ", 297, m.getX());
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger en haut ou en bas vers le personnage", 100, m.getY());
    }
	
	@Test
    public void testMonstreSuiviComportementMur() {
		String[] modeleDefaut =
            {
                    "X X X X X X X X X X X X X X X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - X - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X - - - - - - - - - - - - - X",
                    "X X X X X X X X X X X X X X X"
            };
		
        Labyrinthe lab = new Labyrinthe(modeleDefaut);
        Aventurier perso = new Aventurier(50, 5*Case.TAILLE, 5*Case.TAILLE, "Eoloc");
        MonstreSuivi m = new MonstreSuivi(10, 8*Case.TAILLE, 5*Case.TAILLE, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.comportement();
        
        assertEquals("Le MonstreSuivi n'aurait du se diriger a gauche ou la droite vers le personnage ", 8*Case.TAILLE, m.getX());
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger en haut ou en bas vers le personnage", 5*Case.TAILLE, m.getY());
    }
	
	@Test
    public void testMonstreSuiviDegat() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 1*Case.TAILLE, 1*Case.TAILLE, "Eoloc");
        MonstreSuivi m = new MonstreSuivi(10, 2*Case.TAILLE-40, 1*Case.TAILLE-40, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.comportement();
        
        assertEquals("L'aventurier aurait du perdre des pv", 45, perso.getPv());
    }
	
	@Test
    public void testMonstreSuiviSubirDegat() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 1*Case.TAILLE, 1*Case.TAILLE, "Eoloc");
        MonstreSuivi m = new MonstreSuivi(10, 2*Case.TAILLE-40, 1*Case.TAILLE-40, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.subirDegat(5);
        
        assertEquals("Le monstre aurait du perdre des pv", 5, m.getPv());
    }
	
	@Test
    public void testMonstreAleatoireDegat() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 1*Case.TAILLE, 1*Case.TAILLE, "Eoloc");
        MonstreAleatoire m = new MonstreAleatoire(10, 2*Case.TAILLE-40, 1*Case.TAILLE-40, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.comportement();
        
        assertEquals("L'aventurier aurait du perdre des pv", 45, perso.getPv());
    }
	
	@Test
    public void testMonstreAleatoireSubirDegat() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 1*Case.TAILLE, 1*Case.TAILLE, "Eoloc");
        MonstreAleatoire m = new MonstreAleatoire(10, 2*Case.TAILLE-40, 1*Case.TAILLE-40, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.subirDegat(5);
        
        assertEquals("Le monstre aurait du perdre des pv", 5, m.getPv());
    }
	
	@Test
    public void testMonstreImmobile() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 200, 100, "Eoloc");
        MonstreImmobile m = new MonstreImmobile(10, 300, 100, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.comportement();
        
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger a gauche ou a droite", 300, m.getX());
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger en haut ou en bas", 100, m.getY());
    }
	
	@Test
    public void testMonstreImmobileDegat() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 1*Case.TAILLE, 1*Case.TAILLE, "Eoloc");
        MonstreImmobile m = new MonstreImmobile(10, 2*Case.TAILLE-40, 1*Case.TAILLE-40, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.comportement();
        
        assertEquals("L'aventurier aurait du perdre des pv", 45, perso.getPv());
    }
	
	@Test
    public void testMonstreImmobileSubirDegat() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 1*Case.TAILLE, 1*Case.TAILLE, "Eoloc");
        MonstreImmobile m = new MonstreImmobile(10, 2*Case.TAILLE-40, 1*Case.TAILLE-40, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        m.setCible(perso);
        m.subirDegat(5);
        
        assertEquals("Le monstre aurait du perdre des pv", 5, m.getPv());
    }
}