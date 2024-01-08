public class Plateau {
    private static int nbEssaisMax; // >= 0
    private Code[] cod;
    private int[][] rep;
    private int nbCoups; // >= 0

    public Plateau(Code[] cod, int[][] rep, int nbCoups){
        this.cod = cod;
        this.rep = rep;
        this.nbCoups = nbCoups;
    }

    public void ajoutCodeStock(Code code){
        this.cod[this.nbCoups] = code;
    }

    public void ajoutRepStock(int[] reponse){
        this.rep[this.nbCoups] = reponse;
    }

    public void ajoutNbCoups(){
        this.nbCoups++;
    }

    public Code[] getCodPlateau(){
        return this.cod;
    }

    public int[][] getRepPlateau() {
        return this.rep;
    }

    public static int getNbEssaisMax(){
        return nbEssaisMax;
    }

    public static void initNbEssaisMax() {
        do{
            System.out.println("Nombre d'essais max ?");
            nbEssaisMax=UtMM.saisirEntier();
            if(nbEssaisMax<0){
                UtMM.clearConsole();
                System.out.println("Vous devez mettre un nombre positif ou nul.");
            }
        }while(nbEssaisMax<=0);
    }
}
