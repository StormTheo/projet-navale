/*
 * Bateau.java                                                   28/04/2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright
 */

package files;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe Bateau permettant de créer des bateaux de 
 * @author Théo Bouchouieff
 * Version 3
 */
public class Bateau {

    /** Nom du bateau (ex : Charles De Gaulle. Ou porte-avions, destroyer etc...) */
    String nom;

    /** Taille du bateau */
    int taille;

    /** Vie restante du bateau */
    int vie;

    /** Nombre de touches sur le bateau */
    int nbTouche;

    /** Positions du bateau */
    int pos[];
    

    /** Liste contenant tous les bateaux, composant une flotte */
    static List<Bateau> flotte = new ArrayList<Bateau>();
    
    /**
     * TODO commenter le rôle de ce constructeur
     */
    public Bateau() {
        
        vie = taille;
        nbTouche = 0;
        i = 0;
        j = 0;
        pos = new int[taille];
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
        vie = taille;
        nbTouche = 0;
        i = 0;
        j = 0;
        pos = new int[taille];
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
     * Ajoute une touche au bateau et teste s'il est "touché" ou "coulé"
     * Retourne true s'il est coulé ou faux (false) s'il est simplement touché
     * @return true ou false
     */
    public boolean ajoutTouche() {
        nbTouche ++;
        if (nbTouche == vie) {
            return true;
        } else {
            return false;
        }
    }


    /** Servira pour placer les bateaux et parcourir horizontalement la mer */
    static int i;
    /** Servira pour placer les bateaux et parcourir verticalement la mer */
    static int j;

    /**
     * Fixe la position du navire
     * @param position
     */
    public void SetPositionHorizontale(int position) {
        if(i < vie)
        {
            pos[i] = position;
            i++;
        }
        if(i == vie) i = 0;
    }

    /**
     * Fixe la position du navire
     * @param position
     */
    public void SetPositionVerticale(int position) {
        if(j < vie) {
            pos[j] = position;
            j++;
        }

        if(j == vie) {
            j = 0;
        }
    }

    /**
     * Retourne le tableau des differentes positions des cases du bateau
     * @return pos
     */
    public int[] getPositions() {
        return pos;
    }
    
    /**
     * TODO commenter le rôle de cette méthode
     * 
     * @return flotte
     */
    public static List<Bateau> getFlotte() {
        return new ArrayList<>(flotte);
    }
    
}
