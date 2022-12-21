package utilisateur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import consocarbone.*;

public class Utilisateur {
    private Alimentation alimentation;
    private BienConso bienConso;
    private ArrayList<Logement> logements;
    private ArrayList<Transport> vehicules;
    private ServicesPublics servicesPublics;

    public Utilisateur(){
        alimentation = new Alimentation();
        bienConso = new BienConso();
        logements = new ArrayList<Logement>();
        logements.add(new Logement());
        vehicules = new ArrayList<Transport>();
        vehicules.add(new Transport());
        servicesPublics = ServicesPublics.getInstance();
    }

    public Utilisateur(Alimentation a, BienConso b, ArrayList<Logement> l, ArrayList<Transport> t){
        alimentation = a;
        bienConso = b;
        logements = l;
        vehicules = t;
        servicesPublics = ServicesPublics.getInstance();
    }
    public Utilisateur(Alimentation a, BienConso b, Logement l, Transport t){
        alimentation = a;
        bienConso = b;
        logements = new ArrayList<Logement>();
        logements.add(l);
        vehicules = new ArrayList<Transport>();
        vehicules.add(t);
        servicesPublics = ServicesPublics.getInstance();
    }

    public double calculerEmpreinte(){
        double empreinte = alimentation.getImpact() + bienConso.getImpact() + servicesPublics.getImpact();
        for (Logement l : logements) empreinte+=l.getImpact();
        for (Transport t : vehicules) empreinte+=t.getImpact();
        return empreinte;
    }

    public void detaillerEmpreinte(){
        double impactLogements=0;
        for (Logement l : logements) impactLogements+=l.getImpact();
        double impactVehicules=0;
        for (Transport t : vehicules) impactVehicules+=t.getImpact();

        System.out.println("Votre empreinte carbone annuelle est de " + calculerEmpreinte()+ " tonnes de CO2 equivalent.\n"
        + alimentation.getImpact() + " TCO2eq vien(nen)t de votre alimentation, " 
        + bienConso.getImpact() + " TCO2eq vien(nen)t de vos consommations diverses, " 
        + impactLogements + " TCO2eq vien(nen)t de votre(vos) logement(s), " 
        + impactVehicules + " TCO2eq vien(nen)t de votre(vos) vehicule(s) et " 
        + servicesPublics.getImpact() + " TCO2eq viennent des services publics.");
    }

    public void recommendations(TreeMap<Integer,String> nomsVehicules, TreeMap<Integer,String> nomsLogements){
        ArrayList<ConsoCarbone> listeconsos = new ArrayList<ConsoCarbone>();
        listeconsos.add(alimentation);
        listeconsos.add(bienConso);
        listeconsos.add(servicesPublics);

        for (Logement l : logements) listeconsos.add(l);
        for (Transport t : vehicules) listeconsos.add(t);

        Collections.sort(listeconsos);

        String s = "";
        for(ConsoCarbone c : listeconsos){
            if(s!="") s+= ", ";
            if (c instanceof Alimentation) s+="l' alimentation";
            else if (c instanceof BienConso) s+="les biens de consommation";
            else if (c instanceof Logement) {
                Logement l = (Logement) c;
                if (l.getSuperficie()!=0)s+="le logement "+ nomsLogements.get(l.getID());
                else s+="le logement";
            }
            else if (c instanceof Transport) {
                Transport t = (Transport) c;
                if(t.getPossede()) s+="le vehicule " + nomsVehicules.get(t.getID());
                else s+="les transports";
            }
            else if (c instanceof ServicesPublics) s+="les services publics";
        }
        System.out.println("Dans l'odre croissant d'impact, vos poste des consommation carbone sont : " + s + ".");

        int longueur = listeconsos.size();
        ConsoCarbone consoMaxImpact = listeconsos.get(longueur-1);

        boolean maxImpactSP=false;
        if (consoMaxImpact instanceof ServicesPublics){
            listeconsos.remove(consoMaxImpact);
            longueur-=1;
            consoMaxImpact = listeconsos.get(longueur-1);
            maxImpactSP=true;
        }
        
        if (maxImpactSP) System.out.println("Vous ne pouvez pas réduire l'impact venant des services publics, il vous donc recommende de reduire votre impact carbone venant de ");
        else System.out.print("Afin d'avoir un mode de vie plus durable, il vous est recommende de reduire votre impact carbone venant de ");
        if (consoMaxImpact instanceof Alimentation) System.out.println("votre alimentation, par exemple en diminuant la proportion de repas a base de boeuf et en augmentant celle de repas vegetariens.");
        else if (consoMaxImpact instanceof BienConso) System.out.println("vos depenses en biens de consommation.");
        else if (consoMaxImpact instanceof Logement) {
            Logement l = (Logement) consoMaxImpact;
            System.out.println("votre logement " + nomsLogements.get(l.getID()) + ", par exemple en le faisant isoler afin d'ameliorer sa classe energetique.");
        }
        else if (consoMaxImpact instanceof Transport) {
            Transport t = (Transport) consoMaxImpact;
            System.out.print("votre vehicule " + nomsVehicules.get(t.getID()) + ", par exemple en l'utilisant moins ou en vous en separant.");
        }
    }
    
}
