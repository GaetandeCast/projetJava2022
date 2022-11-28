package consocarbone;

public abstract class ConsoCarbone implements Comparable<ConsoCarbone>{
    private double impact;
    private final int ID;
    private static int compteur = 0;

    public ConsoCarbone(double impact){
        ID = compteur;
        compteur++;
        this.impact = impact;
    }
    
    public ConsoCarbone(){
        compteur++;
        ID = compteur;
        impact = 0;
    }

    public void setImpact(double impact){
        this.impact=impact;
    }
    public double getImpact(){
        return impact;
    }

    public int getID(){
        return ID;
    }

    public abstract String toString();

    public int compareTo(ConsoCarbone c){
        if (this.getImpact()==c.getImpact()) return 0;
        if (this.getImpact()>c.getImpact()) return 1;
        return -1;
    }
}
