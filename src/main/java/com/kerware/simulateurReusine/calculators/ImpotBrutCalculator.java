package com.kerware.simulateurReusine.calculators;

import com.kerware.simulateurReusine.enums.limitesTranchesRevenusImposables;
import com.kerware.simulateurReusine.enums.tauxImposition;

public class ImpotBrutCalculator {
    public double calculer(double revenuFiscaleRef, double nbParts) {
        double revenuImposable = revenuFiscaleRef / nbParts;
        double impots = 0;

        var tranches = limitesTranchesRevenusImposables.values();
        var taux = tauxImposition.values();

        for (int i = 0; i < taux.length; i++) {
            if (revenuImposable >= tranches[i].getLimite() && revenuImposable < tranches[i + 1].getLimite()) {
                impots += (revenuImposable - tranches[i].getLimite()) * taux[i].getTaux();
                break;
            } else {
                impots += (tranches[i + 1].getLimite() - tranches[i].getLimite()) * taux[i].getTaux();
            }
        }

        return Math.round(impots * nbParts);
    }
}