package consocarbone;

public class Main {
    public static void main(String[] args) {
        Alimentation a = new Alimentation(0.1, 0.3);
        BienConso b = new BienConso(3957);
        Logement l = new Logement(50, CE.A);
        Transport t = new Transport(true, Taille.P, 750, 3);

        //On teste le polymophisme sur toString
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(l.toString());
        System.out.println(t.toString());

        System.out.println();

        //et sur carboneMoyen
        Alimentation.carboneMoyen();
        BienConso.carboneMoyen();
        Logement.carboneMoyen();
        Transport.carboneMoyen();

        System.out.println();

        //On teste quelques setters et getters
        
        a.setTxBoeuf(0.2);
        System.out.println(a.getTxBoeuf());

        b.setMontant(5467);
        System.out.println(b.getMontant());

        l.setSuperficie(250);
        l.setCe(CE.E);
        System.out.println(l.toString());

        
        t.setTaille(Taille.G);
        System.out.println(t.getTaille());
    }
}
        
