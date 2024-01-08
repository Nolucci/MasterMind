public class Couleur {
    private static char[] tabCouleurs;


    public static char[] getTabCouleurs(){
        return tabCouleurs;
    }

    public static int getNbCouleursTab(int[] tabCouleurs){
        return tabCouleurs.length;
    }

    public static int[] initTab(int nb, int val) {
        int[] tab = new int[nb]; // crée un tableau de nb valeur 0
        for (int i = 0; i < tab.length; i++) {
            tab[i] = val;
        }
        return tab;
    }

    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
        int[] tb = new int[codMot.length()];
        for (int i = 0; i < codMot.length(); i++) { // lettre par lettre
            boolean assigner = false;
            for (int j = 0; j < tabCouleurs.length && !assigner; j++) {
                if (codMot.charAt(i) == tabCouleurs[j]) { //cherche l'indice de la couleur codMot.charAt(i) dans tabCouleurs.
                    tb[i] = j; // j indice du tableau tabCouleurs.
                    assigner = true;
                }
            }
        }
        return tb;
    }

    public static String entiersVersMot(Code cod, char[] tabCouleurs) {
        String codMot = "";
        for (int i = 0; i < cod.getLgCode(); i++) {
            codMot += tabCouleurs[cod.getCodCode()[i]]; // Ajoute la lettre de tabCouleurs (car cod[i] = indice de la couleur) au String
        }
        return codMot;
    }

    public static void saisirCouleurs() {
        /*partie de saisie du nombre de couleur*/
        System.out.println("Saisir le nombre de couleurs.");
        int saisieNbC = UtMM.saisirEntierPositif();
        /*partie saisie des couleurs avec verification*/
        tabCouleurs = new char[saisieNbC];
        boolean correct;
        do {
            UtMM.clearConsole();
            correct = true;
            System.out.println("Donner l'initiale des couleurs (" + saisieNbC + ") sous la forme \"RBVJ\".");
            String couleurChoisie = UtMM.saisirChaine();
            /*verifie si la chaine de couleur est conforme*/
            if (couleurChoisie.length() == saisieNbC) {

                for (int i = 0; i < couleurChoisie.length() && correct; i++) {

                    if (UtMM.estPresent(tabCouleurs, couleurChoisie.charAt(i)) || couleurChoisie.charAt(i) == ' ') { // verifie si couleur déjà uilisée.
                        System.out.println( "ERREUR : " + couleurChoisie.charAt(i) + " a deja etait choisie ou est un espace.");
                        UtMM.pause(2000); //laisse le temps de lire le msg d'erreur avant le prochain clear.
                        correct = false;
                    } else {
                        tabCouleurs[i] = couleurChoisie.charAt(i);
                    }
                }
            } else {
                /*elle n'est donc pas conforme*/
                System.out.println("ERREUR : le nombre de couleur doit etre egal a " + saisieNbC + ".");
                UtMM.pause(2000);
                correct = false;
            }
        } while (!correct);

        UtMM.clearConsole();
    }
}
