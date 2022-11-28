package utilisateur;
import consocarbone.*;

public class Utilisateur {
    private Alimentation alimentation;
    private BienConso bienConso;
    private Logement logement;
    private Transport transport;
    private ServicesPublics servicesPublics;

    public Utilisateur(){
        alimentation = new Alimentation();
        bienConso = new BienConso();
        logement = new Logement();
        transport = new Transport();
        servicesPublics = ServicesPublics.getInstance();
    }

    public Utilisateur(Alimentation a, BienConso b, Logement l, Transport t){
        alimentation = a;
        bienConso = b;
        logement = l;
        transport = t;
        servicesPublics = ServicesPublics.getInstance();
    }

    public double calculerEmpreinte(){
        return alimentation.getImpact() + bienConso.getImpact() + logement.getImpact() + transport.getImpact() + servicesPublics.getImpact();
    }

    public void detaillerEmpreinte(){
        System.out.println("L'utilisateur.trice a une empreinte carbone annuelle de " + calculerEmpreinte()+ "tonnes de CO2 equivalent.\n"
        + alimentation.getImpact() + " TCO2eq vien(nen)t de son alimentation, " 
        + bienConso.getImpact() + " TCO2eq vien(nen)t de ses consommations diverses, " 
        + logement.getImpact() + " TCO2eq vien(nen)t de son logement, " 
        + transport.getImpact() + " TCO2eq vien(nen)t de ses transports et " 
        + servicesPublics.getImpact() + " viennent des services publics.");
    }
}
