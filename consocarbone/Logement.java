package consocarbone;

public class Logement extends ConsoCarbone{
    private int superficie;
    private CE ce;

    public Logement() {
        super();
        this.superficie = 0;
        this.ce = CE.G;
    }

    public Logement(int superficie, CE ce) {
        super();
        this.superficie = superficie;
        this.ce = ce;
        super.setImpact(calculImpact(superficie, ce));
    }

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
