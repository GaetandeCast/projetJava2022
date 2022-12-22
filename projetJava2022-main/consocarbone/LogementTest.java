package consocarbone;

import org.junit.Test;

import junit.framework.TestCase;

public class LogementTest extends TestCase {

    @Test
    public void testLogement() {
        final CE ce1 = CE.A;
        final CE ce2 = CE.B;
        Logement logement = new Logement(150, ce1);

        assertEquals("Erreur pour setSuperficie", 150, logement.getSuperficie());
        assertEquals("Erreur pour setCe", CE.A, logement.getCe());

        logement.setSuperficie(200);
        logement.setCe(ce2);

        assertEquals("Erreur pour getSuperficie", 200, logement.getSuperficie());
        assertEquals("Erreur pour getCe", CE.B, logement.getCe());

        assertEquals("Erreur pour calculImpact", 2, logement.calculImpact(200, ce2), 0.00001);
    }

}