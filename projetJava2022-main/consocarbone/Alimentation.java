package consocarbone;

/**
    * <b> Cette classe implémente le poste de consommation carbone relatif à l'alimentation. </b>
    *
    * @author Gaétan De Castellane, Martin Youssef
    * @version 1.0
*/

public class Alimentation extends ConsoCarbone{
    private double txBoeuf;
    private double txVege;

    public Alimentation() {
        super();
        this.txBoeuf = 0;
        this.txVege = 0;
    }

    /**
        * Le constructeur Alimentation implémente le taux de boeuf et de végétaux ainsi que l'impact d'un objet de type Alimentation
        * 
        * @param txBoeuf de type double, indique le taux de boeuf dans l'alimention de l'utilisateur
        * @param txVege de type double, indique le taux de végétaux dans l'alimention de l'utilisateur
        * @since 1.0
    */
    public Alimentation(double txBoeuf, double txVege) {
        super();
        this.txBoeuf = txBoeuf;
        this.txVege = txVege;
        super.setImpact(calculImpact(txVege, txBoeuf));
    }

    /**
        * la méthode carboneMoyen affiche à l'écran l'émission moyenne de CO2 en kg/an vis-à-vis de différents secteurs <br>
        *<pre>{@code public static void carboneMoyen(){
        System.out.println("Un français émet en moyenne... }
        *}</pre>
        *
        * @since 1.0 
    */
    public static void carboneMoyen() {
        System.out.println("Un français émet en moyenne 263 kg/an de CO2 équivalent en consommant des boissons, 408 kg/an en produits laitiers et oeufs, 1144 kg/an en viandes et poissons et 538 kg/an en consommations diverses");        
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

    /**
        * la méthode calculImpact calcule et retourne l'impact carbone en TCO2eq de l'alimentation de l'utilisateur,
        par rapport à son taux de végétaux et de boeuf dans son alimentation à l'aide de la formule suivante :
        8 * txBoeuf + 1.6 * (1 - txVege - txBoeuf) + 0.9 * txVege
        * 
        * @param txVege le taux de végétaux dans l'alimention de l'utilisateur
        * @param txBoeuf le taux de boeuf dans l'alimention de l'utilisateur 
        * @return l'impact carbone relatif à l'alimentation de l'utilisateur
        * @since 1.0
    */
    public double calculImpact(double txVege, double txBoeuf) {
        return 8 * txBoeuf + 1.6 * (1 - txVege - txBoeuf) + 0.9 * txVege;
    }

    /**
        * {@inheritDoc}
    */
    @Override
    public String toString(){
        return "[ID = " + super.getID() + " ] " + "Alimentation avec un taux de repas a base de boeuf de " + txBoeuf + " et vegetarien de " + txVege + ". L'impact carbone est donc de " + super.getImpact() + " TCO2eq";    
    }


}
