package com.kerware.simulateurReusine.calculators;

/**
 * Cette classe calcule la baisse d'impot en fonction des enfants
 */

public class BaisseImpotCalculator {
    private static final double PLAF_DEMI_PART = 1759;

    public double appliquerPlafond(double impotsDeclarants, double impotsFoyer, double nbParts, double nbPartsDeclarants) {
        double ecartParts = nbParts - nbPartsDeclarants;
        double plafond = (ecartParts / 0.5) * PLAF_DEMI_PART;
        double baisse = impotsDeclarants - impotsFoyer;

        return (baisse > plafond) ? impotsDeclarants - plafond : impotsFoyer;
    }
}