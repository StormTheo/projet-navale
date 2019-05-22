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
        int nbBateau = plateauJeu.getFlotte().size();
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
        plateauJeu.afficherGrille(false);
    }

    /**
     * Effectue le placement des bateaux sur le plateau de jeu
     * 
     * @param direction
     * @param sens
     * @param coordX
     * @param coordY      l'ordonnée du bateau à placer
     * @param indexBateau l'index du bateau dans la liste
     * @return true si le bateau a pu être placé, false sinon
     */
    public static boolean placement(int direction, int sens, int coordX, int coordY, int indexBateau) {
        boolean placeLibre = true;
        
        int tailleBateau = plateauJeu.getFlotte().get(indexBateau).getTaille();

        if (direction == 1 && sens == 1) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && plateauJeu.verifierCoordsLibres(coordX, coordY - i);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    plateauJeu.setGrille(coordX, coordY - i, indexBateau);
                }
            }
        } else if (direction == 1 && sens == 2) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && plateauJeu.verifierCoordsLibres(coordX, coordY + i);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    plateauJeu.setGrille(coordX, coordY + i, indexBateau);
                }
            }
        } else if (direction == 2 && sens == 1) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && plateauJeu.verifierCoordsLibres(coordX - i, coordY);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    plateauJeu.setGrille(coordX - i, coordY, indexBateau);
                }
            }
        } else if (direction == 2 && sens == 2) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre =  placeLibre && plateauJeu.verifierCoordsLibres(coordX + i, coordY);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    plateauJeu.setGrille(coordX + i, coordY, indexBateau);
                }
            }
        }

        return placeLibre;

    }

    /**
     * récupération des coordonnées qu'entre le joueur et placement de celles-ci
     * dans deux variables x et y de type String.
     * 
     * @param plateauJeu
     */
    public static void recupCoord(Plateau plateauJeu) {
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
            plateauJeu.afficherGrille(true);
            recupCoord(plateauJeu);
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
            verification = (x <= plateauJeu.getDimX() + 'A' && x >= 'A') && (posY >= 0 && posY < plateauJeu.getDimY());
            if (verification) {
                System.out.println("x = " + x + "\ny = " + posY);
                tir(posX, posY);
            }
        }

        if (!verification) {
            System.out.println("Coordonnées incorrect, votre plateau a une dimension x = " + plateauJeu.getDimX()
                    + " y = " + plateauJeu.getDimY());
            recupCoord(plateauJeu);
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
        indexBateau = Bateau.verifTir(x, y, plateauJeu);
        if (indexBateau >= 0) {
            bateauActuel = plateauJeu.getFlotte().get(indexBateau);
            plateauJeu.setGrille(x, y, -2);
            plateauJeu.afficherGrille(false);
            if (bateauActuel.toucher()) {
                System.out.println("Bateau : " + bateauActuel.getNom() + " coulé !");
            } else {
                System.out.println("touché !");
            }
        } else {
            if (indexBateau == -2) {
                plateauJeu.setGrille(x, y, -2);
            } else {
                plateauJeu.setGrille(x, y, -3);
            }
            plateauJeu.afficherGrille(false);
            System.out.println("aucun bateau touché :(");
        }

        if (Bateau.bateauRestant(plateauJeu)) {
            recupCoord(plateauJeu);
        } else {
            System.out.println("\n====>Partie terminée ! tous les bateaux ont été coulés !");
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

        /* création du plateau de jeu */
        plateauJeu = new Plateau();

        /* Liste des bateaux à placer sur le plateau */
        plateauJeu.ajouterBateau(new Bateau("Chocapic", 4));
        plateauJeu.ajouterBateau(new Bateau("sous-marineLePen", 3));
        plateauJeu.ajouterBateau(new Bateau());
        plateauJeu.ajouterBateau(new Bateau("aeroglisseur", 1));
        plateauJeu.ajouterBateau(new Bateau("bernard", 3));
        plateauJeu.ajouterBateau(new Bateau("jean-marc", 2));
        plateauJeu.ajouterBateau(new Bateau("marie", 2));
        plateauJeu.ajouterBateau(new Bateau("jacqueline", 1));
        plateauJeu.ajouterBateau(new Bateau("miel", 1));
        plateauJeu.ajouterBateau(new Bateau("pops", 1));
        /* affichage des infos du plateau pour le joueur */
        System.out.println("\n" + plateauJeu.toString() + "\n");
        effectuerPlacement(plateauJeu);

        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < plateauJeu.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, plateauJeu.getFlotte().get(aAfficher).toString());
        }
        recupCoord(plateauJeu);
    }

}
