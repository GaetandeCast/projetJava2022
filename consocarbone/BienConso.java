package consocarbone;

public class BienConso extends ConsoCarbone{
    double montant;

    public BienConso(){
        super();
        this.montant = 0;
    }

    public BienConso(double montant){
        super();
        this.montant = montant;
        super.setImpact(calculImpact(montant));
    }

    public static void carboneMoyen(){
        System.out.println("Un français émet en moyenne 763 kg/an de CO2 équivalent en habillement, 1180 kg/an en achat et usages internet et technologies,et 682 kg/an en autres biens et services");
    }

    public void setMontant(double montant){
        this.montant = montant;
        super.setImpact(calculImpact(montant));
    }
    public double getMontant(){
        return montant;
    }

    public double calculImpact(double montant){
        return montant / 1750;
    }

    @Override
    public String toString(){
        return "[ID = " + super.getID() + " ] " + "Depenses annuelles de consommation de " + montant + " euros. L'impact carbone est donc de " + super.getImpact() + " TCO2eq";    
    }
}