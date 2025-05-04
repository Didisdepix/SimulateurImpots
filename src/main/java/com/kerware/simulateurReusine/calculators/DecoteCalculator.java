package com.kerware.simulateurReusine.calculators;

/**
 * Cette classe permet de calculer la décote attribué aux déclarants en fonction et l'impot à payer une fois la décote appliquée
 * 
 * Paramètres:
 * 	L'impot brut sur le revenu
 * 	Le nombre de parts 
 * 	La contribution éventuelle en cas de grande fortune
 */

public class DecoteCalculator {
    private static final double SEUIL_SEUL = 1929;
    private static final double SEUIL_COUPLE = 3191;
    private static final double DECOTE_SEUL = 873;
    private static final double DECOTE_COUPLE = 1444;
    private static final double TAUX = 0.4525;

    //Fonction qui retourne l'impot "décoté"
    public double appliquer(double impotBrut, double nbPartsDeclarants, double contribution) {
        
        double decote = getDecote(impotBrut, nbPartsDeclarants,contribution);
        return Math.round((impotBrut - decote) + contribution);
    }
    
    //Fonction qui retourne la décote attribuée aux déclarants
    public double getDecote(double impotBrut, double nbPartsDeclarants, double contribution) {
    	double decote = 0;

        if (nbPartsDeclarants == 1 && impotBrut < SEUIL_SEUL) {
            decote = DECOTE_SEUL - (impotBrut * TAUX);
        } else if (nbPartsDeclarants == 2 && impotBrut < SEUIL_COUPLE) {
            decote = DECOTE_COUPLE - (impotBrut * TAUX);
        }

        decote = Math.min(Math.round(decote), impotBrut);
        return decote;
    }
}
