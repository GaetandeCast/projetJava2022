package consocarbone;
//design pattern: Singleton
public class ServicesPublics extends ConsoCarbone {
    private static ServicesPublics instance = null;
    private static double impact;

    private ServicesPublics(double i){
        super();
        impact = i;
    }

    public static ServicesPublics getInstance(){
        if (instance == null) instance = new ServicesPublics(1.5);
        return instance;
    }

    @Override
    public String toString(){
        return "[ID = " + super.getID() + " ] " + "L'impact carbone des services publics est de " + impact + " TCO2eq.";
    }
}
