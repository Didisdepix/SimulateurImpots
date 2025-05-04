package com.kerware.simulateurReusine.calculators;

import com.kerware.simulateurReusine.enums.limitesTranchesRevenusImposables;
import com.kerware.simulateurReusine.enums.tauxImposition;

/*
 * Cette classe calcul l'impot à payer pour les déclarants avant toute modification dû à la décote ou autre
 * 
 * Paramètres:
 * 	Revenu annuel du ou des déclarants
 * 	Nombre de parts 
 */

public class ImpotBrutCalculator {
    public double calculer(double revenuFiscaleRef, double nbParts) {
        double revenuImposable = revenuFiscaleRef / nbParts;
        double impots = 0;

        //Listes contenant les tranches de revenus et les taux d'imposition associés
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