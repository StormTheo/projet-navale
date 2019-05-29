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
import files.Direction;

/**
 * Classe principale du jeu
 * 
 * @author IUT INFO1 groupe 1
 *
 */
public class BatailleNavale {

    /** Plateau de l'ia */
    private static Plateau plateauIA;

    /** plateau du joueur */
    private static Plateau plateauJoueur;

    /** Indique si c'est au tour du joueur */
    private static boolean tourJoueur;

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
            System.out.println("Vous avez quitt� le jeu.");
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
            System.out.println("Vous avez quitt� le jeu.");
            System.exit(0);
        }

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
     * Effectue le placement des bateaux dans la liste sur le plateau de jeu.
     * 
     * @param plateau le plateau sur lequel placer les bateaux
     */
    public static void effectuerPlacement(Plateau plateau) {
        Random random = new Random();
        int nbEssais;
        final int MAX_ESSAIS = 8;
        int coordBateauX;
        int coordBateauY;
        int[] placementJoueur;
        Direction directionJoueur = new Direction();
        int direction = -1; // direction du bateau comprise entre 1 et 2
        // 1 = haut-bas; 2 = gauche-droite
        int sens; // 1 = haut/gauche; 2 = bas/droite
        boolean resultatPlacement;
        int nbBateau = plateau.getFlotte().size();
        for (int i = 0; i < nbBateau; i++) {
            nbEssais = 0;
            do {
                /* placement par le joueur de ses bateaux */
                if (plateau == plateauJoueur) {
                    System.out.println("Placement du bateau : " + plateau.getFlotte().get(i).getNom() + " de taille : "
                            + plateau.getFlotte().get(i).getTaille());
                    /* entr�e de la ligne et colonne par le joueur */
                    do {
                        placementJoueur = recupCoord(plateau, true);
                    } while (placementJoueur[0] == -1);
                    coordBateauX = placementJoueur[0];
                    coordBateauY = placementJoueur[1];
                    /*
                     * entr�e de la direction ( n/s/o/e) par le joueur et attribution des valeurs
                     * correspondantes aux bonnes variables pour le placement
                     */
                    directionJoueur.directionChoisie(directionJoueur());
                    direction = directionJoueur.getDirection();
                    sens = directionJoueur.getSens();
                } else { // placement des bateaux de l'IA automatiquement
                         // g�n�ration al�atoire des positions x et y des bateaux
                    coordBateauX = random.nextInt(plateau.getDimX());
                    coordBateauY = random.nextInt(plateau.getDimY());
                    /* placement horizontal / vertical */
                    direction = random.nextInt(2) + 1;
                    // sens du bateau al�atoire
                    sens = random.nextInt(2) + 1;
                }
                resultatPlacement = plateau.placement(direction, sens, coordBateauX, coordBateauY, i);
                nbEssais++;
            } while (!resultatPlacement || nbEssais == MAX_ESSAIS || direction == -1);
            if (plateau == plateauJoueur) {
                plateau.afficherGrille(true);
            }
        }
        System.out.println("D�but de la partie...");
        plateau.afficherGrille(false);
    }
    
    // TODO rajouter une m�thode de sauvegarde
    
    // TODO rajouter une m�thode de chargement

    /**
     * V�rifie si la direction entr�e par le joueur est valide et renvoie la
     * direction entr�e
     * 
     * @return la direction entr�e pas le joueur
     */
    public static String directionJoueur() {
        String direction;
        char[] dirPossible = { 'o', 'O', 'n', 'N', 'e', 'E', 's', 'S' };
        boolean valide = false;
        do {
            System.out.println("Veuillez entrer la direction (n,s,o,e)");
            direction = entreeUtilisateur();
            for (int index = 0; !valide && index < dirPossible.length; index++) {
                valide = direction.charAt(0) == dirPossible[index];
            }
            if (!valide) {
                System.out.println("entr�e incorrect");
            }
        } while (!valide);
        return direction;
    }

    /**
     * R�cup�ration des coordonn�es qu'entre le joueur et placement de celles-ci
     * dans deux variables x et y de type String.
     * 
     * @param isPlacement permet de r�cup�rer les coordonn�es lorsqu'on place un
     *                    bateau
     * @param plateau
     * @return Le couple de coordonn�es entr�es par le joueur
     */
    public static int[] recupCoord(Plateau plateau, boolean isPlacement) {
        String coordonnees;
        int[] TablePlacement = new int[2];
        /* coordonn�es s�par�s en abscisse et ordonn�e */
        char x = '0';
        String chaineY = null;
        /* permet de mettre la condition de cha�ne valide */
        boolean verification;

        System.out.print("\nEntrez les coordonn�s sous la forme : caract�re," + " nombre (ex : A, 11 ou A11)");
        coordonnees = entreeUtilisateur();
        if (coordonnees.equals("cheat")) {
            plateau.afficherGrille(true);
            recupCoord(plateau, false);
        }
        
        // TODO rajouter un else if pour la sauvegarde
        
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
            /* les positions en nombre pour v�rifier dans le plateau */
            int posX = x - 'A';
            int posY = Integer.parseInt(chaineY);

            /* Si x et y sont existant dans le plateau. */
            verification = (x <= plateau.getDimX() + 'A' && x >= 'A') && (posY >= 0 && posY < plateau.getDimY());
            if (verification && isPlacement) {
                TablePlacement[0] = posX;
                TablePlacement[1] = posY;
            } else if (verification && !isPlacement) {
                System.out.println("Colonne = " + x + "\nLigne = " + posY);
                tir(posX, posY, plateau);
            }
        }

        if (!verification) {
            System.out.println("Coordonn�es incorrect, votre plateau a une dimension x = " + plateau.getDimX() + " y = "
                    + plateau.getDimY());
            /* recommence la saisie des coordonn�es pour le placement */
            if (isPlacement) {
                TablePlacement[0] = -1;
                TablePlacement[1] = -1;
            } else {
                recupCoord(plateau, false);
            }
        }
        return TablePlacement;

    }

    /**
     * Permet de cr�er une pause apr�s que l'utilisateur ait jou�.
     */
    private static void continuer() {
        Scanner entree = new Scanner(System.in);
        System.out.println("Appuyez sur entr�e pour continuer...");
        entree.nextLine();
    }

    /**
     * Lance la v�rification des coordonn�es entr�es, et affiche le r�sultat du tir.
     * 
     * @param x       coordonn�e entr�e par l'utilisateur pour l'abscisse
     * @param y       coordonn�e entr�e par l'utilisateur pour l'ordonn�e
     * @param plateau le plateau sur lequel on tire
     * 
     */
    public static void tir(int x, int y, Plateau plateau) {
        int indexBateau;
        Bateau bateauActuel;
        indexBateau = Bateau.verifTir(x, y, plateau);
        if (indexBateau >= 0) {
            bateauActuel = plateau.getFlotte().get(indexBateau);
            plateau.setGrille(x, y, -2);
            plateau.afficherGrille(false);
            if (bateauActuel.toucher()) {
                System.out.println("Bateau : " + bateauActuel.getNom() + " coul� !");
            } else {
                System.out.println("touch� !");
            }
        } else {
            if (indexBateau == -2) {
                plateau.setGrille(x, y, -2);
            } else {
                plateau.setGrille(x, y, -3);
            }
            plateau.afficherGrille(false);
            System.out.println("aucun bateau touch� :(");
        }
        /* avant que ce ne soit l'IA qui tire on marque une pause */
        if (!tourJoueur) {
            continuer();
        }
        if (Bateau.bateauRestant(plateau) && tourJoueur) {
            tourJoueur = false;
            recupCoord(plateauIA, false);
        } else if (Bateau.bateauRestant(plateau) && !tourJoueur) {
            /* Si c'est au tour de l'IA */
            System.out.println("\nTour de l'ordinateur ... \n");
            tourJoueur = true;
            Random random = new Random();
            do {
                x = random.nextInt(plateau.getDimX());
                y = random.nextInt(plateau.getDimY());
            } while (plateauJoueur.getGrille(x, y) == -2 || plateauJoueur.getGrille(x, y) == -3);
            System.out.println("Colonne = " + x + "\nLigne = " + y);
            tir(x, y, plateauJoueur);
        } else {
            System.out.println("\n====>Partie termin�e ! tous les bateaux ont �t� coul�s !");
            System.exit(0);
        }
    }

    /**
     * Lancement des principales fonctions et cr�ations des objets
     * 
     * @param args non utilis�
     *
     */
    public static void main(String[] args) {
        menu();

        /* cr�ation des plateau de jeu */
        plateauIA = new Plateau();
        plateauJoueur = new Plateau();

        /* Liste des bateaux � placer sur le plateau de l'ia */
        plateauIA.ajouterBateau(new Bateau("Chocapic", 4));
        plateauIA.ajouterBateau(new Bateau("sous-marineLePen", 3));
        plateauIA.ajouterBateau(new Bateau());
        plateauIA.ajouterBateau(new Bateau("aeroglisseur", 1));
        plateauIA.ajouterBateau(new Bateau("bernard", 3));
        plateauIA.ajouterBateau(new Bateau("jean-marc", 2));
        plateauIA.ajouterBateau(new Bateau("marie", 2));
        plateauIA.ajouterBateau(new Bateau("jacqueline", 1));
        plateauIA.ajouterBateau(new Bateau("miel", 1));
        plateauIA.ajouterBateau(new Bateau("pops", 1));

        /* Liste des bateaux � placer sur le plateau de l'ia */
        plateauJoueur.ajouterBateau(new Bateau("Chocapic", 4));
        plateauJoueur.ajouterBateau(new Bateau("sous-marineLePen", 3));
        plateauJoueur.ajouterBateau(new Bateau());
        plateauJoueur.ajouterBateau(new Bateau("aeroglisseur", 1));
        plateauJoueur.ajouterBateau(new Bateau("bernard", 3));
        plateauJoueur.ajouterBateau(new Bateau("jean-marc", 2));
        plateauJoueur.ajouterBateau(new Bateau("marie", 2));
        plateauJoueur.ajouterBateau(new Bateau("jacqueline", 1));
        plateauJoueur.ajouterBateau(new Bateau("miel", 1));
        plateauJoueur.ajouterBateau(new Bateau("pops", 1));

        /* affichage des infos du plateau pour le joueur */
        System.out.println("\n" + plateauJoueur.toString() + "\n");
        effectuerPlacement(plateauJoueur);

        System.out.println("Bateaux pr�sents sur le plateau du joueur");

        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < plateauJoueur.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, plateauJoueur.getFlotte().get(aAfficher).toString());
        }

        /* affichage des infos du plateau de l'IA */
        System.out.println("\n" + plateauIA.toString() + "\n");
        effectuerPlacement(plateauIA);
        System.out.println("Bateaux pr�sents sur le plateau de l'IA");

        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < plateauIA.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, plateauIA.getFlotte().get(aAfficher).toString());
        }
        recupCoord(plateauIA, false);
    }

}