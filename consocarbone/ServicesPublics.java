package consocarbone;
//design pattern: Singleton
public class ServicesPublics extends ConsoCarbone {
    private static ServicesPublics instance = null;

    private ServicesPublics(double i){
        super();
        setImpact(i);
    }

    public static ServicesPublics getInstance(){
        if (instance == null) instance = new ServicesPublics(1.5);
        return instance;
    }

    @Override
    public String toString(){
        return "[ID = " + super.getID() + " ] " + "L'impact carbone des services publics est de " + getImpact() + " TCO2eq.";
    }
    
    public static void carboneMoyen() {
        System.out.println("L'empreinte carbone des services publics français se repercute en un impact de 1.5 TCO2eq par français.");        
    }
}
