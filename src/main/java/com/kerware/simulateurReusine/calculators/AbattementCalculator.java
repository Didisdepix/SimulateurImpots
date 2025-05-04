package com.kerware.simulateurReusine.calculators;

import com.kerware.simulateur.SituationFamiliale;

/**
 * Cette classe calcule l'abattement en fonction des revenus 
 */

public class AbattementCalculator {
    private static final int L_ABT_MAX = 14171;
    private static final int L_ABT_MIN = 495;
    private static final double T_ABT = 0.1;

    public double calculer(int revNetDecl1, int revNetDecl2, SituationFamiliale situation) {
        long abt1 = Math.round(revNetDecl1 * T_ABT);
        long abt2 = Math.round(revNetDecl2 * T_ABT);

        abt1 = Math.max(Math.min(abt1, L_ABT_MAX), L_ABT_MIN);
        abt2 = (situation == SituationFamiliale.MARIE || situation == SituationFamiliale.PACSE)
                ? Math.max(Math.min(abt2, L_ABT_MAX), L_ABT_MIN)
                : 0;

        return abt1 + abt2;
    }
}
