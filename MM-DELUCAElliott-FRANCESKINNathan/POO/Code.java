public class Code {
    private int[] cod;
    private int lgCode;

    public Code(int[] cod, int lgCode){
        this.cod=cod;
        this.lgCode=lgCode;
    }

    public int[] getCodCode(){
        return this.cod;
    }

    public int getLgCode(){
        return this.lgCode;
    }

    public void saisirLgCode(){
        this.lgCode = UtMM.saisirEntierPositif();
    }

    private static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {
        boolean correct = true;
        if (codMot.length() != lgCode) { //verifie si la longueur est n'est pas correcte.
            correct = false;
            System.out.println("La longueur du code doit etre de : " + lgCode);
        } else { //verifie si les caractères sont dans tabCouleurs.
            for (int i = 0; i < lgCode && correct; i++) {
                if (!UtMM.estPresent(tabCouleurs, codMot.charAt(i))) { // si la valeur est pas présente.
                    correct = false;
                    System.out.println("ERREUR : code couleur incorrect (" + codMot.charAt(i) + " n'entre pas dans la table de couleurs)");
                }
            }
        }
        return correct;
    }

    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
        boolean coupCorrect = false;
        String codeMot = "";
        while (!coupCorrect) {
            System.out.println("Donner un code pour la " + (nbCoups+1) + " fois");
            codeMot = UtMM.saisirChaine();
            if (codeCorrect(codeMot, lgCode, tabCouleurs)) {
                coupCorrect = true;
            }
        }
        int[] tabCodeMot = Couleur.motVersEntiers(codeMot, tabCouleurs);
        return tabCodeMot;
    }

    private static int nbBienPlaces(int[] cod1, int[] cod2) {
        int total = 0;
        for (int i = 0; i < cod1.length; i++) {
            if (cod1[i] == cod2[i]) {
                total++; // +1 si la valeur cod1[i] == cod2[i]
            }
        }
        return total;
    }

    private static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] nbCoulCod1 = tabFrequence(cod1, nbCouleurs);
        int[] nbCoulCod2 = tabFrequence(cod2, nbCouleurs);
        int total = 0;
        for (int i = 0; i < nbCouleurs; i++) {
            // Cherche la valeur minimum entre les 2 tableaux et l'ajoute au total (car c'est le nombre commun de cette valeur)
            total += Math.min(nbCoulCod1[i], nbCoulCod2[i]);
        }
        return total;
    }

    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] tb = new int[2];
        tb[0] = nbBienPlaces(cod1, cod2);
        tb[1] = nbCommuns(cod1, cod2, nbCouleurs) - tb[0];
        return tb;
    }

    private static int[] tabFrequence(int[] cod, int nbCouleurs) {
        int[] repet = new int[nbCouleurs];
        for (int i = 0; i < cod.length; i++) {
            repet[cod[i]]++; // cod[i] = indice de la couleur dans tabCouleurs et l'indice de repet = indice de la couleur. +1 par répétition.
        }
        return repet;
    }

    private static boolean repCorrecte(int[] rep, int lgCode) {
        if (rep[0] < 0 || rep[1] < 0) {
            System.out.println("Les valeurs ne peuvent pas etre inferieures à zero.");
            return false;
        }
        if (rep[0] + rep[1] > lgCode) {
            System.out.println("La somme des reponses ne peut etre superieure à la longueur du code.");
            return false;
        }
        return true;
    }

    public static int[] reponseHumain(int lgCode) {
        int[] rep = new int[2];
        do {
            System.out.print("Nombres de couleurs bien placees : ");
            rep[0] = UtMM.saisirEntier();
            System.out.print("Nombres de couleurs mal placees : ");
            rep[1] = UtMM.saisirEntier();
        } while (!repCorrecte(rep, lgCode));
        return rep;
    }

    private static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
        /* 
         * Exemple :
         * 5 couleurs donc base 5
         * (3, 2, 3, 4) ==> +1 =
         * (3, 2, 4, 0)
         * suite max = (4, 4, 4, 4) donc suite non realisable (4 = base-1)
         */

        for (int i = cod1.length - 1; i >= 0; i--) {
            cod1[i] = (cod1[i] + 1) % nbCouleurs; // augmente de 1 la valeur et 0 si egal a la base
            if (cod1[i] > 0) { // il n'y a pas de retenu donc fin
                return true;
            }
        }
        return false; // Si que des 0 alors non réalisable.
    }

    private static boolean estCompat(int[] cod1, Code[] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        
        /*
        Pour etre compatible cod1 doit avoir le meme nombre de bien et mal placé que les reponses "rep" des codes "cod" avec tous les codes "cod"
        C'est à dire :
        - cod1 doit avoir le meme nombre de bien placé car on veut conserver les biens placés.
        - il doit aussi avec le meme nombre de mal placé car les mal placé sont dans le code solution mais ne sont pas bien placé
        elle ne doivent donc pas etre placé au meme endroit (en gros : les mal placé mal placé sont donc possiblement bien placé).
        Exemple (couleur :{R,V,B}) : avec RRRR (1,0) et RBBB (1,1) le code "cod1" devra donc respecté les 2 donc RBBJ ne peut etre compatible et BRJJ sera compatible.
        */
        for (int i = 0; i < nbCoups; i++) {
            int[] codNbCoupsRep = nbBienMalPlaces(cod1, cod[i].getCodCode(), nbCouleurs);
            if (!UtMM.sontEgaux(codNbCoupsRep, rep[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, Code[] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean codeExiste = false;
        do {
            codeExiste = passeCodeSuivantLexico(cod1, nbCouleurs);
        } while (!estCompat(cod1, cod, rep, nbCoups, nbCouleurs) && codeExiste);
        return codeExiste;
    } 
}

