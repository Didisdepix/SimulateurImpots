package com.kerware.simulateurReusine.calculators;

public class DecoteCalculator {
    private static final double SEUIL_SEUL = 1929;
    private static final double SEUIL_COUPLE = 3191;
    private static final double DECOTE_SEUL = 873;
    private static final double DECOTE_COUPLE = 1444;
    private static final double TAUX = 0.4525;

    public double appliquer(double impotBrut, double nbPartsDeclarants, double contribution) {
        
        double decote = getDecote(impotBrut, nbPartsDeclarants,contribution);
        return Math.round((impotBrut - decote) + contribution);
    }
    
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
