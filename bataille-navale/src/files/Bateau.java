/*
 * Bateau.java                                                   28/04/2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright
 */

package files;

import files.Plateau;

/**
 * Classe Bateau permettant de cr�er des bateaux de
 * 
 * @author INFO 1
 */
public class Bateau {

    /** nom par d�faut d'un navire */
    private static final String NOM_DEFAUT = "Vedette";

    /** taille par d�faut de ce navire */
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
     * constructeurs de l'objet bateau avec les informations par d�faut.
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
     * @return un int repr�sentant le nbr de points de vie.
     */
    public int getVie() {
        return vie;
    }

    /**
     * @return la valeur de �tat
     */
    public boolean getEtat() {
        return etat;
    }

    /**
     * @param etat la nouvelle valeur de �tat
     */
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    /**
     * Ajoute une touche au bateau et teste s'il est seulement "touch�" ou "coul�"
     * 
     * @return true s'il est coul� ou false s'il est simplement touch�
     */
    public boolean toucher() {
        /* n'est pas sur le point d'être d�truit */
        if (vie >= 2) {
            vie--;
        }
        /* vas être d�truit */
        else if (vie == 1 && getEtat()) {
            /*
             * on enlève sa dernière vie et on "l'�limine" en passant son �tat à false
             */
            vie--;
            setEtat(false);
        }
        return vie == 0;
    }
    
    /**
     * v�rifie si les coordonn�es arguments correspondent aux coordonn�es d'un
     * bateau
     * 
     * @param x char coordonn�e
     * @param y int coordonn�e
     * @param plateauJouer le plateau de jeu
     * @return indexBateau, l'index du bateau qui a �t� touch�, -1 si aucun bateau
     *         n'a �t� touch�.
     */
    public static int verifTir(int x, int y, Plateau plateauJouer) {
        Bateau bateauActuel = null;
        int indexBateau;
        indexBateau = plateauJouer.getGrille(x,y);
        
        return indexBateau;
    }

    /**
     * recherche si il reste dans bateau �tant encore en vie ( vie > 0 )
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
     * Renvoie une chaîne de caractère avec le nom et la taille de ce bateau
     */
    public String toString() {
        return "Nom : " + getNom() + ", Taille : " + getTaille() + "\n";
    }

}