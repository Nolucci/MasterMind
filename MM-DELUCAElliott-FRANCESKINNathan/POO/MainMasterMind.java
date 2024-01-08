public class MainMasterMind {
    public static void main(String[] args) {
        UtMM.clearConsole();

        //initier TabCouleurs
        Couleur.saisirCouleurs();

        System.out.println("Le code contiendra combien de caracteres ?");
        int lgCode = UtMM.saisirEntierPositif();
        UtMM.clearConsole();

        System.out.println("Combien y aura t'il de manches ?");
        int nbManches = UtMM.saisirEntierPairPositif();
        UtMM.clearConsole();

        //initier nbEssaisMax
        Plateau.initNbEssaisMax();
        UtMM.clearConsole();
        

        //créer un objet Partie et lancer la partie
        Partie nouvPartie = new Partie(nbManches, 1, 0,0);
        nouvPartie.joue(lgCode);

        UtMM.clearConsole();
        System.out.println("FIN DE LA PARTIE");
        System.out.println("Score total Joueur : " + nouvPartie.getScoreJoueur());
        System.out.println("Score total Ordi : " + nouvPartie.getScoreOrdi());

    } // fin main
}
