package consocarbone;

import org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class LogementTest {

    @Test
    public void testLogement() {
        final CE ce = CE.G;
        Logement logement = new Logement(150, ce);

        assertEquals("Erreur pour setSuperficie", 150, logement.setSuperficie(150));
        assertEquals("Erreur pour setCe", ce, logement.setCe(ce));

        assertEquals("Erreur pour getSuperficie", 150, logement.getSuperficie());
        assertEquals("Erreur pour getCe", ce, logement.getCe());

        assertEquals("Erreur pour calculImpact", 0.75, logement.calculImpact(150, ce));
    }

}