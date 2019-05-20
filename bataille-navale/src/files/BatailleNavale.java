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
     * Effectue le placement des bateaux dans la liste sur le plateau de jeu.
     * 
     * @param plateau le plateau sur lequel placer les bateaux
     * @throws Exception si il est impossible de placer les bateaux
     *                                  (ex: plateau trop petit)
     */
    public static void effectuerPlacement(Plateau plateau) throws Exception {
        Random random = new Random();
        int nbEssais;
        int coordBateauX;
        int coordBateauY;
        int sens; // 1 = haut/gauche; 2 = bas/droite
        int direction; // direction du bateau comprise entre 1 et 2
        // 1 = haut-bas; 2 = gauche-droite
        int nbBateau = plateauJeu.getFlotte().size();
        for (int i = 0; i < nbBateau; i++) {
            nbEssais = 0;
            do {
                // g�n�ration al�atoire des positions x et y des bateaux
                coordBateauX = random.nextInt(plateau.getDimX());
                coordBateauY = random.nextInt(plateau.getDimY());
                /* placement horizontal / vertical */
                direction = random.nextInt(2) + 1;
                // sens du bateau al�atoire
                sens = random.nextInt(2) + 1;
                nbEssais++;
            } while (!(placementButBetter(direction, sens, coordBateauX, coordBateauY, i)) || nbEssais == 5);
            if (nbEssais == 5) {
                throw new Exception("Placement du bateau immpossible");
            }
        }
        plateauJeu.afficherGrille(false);
    }

    /**
     * Effectue le placement des bateaux sur le plateau de jeu
     * 
     * @param direction
     * @param sens
     * @param coordX
     * @param coordY      l'ordonn�e du bateau � placer
     * @param indexBateau l'index du bateau dans la liste
     * @return true si le bateau a pu �tre plac�, false sinon
     */
    public static boolean placementButBetter(int direction, int sens, int coordX, int coordY, int indexBateau) {
        boolean placeLibre = false;
        
        if (direction == 1 && sens == 1) {
            for(int i = 0; i < plateauJeu.getFlotte().get(indexBateau).getTaille(); i++) {
                placeLibre = plateauJeu.verifierCoords(coordX - i, coordY);
            }
            if(placeLibre) {
                for (int i = 0; i < plateauJeu.getFlotte().get(indexBateau).getTaille(); i++) {
                    plateauJeu.setGrille(coordX - i, coordY, indexBateau);
                }
            }
        } else if (direction == 1 && sens == 2) {
            for(int i = 0; i < plateauJeu.getFlotte().get(indexBateau).getTaille(); i++) {
                placeLibre = plateauJeu.verifierCoords(coordX + i, coordY);
            }
            if(placeLibre) {
                for (int i = 0; i < plateauJeu.getFlotte().get(indexBateau).getTaille(); i++) {
                    plateauJeu.setGrille(coordX + i, coordY, indexBateau);
                }
            }
        } else if (direction == 2 && sens == 1) {
            for(int i = 0; i < plateauJeu.getFlotte().get(indexBateau).getTaille(); i++) {
                placeLibre = plateauJeu.verifierCoords(coordX, coordY + i );
            }
            if(placeLibre) {
                for (int i = 0; i < plateauJeu.getFlotte().get(indexBateau).getTaille(); i++) {
                    plateauJeu.setGrille(coordX, coordY + i, indexBateau);
                }
            }
        } else if (direction == 2 && sens == 2) {
            for(int i = 0; i < plateauJeu.getFlotte().get(indexBateau).getTaille(); i++) {
                placeLibre = plateauJeu.verifierCoords(coordX, coordY - i );
            }
            if(placeLibre) {
                for (int i = 0; i < plateauJeu.getFlotte().get(indexBateau).getTaille(); i++) {
                    plateauJeu.setGrille(coordX, coordY - i, indexBateau);
                }
            }
        }

        return false;

    }

    /**
     * TODO commenter le r�le de cette m�thode
     * 
     * @param direction
     * @param sens         le sens dans lequel est plac� le bateau (1 = haut ou
     *                     gauche; 2 = bas ou droite)
     * @param coordBateauX
     * @param coordBateauY
     * @param i
     * @return true si le bateau a pu �tre plac�
     */
    public static boolean placement(int direction, int sens, int coordBateauX, int coordBateauY, int i) {
        int directionBoucle = 0;
        Bateau bateauActuel;
        bateauActuel = plateauJeu.getFlotte().get(i);
        if (sens == 1 && (bateauActuel.getTaille() * direction + coordBateauX < 0
                || bateauActuel.getTaille() * direction + coordBateauX >= plateauJeu.getDimX())) {
            return false;
        }
        if (sens == 2 && (bateauActuel.getTaille() * direction + coordBateauY < 0
                || bateauActuel.getTaille() * direction + coordBateauY >= plateauJeu.getDimY())) {
            return false;
        }
        plateauJeu.setGrille(coordBateauX, coordBateauY, i);
        for (int index = 1; index < bateauActuel.getTaille(); index++) {
            if (sens == 1) {
                directionBoucle += direction;
                plateauJeu.setGrille(coordBateauX + directionBoucle, coordBateauY, i);
            } else if (sens == 2) {
                directionBoucle += direction;
                plateauJeu.setGrille(coordBateauX, coordBateauY + directionBoucle, i);
            }
        }
        return true;
    }

    /**
     * r�cup�ration des coordonn�es qu'entre le joueur et placement de celles-ci
     * dans deux variables x et y de type String.
     * 
     * @param plateauJeu
     */
    public static void recupCoord(Plateau plateauJeu) {
        String coordonnees;
        /* coordonn�es s�par�s en abscisse et ordonn�e */
        char x = '0';
        int y;
        String chaineY = null;
        /* permet de mettre la condition de chaine valide */
        boolean verification;

        System.out.print("\nEntrez les coordonn�s sous la forme : caract�re," + " nombre (ex : A, 11 ou A11)");
        coordonnees = entreeUtilisateur();
        if (coordonnees.equals("cheat")) {
            plateauJeu.afficherGrille(true);
            recupCoord(plateauJeu);
        }
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
            verification = (x <= plateauJeu.getDimX() + 'A' && x >= 'A') && (posY >= 0 && posY < plateauJeu.getDimY());
            if (verification) {
                System.out.println("x = " + x + "\ny = " + posY);
                tir(posX, posY);
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
    public static void tir(int x, int y) {
        int indexBateau;
        Bateau bateauActuel;
        indexBateau = Bateau.verifTir(x, y, plateauJeu);
        if (indexBateau >= 0) {
            bateauActuel = plateauJeu.getFlotte().get(indexBateau);
            plateauJeu.setGrille(x, y, -2);
            plateauJeu.afficherGrille(false);
            if (bateauActuel.toucher()) {
                System.out.println("Bateau : " + bateauActuel.getNom() + " coul� !");
            } else {
                System.out.println("touch� !");
            }
        } else {
            if (indexBateau == -2) {
                plateauJeu.setGrille(x, y, -2);
            } else {
                plateauJeu.setGrille(x, y, -3);
            }
            plateauJeu.afficherGrille(false);
            System.out.println("aucun bateau touch� :(");
        }

        if (Bateau.bateauRestant(plateauJeu)) {
            recupCoord(plateauJeu);
        } else {
            System.out.println("\n====>Partie termin�e ! tous les bateaux ont �t� coul�s !");
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

        /* cr�ation du plateau de jeu */
        plateauJeu = new Plateau();

        /* Liste des bateaux � placer sur le plateau */
        plateauJeu.setFlotte(new Bateau("Chocapic", 4));
        plateauJeu.setFlotte(new Bateau("sous-marineLePen", 3));
        plateauJeu.setFlotte(new Bateau());
        plateauJeu.setFlotte(new Bateau("aeroglisseur", 1));
        /* affichage des infos du plateau pour le joueur */
        System.out.println("\n" + plateauJeu.toString() + "\n");
        try {
            effectuerPlacement(plateauJeu);
        } catch (Exception e) {
            System.err.println("Impossible de placer les bateaux !");
        }

        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < plateauJeu.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, plateauJeu.getFlotte().get(aAfficher).toString());
        }
        recupCoord(plateauJeu);
    }

}
