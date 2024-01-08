public class Partie {
    private int nbManches; // pair > 0
    private int numManche; // de 1 à nbManches
    private int scoreJoueur; // >= 0
    private int scoreOrdi; // >= 0

    public Partie(int nbManches, int numManche,int scoreJoueur,int scoreOrdi){
        this.nbManches = nbManches;
        this.numManche = numManche;
        this.scoreJoueur = scoreJoueur;
        this.scoreOrdi = scoreOrdi;
    }

    public int getScoreJoueur(){
        return this.scoreJoueur;
    }

    public int getScoreOrdi(){
        return this.scoreOrdi;
    }

    public void joue(int lgCode){
        while(this.numManche<= this.nbManches){

            UtMM.clearConsole();
            System.out.println("DEBUT DE LA MANCHE NUMERO : " + numManche);
            System.out.println("Couleurs disponibles : " + UtMM.listElem(Couleur.getTabCouleurs()));
            UtMM.clearConsole();
            int[][] rep = new int[Plateau.getNbEssaisMax()][2];

            Code[] nouvCodes = new Code[Plateau.getNbEssaisMax()];
            Plateau nouvPlateau = new Plateau(nouvCodes,rep,0);
            
            if(this.numManche % 2 == 0){ 
                MancheOrdinateur nouvMancheOrdi = new MancheOrdinateur(nouvPlateau);
                this.scoreOrdi += nouvMancheOrdi.joue(lgCode,Couleur.getTabCouleurs(),numManche,Plateau.getNbEssaisMax());

                System.out.println("Score des manches 1 à "+ numManche +" : " + scoreOrdi);
            } else {
                MancheHumain nouvMancheHumain = new MancheHumain(nouvPlateau);
                this.scoreJoueur += nouvMancheHumain.joue(lgCode,Couleur.getTabCouleurs(),Plateau.getNbEssaisMax());
                
                System.out.println("Score des manches 1 à"+ numManche +" : " + scoreJoueur);
            }
            this.numManche++;
            System.out.println("Touche entrer pour continuer");
        }
        
    }

}
