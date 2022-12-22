package consocarbone;

/**
    * <b> Cette classe implémente le poste de consommation carbone relatif aux transports </b>
    *
    * @author Gaétan De Castellane, Martin Youssef
    * @version 1.0
*/

public class Transport extends ConsoCarbone{
    private boolean possede;
    private Taille taille;
    private int kilomAnnee;
    private int amortissement;

    public Transport(){
        super();
        possede = false;
        taille = Taille.P;
        kilomAnnee = 0;
        amortissement = 0;
    }

    /**
        * le constructeur Transport implémente les différents attributs ainsi que son impact à un objet de type Transport
        *
        * @param possede indique si l'utilisateur possède ou ne possède pas de moyen de transport 
        * @param taille indique la taille du véhicule, G pour gros et P pour petit
        * @param kilomAnnee nombre de kilomètre parcourus par an
        * @param amortissement durée de conservation du véhicule
        * @since 1.0
        */
    public Transport(boolean possede, Taille taille, int kilomAnnee, int amortissement){
        super();
        this.possede = possede;
        this.taille = taille;
        this.kilomAnnee = kilomAnnee;
        this.amortissement = amortissement;
        super.setImpact(this.calculImpact(possede, taille, kilomAnnee, amortissement));
    }

    /**
        * la méthode carboneMoyen affiche à l'écran l'émission moyenne de CO2 en kg/an vis-à-vis de différents secteurs <br>
        *<pre>{@code public static void carboneMoyen(){
        System.out.println("Un français émet en moyenne... }
        *}</pre>
        *
        * @since 1.0 
    */
    public static void carboneMoyen(){
        System.out.println("Un français émet en moyenne 1972 kg/an de CO2 equivalent en voiture, 480 kg/an en avion, 383 kg/an en fret et messagerie et 85 kg/an en Train et bus.");
    }

    public boolean getPossede(){
        return possede;
    }
    public void setPossede(boolean possede){
        this.possede = possede;
        super.setImpact(calculImpact(possede, taille, kilomAnnee, amortissement));
    }    

    public Taille getTaille(){
        return taille;
    }
    public void setTaille(Taille taille){
        this.taille = taille;
        super.setImpact(calculImpact(possede, taille, kilomAnnee, amortissement));
    }

    public int getKilomAnnee(){
        return kilomAnnee;
    }
    public void setKilomAnnee(int kilomAnnee){
        this.kilomAnnee = kilomAnnee;
        super.setImpact(calculImpact(possede, taille, kilomAnnee, amortissement));
    }

    public int getAmortissement(){
        return amortissement;
    }
    public void setAmortissement(int amortissement){
        this.amortissement = amortissement;
        super.setImpact(calculImpact(possede, taille, kilomAnnee, amortissement));
    }
    
    /**
     * la méthode calculImpact calcule et retourne l'impact carbone en TCO2eq relatif aux transports de l'utilisateur
     * en faisant une disjonction de cas sur la taille du vehicule en verifiant d'abord que l'utilisateur
     * possède un vehicule 
     * <pre>{@code public double calculImpact(boolean possede, Taille taille, int kilomAnnee, int amortissement){
        if (!possede)
            return 0;
        else {
            switch(taille){
                case P:
                    return kilomAnnee*1.93*0.0001 + (4.2/amortissement);
                case G:
                    return kilomAnnee*1.93*0.0001 + (19/amortissement);
                default:
                    return 0;
            }
        }
    }
     *}</pre>
     * 
     * @param possede indique si l'utilisateur possède ou ne possède pas de moyen de transport 
     * @param taille indique la taille du véhicule, G pour gros et P pour petit
     * @param kilomAnnee nombre de kilomètre parcourus par an
     * @param amortissement durée de conservation du véhicule
     * @since 1.0
     * @return l'impact carbone relatif aux transports de l'utilisateur
     */
    public double calculImpact(boolean possede, Taille taille, int kilomAnnee, int amortissement){
        if (!possede)
            return 0;
        else {
            switch(taille){
                case P:
                    return kilomAnnee*1.93*0.0001 + (4.2/amortissement);
                case G:
                    return kilomAnnee*1.93*0.0001 + (19/amortissement);
                default:
                    return 0;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        if (!possede)
            return "[ID = " + super.getID() + " ] " + "Pas de voiture donc impact nul pour le poste de consommation transport";
        else{
            switch(taille){
                case P:
                    return "[ID = " + super.getID() + " ] " + "Voiture de petite taille, achetee il y a " + amortissement + " ans, utilisee pour " + kilomAnnee + " kilometres par an. L'impact carbone est donc de " + super.getImpact() + " TCO2eq";
                case G:
                    return "[ID = " + super.getID() + " ] " + "Voiture de grosse taille, achetee il y a " + amortissement + " ans, utilisee pour " + kilomAnnee + " kilometres par an. L'impact carbone est donc de " + super.getImpact() + " TCO2eq";
                default:
                    return "";
            }
        }
    }
}
