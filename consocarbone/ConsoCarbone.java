package consocarbone;

/** 
    * <b> Les classes du package consocarbone hériteront de cette classe abstraite et representeront des postes de consommation carbone </b>
    * la méthode abstraite est toString(), elle sera redéfinie dans chacune des classes filles
    *
    * @author Gaétan De Castellane, Martin Youssef
    * @version 1.0
    * @see #toString()
*/

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

    /**
     * la méthode toString indique les détails de l'instance (poste de consommation) ainsi que son impact carbone sous forme d'une chaîne de caractères.
     * 
     * @return une chaîne de caractères contenant les détails du poste de consommation ainsi que son impact carbone
     * @since 1.0
     */
    public abstract String toString();

    public int compareTo(ConsoCarbone c){
        if (this.getImpact()==c.getImpact()) return 0;
        if (this.getImpact()>c.getImpact()) return 1;
        return -1;
    }
}
