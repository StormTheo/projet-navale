/*
 * Bateau.java                                                   28/04/2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright
 */

package files;

import files.Plateau;

/**
 * Classe Bateau permettant de créer des bateaux de
 * 
 * @author INFO 1
 */
public class Bateau {

    /** nom par défaut d'un navire */
    private static final String NOM_DEFAUT = "Vedette";

    /** taille par défaut de ce navire */
    private static final int TAILLE_DEFAUT = 2;

    /** Nom du bateau (ex : Sous-marin, porte-avions, destroyer etc...) */
    private String nom;

    /** Taille du bateau */
    private int taille;

    /** Vie restante du bateau */
    private int vie;

    /** true si le bateau est en vie, false si sa vie <= 0 */
    private boolean etat;

    /**
     * constructeurs de l'objet bateau avec les informations par défaut.
     */
    public Bateau() {
        this.nom = NOM_DEFAUT;
        this.taille = TAILLE_DEFAUT;
        this.vie = taille;
        this.etat = true;
    }

    /**
     * constructeur de l'objet bateau, avec les informations nom et taille
     * 
     * @param nom
     * @param taille
     */
    public Bateau(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
        this.vie = taille;
        this.etat = true;
    }

    /**
     * Retourne le nom du bateau
     * 
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la taille du bateau.
     * 
     * @return taille
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Renvoie le nombre de point de vie restant d'un bateau.
     * 
     * @return un int représentant le nbr de points de vie.
     */
    public int getVie() {
        return vie;
    }

    /**
     * @return la valeur de état
     */
    public boolean getEtat() {
        return etat;
    }

    /**
     * @param etat la nouvelle valeur de état
     */
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    /**
     * Ajoute une touche au bateau et teste s'il est seulement "touché" ou "coulé"
     * 
     * @return true s'il est coulé ou false s'il est simplement touché
     */
    public boolean toucher() {
        /* n'est pas sur le point d'Ãªtre détruit */
        if (vie >= 2) {
            vie--;
        }
        /* vas Ãªtre détruit */
        else if (vie == 1 && getEtat()) {
            /*
             * on enlÃ¨ve sa derniÃ¨re vie et on "l'élimine" en passant son état Ã  false
             */
            vie--;
            setEtat(false);
        }
        return vie == 0;
    }
    
    /**
     * vérifie si les coordonnées arguments correspondent aux coordonnées d'un
     * bateau
     * 
     * @param x char coordonnée
     * @param y int coordonnée
     * @param plateauJouer le plateau de jeu
     * @return indexBateau, l'index du bateau qui a été touché, -1 si aucun bateau
     *         n'a été touché.
     */
    public static int verifTir(int x, int y, Plateau plateauJouer) {
        Bateau bateauActuel = null;
        int indexBateau;
        indexBateau = plateauJouer.getGrille(x,y);
        
        return indexBateau;
    }

    /**
     * recherche si il reste dans bateau étant encore en vie ( vie > 0 )
     * @param plateauJouer le plateau de jeu
     * 
     * @return true si il reste des bateaux, false sinon
     */
    public static boolean bateauRestant(Plateau plateauJouer) {
        boolean reste = false;
        for (int index = 0; !reste && index < plateauJouer.getFlotte().size(); index++) {
            reste = plateauJouer.getFlotte().get(index).getEtat();
        }
        return reste;
    }

    /**
     * Renvoie une chaÃ®ne de caractÃ¨re avec le nom et la taille de ce bateau
     */
    public String toString() {
        return "Nom : " + getNom() + ", Taille : " + getTaille() + "\n";
    }

}