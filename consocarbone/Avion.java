package consocarbone;

/**
    * <b> Cette classe hérite de la classe Transport,
    * elle implémente le poste de consommation carbone relatif à l'utilisation de l'avion</b>
    *
    * @author Gaétan De Castellane, Martin Youssef
    * @version 1.0
*/

public class Avion extends Transport {
    private int nbDeTrajet;
    private Longueur longueurCourrier;

    public Avion(){
        super();
        nbDeTrajet = 0;
        longueurCourrier = Longueur.Petit;
    }


    /**
        * le constructeur Avion implémente les différents attributs ainsi que son impact à un objet de type Avion
        *
        * @param nbDeTrajet le nombre de trajets effectués
        * @param longueurCourrier la type de trajet (Petit, Moyen, Long)
        * @since 1.0
    */
    public Avion(int nbDeTrajet,Longueur longueurCourrier){
        super();
        this.nbDeTrajet = nbDeTrajet;
        this.longueurCourrier = longueurCourrier;
        super.setImpact(this.calculImpact(nbDeTrajet, longueurCourrier));
    }

    /**
        * la méthode carboneMoyen affiche à l'écran l'émission moyenne de CO2 en kg/an vis-à-vis de l'utilisation de l'avion :
        *<pre>{@code public static void carboneMoyen(){
        System.out.println("Un français émet en moyenne ... en prenant l'avion }
        *}</pre>
        *
        * @since 1.0 
    */
    public static void carboneMoyen(){
        System.out.println("Un français émet en moyenne 480 kg/an de CO2 équivalent en prenant l'avion.");
    }


    /**
     * la méthode calculImpact calcule et retourne l'impact carbone en TCO2eq realtif à l'utilisation de l'avion de l'utilisateur
     * en faisant une disjonction de cas sur le type de trajet en verifiant d'abord que l'utilisateur
     * a effectué des trajets en avion
     * <pre>{@code  public double calculImpact(int nbDeTrajet,Longueur longueurCourrier){
        if(nbDeTrajet == 0){
            return 0;
        }
        switch(longueurCourrier){
            case Petit:
                return nbDeTrajet*500*0.252;
            case Moyen:
            return nbDeTrajet*1000*0.187;
            case Long:
                return nbDeTrajet*3000*0.152;
            default:
                return 0;
        }
    }
     * }</pre>
     * 
     * @param nbDeTrajet le nombre de trajets effectues
     * @param longueurCourrier la type de trajet (Petit, Moyen, Long)
     * @since 1.0
     * @return l'impact carbone relatif à l'utilisation de l'avion de l'utilisateur
     */
    public double calculImpact(int nbDeTrajet,Longueur longueurCourrier){
        if(nbDeTrajet == 0){
            return 0;
        }
        switch(longueurCourrier){
            case Petit:
                return nbDeTrajet*500*0.252; // 500 et 0.252 correspondent respectivement au nombre de km et à l'émission par km pour un petit courrier
            case Moyen:
            return nbDeTrajet*1000*0.187; // 1000 et 0.187 correspondent respectivement au nombre de km et à l'émission par km pour un moyen courrier
            case Long:
                return nbDeTrajet*3000*0.152;// 3000 et 0.152 correspondent respectivement au nombre de km et à l'émission par km pour un long courrier
            default:
                return 0;
        }
    }

    /**
        * {@inheritDoc}
    */
    @Override
    public String toString(){
        if (nbDeTrajet == 0)
            return "[ID = " + super.getID() + " ] " + "Pas de trajet en avion donc impact nul concernant les trajets en avion.";
        else{
            switch(longueurCourrier){
                case Petit:
                    return "[ID = " + super.getID() + " ] " + "trajet(s) petit courrier, au nombre de " + nbDeTrajet + ", la consommation est donc de " + super.getImpact() + " TCO2eq";
                case Moyen:
                    return "[ID = " + super.getID() + " ] " + "trajet(s) moyen courrier, au nombre de " + nbDeTrajet + ", la consommation est donc de " + super.getImpact() + " TCO2eq";
                case Long:
                return "[ID = " + super.getID() + " ] " + "trajet(s) long courrier, au nombre de " + nbDeTrajet + ", la consommation est donc de " + super.getImpact() + " TCO2eq";
                default:
                    return "";
            }
        }
    }
}