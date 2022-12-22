package consocarbone;
//design pattern: Singleton

/**
    * <b> Cette classe implémente le poste de consommation carbone relatif aux services publics </b>
    *
    * @author Gaétan De Castellane, Martin Youssef
    * @version 1.0
*/
public class ServicesPublics extends ConsoCarbone {
    private static ServicesPublics instance = null;

    private ServicesPublics(double i){
        super(); // instanciation à l'aide du constructeur de la classe mère ConsoCarbone
        setImpact(i);
    }

    public static ServicesPublics getInstance(){
        if (instance == null) instance = new ServicesPublics(1.5);
        return instance;
    }

    /**
        * {@inheritDoc}}
    */
    @Override
    public String toString(){
        return "[ID = " + super.getID() + " ] " + "L'impact carbone des services publics est de " + getImpact() + " TCO2eq.";
    }
    
    /**
        * la méthode carboneMoyen affiche à l'écran l'émission moyenne de CO2 d'un français en kg/an vis-à-vis des services publics <br>
        *
        * @since 1.0
    */
    public static void carboneMoyen() {
        System.out.println("L'empreinte carbone des services publics français se repercute en un impact de 1.5 TCO2eq par français.");        
    }
}
