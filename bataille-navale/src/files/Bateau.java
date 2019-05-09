/*
 * Bateau.java                                                   28/04/2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright
 */

package files;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe Bateau permettant de créer des bateaux de 
 * @author INFO 1
 */
public class Bateau {
    
    /** nom par défaut d'un navire */
    private static final String NOM_DEFAUT =  "Vedette";
    
    /** taille par défaut de ce navire */
    private static final int TAILLE_DEFAUT = 2;
    
    /** Nom du bateau (ex : Sous-marin, porte-avions, destroyer etc...) */
    private String nom;

    /** Taille du bateau */
    private int taille;

    /** Vie restante du bateau */
    private int vie;

    /** Nombre de touches sur le bateau */
    private int nbTouche;

    /** Positions du bateau */
    private int pos[];
    
    /** Servira pour placer les bateaux et parcourir horizontalement la mer */
    static int i;
    /** Servira pour placer les bateaux et parcourir verticalement la mer */
    static int j;
 
    

    /** Liste contenant tous les bateaux, composant une flotte */
    static List<Bateau> flotte = new ArrayList<Bateau>();
    
    /**
     * TODO commenter le rôle de ce constructeur
     */
    public Bateau() {
        this.nom = NOM_DEFAUT;
        this.taille = TAILLE_DEFAUT;
        this.vie = taille;
        this.nbTouche = 0;
        this.i = 0;
        this.j = 0;
        this.pos = new int[taille];
        flotte.add(this);
    }

    /**
     * TODO idem que le précédent, commenter le rôle de ce constructeur
     * @param nom
     * @param taille
     */
    public Bateau(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
        this.vie = taille;
        this.nbTouche = 0;
        this.i = 0;
        this.j = 0;
        this.pos = new int[taille];
        flotte.add(this);
    }

    /**
     * Retourne le nom du bateau
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la taille du bateau.
     * @return taille
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Ajoute une touche au bateau et teste s'il est seulement "touché" ou "coulé"
     * Retourne true s'il est coulé ou faux (false) s'il est simplement touché
     * @return true ou false
     */
    public boolean ajoutTouche() {
        this.nbTouche ++;
        if (nbTouche == vie) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Place un point donné dans le tableau 
     * contenant les positions de ce bateau.
     * Ici le point x ( abscisse ).
     * @param position Un point contenant d'abcisse du bateau 
     */
    public void SetPositionHorizontale(int position) {
        if (i < vie) {
            pos[i] = position;
            i++;
        }
        if (i >= vie) {
            i = 0;
        }
    }

    /**
     * Place un point donné dans le tableau 
     * contenant les positions de ce bateau.
     * Ici le point y ( ordonnée ).
     * @param position
     */
    public void SetPositionVerticale(int position) {
        /* Le nombre de point doit être inférieur à la taille du bateau */
        if(j < vie) {
            pos[j] = position;
            j++;
        }
        if(j >= vie) {
            j = 0;
        }
    }

    /**
     * Retourne le tableau des différentes positions des cases du bateau
     * @return pos
     */
    public int[] getPositions() {
        return pos;
    }
    
    /**
     * retourne la liste contenant tout les objets Bateau créés
     * 
     * @return flotte
     */
    public static List<Bateau> getFlotte() {
        return new ArrayList<>(flotte);
    }
    
    /**
     * vide la liste des objets créés
     */
    public static void clearFlotte() {
        flotte.clear();
    }
    
    /**
     * Renvoie une chaine de caractère avec le nom et 
     * la taille de ce bateau
     */
    public String toString() {
        return "Nom : " + this.getNom() 
                + "Taille : " + this.getTaille() + "\n";  
    } 
    
}
