package com.kerware.simulateurReusine.calculators;

import com.kerware.simulateurReusine.enums.*;

public class ContributionExceptionnelleCalculator {

    public double calculer(double revenu, double nbPartsDecl) {
        double total = 0;
        var tranches = limitesTranchesContributionExceptionnelle.values();
        var tauxCelib = tauxContributionExceptionnelle.values();
        var tauxCouple = tauxContributionExceptionnelleCouples.values();

        for (int i = 0; i < 5; i++) {
            if (revenu >= tranches[i].getLimite() && revenu < tranches[i + 1].getLimite()) {
                double taux = nbPartsDecl == 1 ? tauxCelib[i].getTaux() : tauxCouple[i].getTaux();
                total += (revenu - tranches[i].getLimite()) * taux;
                break;
            } else {
                double taux = nbPartsDecl == 1 ? tauxCelib[i].getTaux() : tauxCouple[i].getTaux();
                total += (tranches[i + 1].getLimite() - tranches[i].getLimite()) * taux;
            }
        }

        return Math.round(total);
    }
}