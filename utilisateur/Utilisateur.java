package utilisateur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import consocarbone.*;

/**
 * Les attributs sont des instances des differentes classes filles de la classe ConsoCarbone
 * representant chacun un poste de consommation carbone pour l'utilisateur
 * Pour avoir la possibilite d'avoir plusieur logements et vehicules, on utilise des ArrayList de Logement et Transport
 * 
 * @author Gaétan De Castellane, Martin Youssef
 * @since 1.0
*/
public class Utilisateur {
    private Alimentation alimentation;
    private BienConso bienConso;
    private ArrayList<Logement> logements;
    private ArrayList<Transport> vehicules;
    private ServicesPublics servicesPublics;

    //Le constructeur par defaut appelle les constructeurs par defaut des classes de ses attributs
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
    
    /**
     * Ce constructeur permet de creer un Utilisateur sans creer de liste de Logement et Transport au prealable
     * 
     * @param a le poste de consommation relatif a l'alimentation
     * @param b le poste de consommation relatif aux biens de consommation
     * @param l le poste de consommation relatif au logement
     * @param t le poste de consommation relatif aux transports
     */
    public Utilisateur(Alimentation a, BienConso b, Logement l, Transport t){
        alimentation = a;
        bienConso = b;
        logements = new ArrayList<Logement>();
        logements.add(l);
        vehicules = new ArrayList<Transport>();
        vehicules.add(t);
        servicesPublics = ServicesPublics.getInstance();
    }

    //Renvoi la sommme des impacts respectifs des postes de consommation carbone de l'utilisateur
    public double calculerEmpreinte(){
        double empreinte = alimentation.getImpact() + bienConso.getImpact() + servicesPublics.getImpact();
        for (Logement l : logements) empreinte+=l.getImpact();
        for (Transport t : vehicules) empreinte+=t.getImpact();
        return empreinte;
    }

    /**
     * Indique, en l'affichant, la repartition de l'empreinte carbone par type de poste de consommation carbone
     */
    public void detaillerEmpreinte(){
        //On cumule les impact de tout les logements et vehicules
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

    /**
     * Determine l'instance de ConsoCarbone avec le plus grand impact 
     * et fait des recommendations pour réduire la valeur de cet impact
     * 
     * @param nomsVehicules TreeMap associant le nom des véhicules et leur ID
     * @param nomsLogements TreeMap associant le nom des logements et leur ID
     * @since 1.0
     */
    public void recommendations(TreeMap<Integer,String> nomsVehicules, TreeMap<Integer,String> nomsLogements){
        //On ajoute toutes les instances de ConsoCarbone a une ArrayList
        ArrayList<ConsoCarbone> listeconsos = new ArrayList<ConsoCarbone>();
        listeconsos.add(alimentation);
        listeconsos.add(bienConso);
        listeconsos.add(servicesPublics);

        for (Logement l : logements) listeconsos.add(l);
        for (Transport t : vehicules) listeconsos.add(t);

        //On trie cette ArrayList pour avoir l'instance de ConsoCarbone avec le plus grand impact au fond de la liste
        Collections.sort(listeconsos);

        //On concatene le nom des instance de ConsoCarbone dans l'ordre d'impact 
        //pour les indiquer dans l'ordre croissant d'impact a l'utilisateur
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

        //On determine l'instance de ConsoCarbone avec l'impact le plus eleve
        int longueur = listeconsos.size();
        ConsoCarbone consoMaxImpact = listeconsos.get(longueur-1);

        //On traite a part le cas ou ce sont les services publics 
        //car l'utilisateur n'a pas d'influence sur ce poste de consommation 
        boolean maxImpactSP=false;
        if (consoMaxImpact instanceof ServicesPublics){
            listeconsos.remove(consoMaxImpact);
            longueur-=1;
            consoMaxImpact = listeconsos.get(longueur-1);
            maxImpactSP=true;
        }
        
        //On fait une recommendation personalisee dependant du type du poste de consommation carbone le plus eleve
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
            System.out.println("votre vehicule " + nomsVehicules.get(t.getID()) + ", par exemple en l'utilisant moins ou en vous en separant.");
        }
    }
    
}
