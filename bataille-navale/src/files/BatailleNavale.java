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

    /** Plateau de l'ia  */
    private static Plateau plateauIA;

    /** plateau du joueur */
    private static Plateau plateauJoueur;
    
    /** Indique si c'est au tour du joueur */
    private static boolean tourJoueur;


    /**
     * Affiche le Menu principal et en fonction du choix, renvoie à la méthode
     * appropriée
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
            System.out.println("Vous avez quitté le jeu.");
            System.exit(0);
            break;
        }
    }

    /**
     * Affiche l'aide et effectue l'action appropriée en fonction du choix de
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
            System.out.println("Vous avez quitté le jeu.");
            System.exit(0);
        }

        // else lance le jeu dans le main
    }

    /**
     * récupère la chaîne de caractères entrée par l'utilisateur
     * 
     * @return la chaîne de caractères que l'utilisateur a entrée
     */
    public static String entreeUtilisateur() {
        /*
         * scanner récupérant les choix et réponses de l'utilisateur
         */
        Scanner entree = new Scanner(System.in);

        String reponse;
        /* indicateur de saisie incorrecte */
        boolean nok;

        nok = false;
        do {
            /* raz de la réponse */
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
        final int MAX_ESSAIS = 20000;
        int coordBateauX;
        int coordBateauY;
        int direction; // direction du bateau comprise entre 1 et 2
        // 1 = haut-bas; 2 = gauche-droite
        int sens; // 1 = haut/gauche; 2 = bas/droite
        boolean resultatPlacement;
        int nbBateau = plateauIA.getFlotte().size();
        for (int i = 0; i < nbBateau; i++) {
            nbEssais = 0;
            do {
                // génération aléatoire des positions x et y des bateaux
                coordBateauX = random.nextInt(plateau.getDimX());
                coordBateauY = random.nextInt(plateau.getDimY());
                /* placement horizontal / vertical */
                direction = random.nextInt(2) + 1;
                // sens du bateau aléatoire
                sens = random.nextInt(2) + 1;
                resultatPlacement = placement(direction, sens, coordBateauX, coordBateauY, i);
                nbEssais++;
            } while (!resultatPlacement || nbEssais == MAX_ESSAIS);
        }
        plateauIA.afficherGrille(false);
    }

    /**
     * Effectue le placement des bateaux sur le plateau de jeu
     * 
     * @param direction   la direction dans laquel placer le bateau : H-B (1) ou G-D (2)
     * @param sens        le sens dans lequel est placé le bateau : 1 = H/G; 2 = B/D
     * @param coordX      l'abscisse du bateau à placer
     * @param coordY      l'ordonnée du bateau à placer
     * @param indexBateau l'index du bateau dans la liste
     * @return true si le bateau a pu être placé, false sinon
     */
    public static boolean placement(int direction, int sens, int coordX, int coordY, int indexBateau) {
        boolean placeLibre = true;
        
        int tailleBateau = plateauIA.getFlotte().get(indexBateau).getTaille();
        
        // Vers le haut
        if (direction == 1 && sens == 1) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && plateauIA.verifierCoordsLibres(coordX, coordY - i);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    plateauIA.setGrille(coordX, coordY - i, indexBateau);
                }
            }
        // Vers le bas
        } else if (direction == 1 && sens == 2) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && plateauIA.verifierCoordsLibres(coordX, coordY + i);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    plateauIA.setGrille(coordX, coordY + i, indexBateau);
                }
            }
        // Vers la gauche
        } else if (direction == 2 && sens == 1) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && plateauIA.verifierCoordsLibres(coordX - i, coordY);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    plateauIA.setGrille(coordX - i, coordY, indexBateau);
                }
            }
        // Vers la droite
        } else if (direction == 2 && sens == 2) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre =  placeLibre && plateauIA.verifierCoordsLibres(coordX + i, coordY);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    plateauIA.setGrille(coordX + i, coordY, indexBateau);
                }
            }
        }

        return placeLibre; // Si la place est libre, alors on a pu placer le bateau

    }

    /**
     * récupération des coordonnées qu'entre le joueur et placement de celles-ci
     * dans deux variables x et y de type String.
     * 
     * @param plateau
     */
    public static void recupCoord(Plateau plateau) {
        String coordonnees;
        /* coordonnées séparés en abscisse et ordonnée */
        char x = '0';
        int y;
        String chaineY = null;
        /* permet de mettre la condition de chaîne valide */
        boolean verification;

        System.out.print("\nEntrez les coordonnés sous la forme : caractère," + " nombre (ex : A, 11 ou A11)");
        coordonnees = entreeUtilisateur();
        if (coordonnees.equals("cheat")) {
            plateau.afficherGrille(true);
            recupCoord(plateau);
        }
        for (int placement = 0; placement < coordonnees.length(); placement++) {
            /* on cherche le caractère minuscule ou majuscule */
            if ((coordonnees.charAt(placement) >= 'a' && coordonnees.charAt(placement) <= 'z')
                    || (coordonnees.charAt(placement) >= 'A' && coordonnees.charAt(placement) <= 'Z')) {

                /* x = morceau de chaîne avec le caractère */
                x = coordonnees.charAt(placement);
                /* mise en majuscule du caractère */
                x = (char) (x >= 'a' ? (x - 32) : x);
            }
            if (coordonnees.charAt(placement) >= '0' && coordonnees.charAt(placement) <= '9') {
                /* y = valeur numérique */
                if (chaineY == null) {
                    chaineY = coordonnees.substring(placement, placement + 1);
                } else {
                    chaineY += coordonnees.substring(placement, placement + 1);
                }
            }
        }

        verification = (x != '0' && chaineY != null);
        if (verification) {
            /* les positions en nombre pour vérifier dans le plateau */
            int posX = x - 'A';
            int posY = Integer.parseInt(chaineY);

            /* Si x et y sont existant dans le plateau. */
            verification = (x <= plateau.getDimX() + 'A' && x >= 'A') && (posY >= 0 && posY < plateau.getDimY());
            if (verification) {
                System.out.println("x = " + x + "\ny = " + posY);
                tir(posX, posY);
            }
        }

        if (!verification) {
            System.out.println("Coordonnées incorrect, votre plateau a une dimension x = " + plateau.getDimX()
                    + " y = " + plateau.getDimY());
            recupCoord(plateau);
        }
    }

    /**
     * Lance la vérification des coordonnées entrées, et affiche le résultat du tir.
     * 
     * @param x coordonnée entrée par l'utilisateur pour l'abscisse
     * @param y coordonnée entrée par l'utilisateur pour l'ordonnée
     * 
     */
    public static void tir(int x, int y) {
        int indexBateau;
        Bateau bateauActuel;
        indexBateau = Bateau.verifTir(x, y, plateau);
        if (indexBateau >= 0) {
            bateauActuel = plateau.getFlotte().get(indexBateau);
            plateau.setGrille(x, y, -2);
            plateau.afficherGrille(false);
            if (bateauActuel.toucher()) {
                System.out.println("Bateau : " + bateauActuel.getNom() + " coulé !");
            } else {
                System.out.println("touché !");
            }
        } else {
            if (indexBateau == -2) {
                plateau.setGrille(x, y, -2);
            } else {
                plateau.setGrille(x, y, -3);
            }
            plateau.afficherGrille(false);
            System.out.println("aucun bateau touché :(");
        }

        if (Bateau.bateauRestant(plateau) && tourJoueur) {
            tourJoueur = false;
            recupCoord(plateauIA);
        } else if (Bateau.bateauRestant(plateau) && !tourJoueur) {
            /* Si c'est au tour de l'IA */
            tourJoueur = true;
            Random random = new Random();
            do {
                x = random.nextInt(plateau.getDimX());
                y = random.nextInt(plateau.getDimY());
            } while (   plateauJoueur.getGrille(x, y) == -2
                     || plateauJoueur.getGrille(x, y) == -3);
            System.out.println("x : " + x + ", y : " + y);
            tir(x, y, plateauJoueur);
        } else {
            System.out.println("\n====>Partie terminée ! tous les bateaux ont été coulés !");
            System.exit(0);
        }
    }

    /**
     * Lancement des principales fonctions et créations des objets
     * 
     * @param args non utilisé
     *
     */
    public static void main(String[] args) {
        menu();

        /* création des plateau de jeu */
        plateauIA = new Plateau();

        /* Liste des bateaux à placer sur le plateau de l'ia */
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

        /* Liste des bateaux à placer sur le plateau de l'ia */
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

        System.out.println("Bateaux présents sur le plateau du joueur");
        
        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < plateauJoueur.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, plateauJoueur.getFlotte().get(aAfficher).toString());
        }

        /* affichage des infos du plateau de l'IA */
        System.out.println("\n" + plateauIA.toString() + "\n");
        placement(plateauIA);
        System.out.println("Bateaux présents sur le plateau de l'IA");


        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < plateauIA.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, plateauIA.getFlotte().get(aAfficher).toString());
        }
        recupCoord(plateauIA);
    }

}