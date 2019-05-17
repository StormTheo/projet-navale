/*
 * BatailleNavale.java                                                   1 mai 2019
 * IUT info1 2018-2019 groupe 1, pas de droits : ni copyright ni copyleft
 */

package files;

import java.util.Random;
import java.util.Scanner;

import files.Affichage;
import files.Bateau;
import files.Plateau;

/**
 * Classe principale du jeu
 * 
 * @author IUT INFO1 groupe 1
 *
 */
public class BatailleNavale {

    /** Plateau du joueur */
    private static Plateau plateauJeu;

    /**
     * Affiche le Menu principal et en fonction du choix, renvoie � la m�thode
     * appropri�e
     */
    public static void menu() {

        boolean nok;
        String reponse;
        char reponseChar;

        Affichage.menuPrincipal();
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while (!nok);
        reponseChar = reponse.charAt(0);
        reponseChar = (char) (reponseChar >= 'a' ? reponseChar - 32 : reponseChar);
        switch (reponseChar) {
        case 'A':
            aide();
            break;
        case 'Q':
            System.out.println("Vous avez quitter le jeu.");
            System.exit(0);
            break;
        }
    }

    /**
     * Affiche l'aide et effectue l'action appropri�e en fonction du choix de
     * l'utilisateur
     */
    public static void aide() {
        String reponse;
        boolean nok;
        char reponseChar;
        Affichage.afficherAide();
        /* saisie du choix de l'utilisateur */
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while (!nok);
        reponseChar = reponse.charAt(0);
        reponseChar = (char) (reponseChar >= 'a' ? reponseChar - 32 : reponseChar);
        if (reponseChar == 'A') {
            aide();
        } else if (reponseChar == 'Q') {
            System.out.println("Vous avez quitter le jeu.");
            System.exit(0);
        }

        // else lance le jeu dans le main
    }

    /**
     * r�cup�re la cha�ne de caract�res entr�e par l'utilisateur
     * 
     * @return la cha�ne de caract�res que l'utilisateur a entr�e
     */
    public static String entreeUtilisateur() {
        /*
         * scanner r�cup�rant les choix et r�ponses de l'utilisateur
         */
        Scanner entree = new Scanner(System.in);

        String reponse;
        /* indicateur de saisie incorrecte */
        boolean nok;

        nok = false;
        do {
            /* raz de la r�ponse */
            reponse = "";
            System.out.print("\n       ====> ");

            if (entree.hasNext()) {
                reponse = entree.nextLine();
                nok = true;
            }
        } while (!nok);
        return reponse;
    }

    /**
     * Effectue le placement d'un nombre donn� de bateaux de taille al�atoire
     * (comprise entre 1 et 4) sur le plateau de jeu.
     * 
     * @param plateau le plateau sur lequel placer les bateaux
     * @throws IllegalArgumentException si il est impossible de placer les bateaux
     *                                  (ex: plateau trop petit)
     */
    public static void placement(Plateau plateau) throws IllegalArgumentException {
        Random random = new Random();
        /*
         * chaine comprenant les coordonn�es possibles choix d'un au hasard pour placer
         * un bateau
         */
        String coordPossible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char coordBateauX;
        int nbrRandX; // sauvegarde du nombre affect� � une lettre
        int coordBateauY;
        int alignement;
        int direction; // direction du bateau comprise entre 1 et 4
        // 1 = haut; 2 = gauche; 3 = bas; 4 = droite
        int nbBateau = Plateau.getFlotte().size();
        for (int i = 0; i < nbBateau; i++) {
            // g�n�ration al�atoire des positions x et y des bateaux
            nbrRandX = random.nextInt(plateau.getDimX());
            coordBateauX = coordPossible.charAt(nbrRandX);
            System.out.println("X = " + coordBateauX); // DEBUG
            coordBateauY = random.nextInt(plateau.getDimY()) + 1;
            System.out.println("Y = " + coordBateauY); // DEBUG
            alignement = random.nextInt(2)+1;
            // direction du bateau al�atoire
            direction = random.nextInt(2)*2-1;
            
            System.out.println("direction : " + direction 
            					+ "\nalignement : " + alignement); // debug
            System.out.println();
            placement(direction, alignement, coordBateauX, coordBateauY, i);
            // placer le bateau i; PENSER A LA DIRECTION !
            Plateau.getFlotte().get(i).afficherPositions();
        }
        
    }
    
