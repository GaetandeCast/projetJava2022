package consocarbone;

import org.junit.Test;

import junit.framework.TestCase;

public class AlimentationTest extends TestCase {

    @Test
    public void testAlimentation() {

        Alimentation alimentation = new Alimentation(0.6, 0.4);

        assertEquals("Erreur pour setTxBoeuf", 0.6, alimentation.getTxBoeuf());
        assertEquals("Erreur pour setTxVege", 0.4, alimentation.getTxVege());

        alimentation.setTxBoeuf(0.5);
        alimentation.setTxVege(0.3);

        assertEquals("Erreur pour getTxBoeuf", 0.5, alimentation.getTxBoeuf());
        assertEquals("Erreur pour getTxVege", 0.3, alimentation.getTxVege());

        assertEquals("Erreur pour calculImpact", 4.59, alimentation.calculImpact(0.3, 0.5), 0.00001);

        assertEquals("Erreur pour ToString",
                "[ID = 1 ] Alimentation avec un taux de repas a base de boeuf de 0.5 et vegetarien de 0.3. L'impact carbone est donc de "
                        + alimentation.getImpact() + " TCO2eq",
                alimentation.toString());
    }

}