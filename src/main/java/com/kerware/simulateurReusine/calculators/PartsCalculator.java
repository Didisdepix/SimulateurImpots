package com.kerware.simulateurReusine.calculators;

import com.kerware.simulateur.SituationFamiliale;

public class PartsCalculator {
    public double calculer(SituationFamiliale situation, int nbEnfants, int nbEnfantsHandicapes, boolean parentIsol) {
        double parts = switch (situation) {
            case MARIE, PACSE -> 2;
            default -> 1;
        };

        parts += (nbEnfants <= 2) ? nbEnfants * 0.5 : 1 + (nbEnfants - 2);
        if (parentIsol && nbEnfants > 0) parts += 0.5;
        if (situation == SituationFamiliale.VEUF && nbEnfants > 0) parts += 1;
        parts += nbEnfantsHandicapes * 0.5;

        return parts;
    }
}