    public static void placement(int direction, int alignement,
    							char coordBateauX, int coordBateauY, int i) {
    	int directionBoucle = 0;
    	Bateau bateauActuel;
    	bateauActuel = Plateau.getFlotte().get(i);
    	bateauActuel.setPositionHorizontale(coordBateauX);
    	bateauActuel.setPositionVerticale(coordBateauY);
    	for (int index = 1; index < bateauActuel.getTaille(); index++) {
    		if(alignement == 1) {
    			directionBoucle += direction;
    			bateauActuel.setPositionHorizontale((char) (coordBateauX+directionBoucle));
    			bateauActuel.setPositionVerticale(coordBateauY);
    		} else if (alignement == 2) {
    			directionBoucle+=direction;
    			bateauActuel.setPositionHorizontale(coordBateauX);
    			bateauActuel.setPositionVerticale(coordBateauY+directionBoucle);
    		}
    	}
    }

    /**
     * r�cup�ration des coordonn�es qu'entre le joueur et placement de celles-ci
     * dans deux variables x et y de type String.
     * 
     * @param plateauJeu
     */
    public static void recupCoord(Plateau plateauJeu) {
        String coordonnees;
        /*
         * coordonn�es s�par�s en abscisse et ordonn�e
         */
        char x = '0';
        int y;
        String chaineY = null;
        /* permet de mettre la condition de chaine valide */
        boolean verification;
        System.out.print("\nEntrez les coordonn�s sous la forme : caract�re," + " nombre (ex : A, 11 ou A11)");
        coordonnees = entreeUtilisateur();
        for (int placement = 0; placement < coordonnees.length(); placement++) {
            /* on cherche le caract�re minuscule ou majuscule */
            if ((coordonnees.charAt(placement) >= 'a' && coordonnees.charAt(placement) <= 'z')
                    || (coordonnees.charAt(placement) >= 'A' && coordonnees.charAt(placement) <= 'Z')) {

                /* x = morceau de cha�ne avec le caract�re */
                x = coordonnees.charAt(placement);
                /* mise en majuscule du caract�re */
                x = (char) (x >= 'a' ? (x - 32) : x);
            }
            if (coordonnees.charAt(placement) >= '0' && coordonnees.charAt(placement) <= '9') {
                /* y = valeur num�rique */
                if (chaineY == null) {
                    chaineY = coordonnees.substring(placement, placement + 1);
                } else {
                    chaineY += coordonnees.substring(placement, placement + 1);
                }
            }
        }
        verification = (x != '0' && chaineY != null);
        if (verification) {
            y = Integer.parseInt(chaineY); // passage de la valeur en String.
            /* Si x et y sont existant dans le plateau. */
            verification = (x <= plateauJeu.getDimX() + 'A' && x >= 'A') && (y > 0 && y <= plateauJeu.getDimY());
            if (verification) {
                System.out.println("x = " + x + "\ny = " + y);
                tir(x, y);
            }
        }
        if (!verification) {
            System.out.println("Coordonn�es incorrect, votre plateau a une dimension x = " + plateauJeu.getDimX()
                    + " y = " + plateauJeu.getDimY());
            recupCoord(plateauJeu);
        }
    }

    /**
     * Lance la v�rification des coordonn�es entr�es, et affiche le r�sultat du tir.
     * 
     * @param x coordonn�e entr�e par l'utilisateur pour l'abscisse
     * @param y coordonn�e entr�e par l'utilisateur pour l'ordonn�e
     * 
     */
    public static void tir(char x, int y) {
        int indexBateau;
        Bateau bateauActuel;
        indexBateau = Bateau.verifTir(x, y);

        if (indexBateau >= 0) {
            bateauActuel = Plateau.getFlotte().get(indexBateau);
            if (bateauActuel.toucher()) {
                System.out.println("Bateau : " + bateauActuel.getNom() + " coul� !");
            } else {
                System.out.println("touch� !");
            }
        } else {
            System.out.println("aucun bateau touch� :(");
        }
        if (Bateau.bateauRestant()) {
            recupCoord(plateauJeu);
        } else {
            System.out.println("\n====>Partie termin�e ! tous les bateaux ont �t� coul�s !");
        }
    }

    /**
     * Lancement des principales fonctions et cr�ations des objets
     * 
     * @param args non utilis�
     */
    public static void main(String[] args) {
        menu();

        /* cr�ation du plateau de jeu */
        plateauJeu = new Plateau();

        /* Liste des bateaux � placer sur le plateau */
        new Bateau("Chocapic", 4);
        new Bateau("sous-marineLePen", 3);
        new Bateau();
        new Bateau("aeroglisseur", 1);
        /* affichage des infos du plateau pour le joueur */
        System.out.println("\n" + plateauJeu.toString() + "\n");
        placement(plateauJeu);

        // TODO placer les bateaux

        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < Plateau.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, Plateau.getFlotte().get(aAfficher).toString());
        }
        // TODO afficher informations de la partie en cours avant
        // la demande des coordonn�es -> m�thode.
        recupCoord(plateauJeu);
    }

}
