package consocarbone;

/**
* <b> Cette classe implémente le poste de consommation carbone relatif au logement. </b>
*
* @author Gaétan De Castellane, Martin Youssef
* @version 1.0
*/

public class Logement extends ConsoCarbone{
    private int superficie;
    private CE ce;

    public Logement() {
        super();
        this.superficie = 0;
        this.ce = CE.G;
    }

    /**
     * Le constructeur Logement implémente la superficie et la classe énergétique ainsi que l'impact d'une instance de sa classe
     * 
     * @param superficie la superficie du logement 
     * @param ce la classe énergétique du logement 
     * @since 1.0
     */
    public Logement(int superficie, CE ce) {
        super();
        this.superficie = superficie;
        this.ce = ce;
        super.setImpact(calculImpact(superficie, ce));
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
        System.out.println("Un français émet en moyenne 1696 kg/an de CO2 équivalent en énergie et utilités, 675 kg/an en construction et gros entretiens, 335 kg/an en équipement des logements");
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
        super.setImpact(calculImpact(superficie, ce));
    }
    public int getSuperficie() {
        return superficie;
    }

    public void setCe(CE ce) {
        this.ce = ce;
        super.setImpact(calculImpact(superficie, ce));
    }
    public CE getCe() {
        return ce;
    }

    /**
     * la méthode calculImpact calcule et retourne l'impact carbone en TCO2eq du logement de l'utilisateur
     * en faisant une disjonction de cas sur la classe energétique du logement :
     * <pre>{@code public double calculImpact(int superficie, CE ce) {
        switch (ce) {
            case A:
                return 0.005 * superficie;
            case B:
                return 0.01 * superficie;
            case C:
                return 0.02 * superficie;
            case D:
                return 0.035 * superficie;
            case E:
                return 0.055 * superficie;
            case F:
                return 0.08 * superficie;
            case G:
                return 0.1 * superficie;
            default:
                return 0;
                    }
        }
     * }</pre>
     * 
     * @param superficie la superficie du logement 
     * @param ce la classe énergétique du logement
     * @return l'impact carbone relatif au logement de l'utilisateur
     * @since 1.0
     */
    public double calculImpact(int superficie, CE ce) {
        switch (ce) {
            case A:
                return 0.005 * superficie;
            case B:
                return 0.01 * superficie;
            case C:
                return 0.02 * superficie;
            case D:
                return 0.035 * superficie;
            case E:
                return 0.055 * superficie;
            case F:
                return 0.08 * superficie;
            case G:
                return 0.1 * superficie;
            default:
                return 0;
        }
    }

    /**
        * {@inheritDoc}
    */
    @Override
    public String toString(){
        switch(ce){
            case A:
                return "[ID = " + super.getID() + " ] " + "Logement de "+ superficie +" m^2 et de classe energetique " + "A" + ". L'impact carbone est donc de "+ super.getImpact() +" TCO2eq";
            case B:
                return "[ID = " + super.getID() + " ] " + "Logement de "+ superficie +" m^2 et de classe energetique " + "B" + ". L'impact carbone est donc de "+ super.getImpact() +" TCO2eq";
            case C:
                return "[ID = " + super.getID() + " ] " + "Logement de "+ superficie +" m^2 et de classe energetique " + "C" + ". L'impact carbone est donc de "+ super.getImpact() +" TCO2eq";
            case D:
                return "[ID = " + super.getID() + " ] " + "Logement de "+ superficie +" m^2 et de classe energetique " + "D" + ". L'impact carbone est donc de "+ super.getImpact() +" TCO2eq";
            case E:
                return "[ID = " + super.getID() + " ] " + "Logement de "+ superficie +" m^2 et de classe energetique " + "E" + ". L'impact carbone est donc de "+ super.getImpact() +" TCO2eq";
            case F:
                return "[ID = " + super.getID() + " ] " + "Logement de "+ superficie +" m^2 et de classe energetique " + "F" + ". L'impact carbone est donc de "+ super.getImpact() +" TCO2eq";
            case G:
                return "[ID = " + super.getID() + " ] " + "Logement de "+ superficie +" m^2 et de classe energetique " + "G" + ". L'impact carbone est donc de "+ super.getImpact() +" TCO2eq";
            default:
                return "";
        }
    }
}
