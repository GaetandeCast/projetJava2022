package consocarbone;

public class Alimentation extends ConsoCarbone{
    private double txBoeuf;
    private double txVege;

    public Alimentation() {
        super();
        this.txBoeuf = 0;
        this.txVege = 0;
    }

    public Alimentation(double txBoeuf, double txVege) {
        super();
        this.txBoeuf = txBoeuf;
        this.txVege = txVege;
        super.setImpact(calculImpact(txVege, txBoeuf));
    }

    public static void carboneMoyen() {
        System.out.println("Un français émet en moyenne 1696 kg/an de CO2 équivalent en énergie et utilités, 675 kg/an en construction et gros entretiens, 335 kg/an en équipement des logements");
    }

    public void setTxBoeuf(double txBoeuf) {
        this.txBoeuf = txBoeuf;
        super.setImpact(calculImpact(txVege, txBoeuf));
    }
    public double getTxBoeuf() {
        return txBoeuf;
    }

    public void setTxVege(double txVege) {
        this.txVege = txVege;
        super.setImpact(calculImpact(txVege, txBoeuf));
    }
    public double getTxVege() {
        return txVege;
    }

    public double calculImpact(double txVege, double txBoeuf) {
        return 8 * txBoeuf + 1.6 * (1 - txVege - txBoeuf) + 0.9 * txVege;
    }

    @Override
    public String toString(){
        return "[ID = " + super.getID() + " ] " + "Alimentation avec un taux de repas a base de boeuf de " + txBoeuf + " et vegetarien de " + txVege + ". L'impact carbone est donc de " + super.getImpact() + " TCO2eq";    
    }


}
