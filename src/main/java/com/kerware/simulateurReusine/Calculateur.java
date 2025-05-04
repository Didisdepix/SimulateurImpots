package com.kerware.simulateurReusine;

import com.kerware.simulateur.SituationFamiliale;

/**
 * Classe permettant d'effectuer les calculs nécessaires 
 */

public class Calculateur {
	
	//Constantes utilisées dans les calculs
	
	// Abattement
    private static final int L_ABT_MAX = 14171;
    private static final int L_ABT_MIN = 495;
    private static final double T_ABT = 0.1;
    
    //Plafond de baisse maximal par demi part
    private static final double PLAF_DEMI_PART = 1759;
    
    //Seuil de décote
    private static final double SEUIL_DECOTE_DECLARANT_SEUL = 1929;
    private static final double SEUIL_DEOCTE_DECLARANT_COUPLE = 3191;

    //Décote
    private static final double DECOTE_MAX_DECLARANT_SEUL = 873;
    private static final double DECOTE_MAX_DECLARANT_COUPLE = 1444;
    private static final double TAUX_DECOTE = 0.4525;
    
    
    //Elements nécessaires au calcul
	private int revNetDecl1;
	private int revNetDecl2;
	private SituationFamiliale sitFam;
	private int nbEnfants;
	private int nbEnfantsHandicapes;
	private boolean parentIsol;
	
	public Calculateur(int revNetDecl1, int revNetDecl2, SituationFamiliale sitFam, int nbEnfants, int nbEnfantsHandicapes, boolean parentIsol) {
		this.nbEnfants = nbEnfants;
		this.nbEnfantsHandicapes = nbEnfantsHandicapes;
		this.parentIsol = parentIsol;
		this.revNetDecl1 = revNetDecl1;
		this.revNetDecl2 = revNetDecl2;
	}
	
	// EXIGENCE : EXG_IMPOT_02
	public double calculerAbattement() {
		
		double abt = 0;
        
		//Abattements pour les deux déclarants
        long abt1 = Math.round(revNetDecl1 * T_ABT);
        long abt2 = Math.round(revNetDecl2 * T_ABT);

        if (abt1 > L_ABT_MAX) {
            abt1 = L_ABT_MAX;
        }
        
        //Application de l'abbatement aux deux déclarants en cas d'union
        if ( sitFam == SituationFamiliale.MARIE || sitFam == SituationFamiliale.PACSE ) {
            if (abt2 > L_ABT_MAX) {
                abt2 = L_ABT_MAX;
            }
        }

        if (abt1 < L_ABT_MIN) {
            abt1 = L_ABT_MIN;
        }

        if ( sitFam == SituationFamiliale.MARIE || sitFam == SituationFamiliale.PACSE ) {
            if (abt2 < L_ABT_MIN) {
                abt2 = L_ABT_MIN;
            }
        }

        abt = abt1 + abt2;
        System.out.println( "Abattement : " + abt );
        
        return abt;
	}
	
	//Calcul du revenu fiscale de référence
	public double calculerRevenuFiscaleDeReference(double abattement) {
		return this.revNetDecl1 + this.revNetDecl2 - abattement;
	}
	
	// parts déclarants
    // EXIG  : EXG_IMPOT_03
	public double calculeNombreDeParts() {
		
		//Nombre de parts des déclarants ensembles
		double nbPtsDecl = 0;
		
        switch ( sitFam ) {
            case CELIBATAIRE:
                nbPtsDecl = 1;
                break;
            case MARIE:
                nbPtsDecl = 2;
                break;
            case DIVORCE:
                nbPtsDecl = 1;
                break;
            case VEUF:
                nbPtsDecl = 1;
                break;
            case PACSE:
                nbPtsDecl = 2;
                break;
        }

        System.out.println( "Nombre d'enfants  : " + this.nbEnfants );
        System.out.println( "Nombre d'enfants handicapés : " + this.nbEnfantsHandicapes );

        // parts enfants à charge
        if ( this.nbEnfants <= 2 ) {
        	nbPtsDecl = nbPtsDecl + this.nbEnfants * 0.5;
        } else if ( this.nbEnfants > 2 ) {
        	nbPtsDecl = nbPtsDecl+  1.0 + ( this.nbEnfants - 2 );
        }

        // parent isolé

        System.out.println( "Parent isolé : " + this.parentIsol );

        if ( this.parentIsol ) {
            if ( this.nbEnfants > 0 ){
            	nbPtsDecl = nbPtsDecl + 0.5;
            }
        }

        // Veuf avec enfant
        if ( sitFam == SituationFamiliale.VEUF && this.nbEnfants > 0 ) {
        	nbPtsDecl = nbPtsDecl + 1;
        }

        // enfant handicapé
        nbPtsDecl = nbPtsDecl + this.nbEnfantsHandicapes * 0.5;

        System.out.println( "Nombre de parts : " + nbPtsDecl );
        
        return nbPtsDecl;
	}
	
	// EXIGENCE : EXG_IMPOT_07:
    // Contribution exceptionnelle sur les hauts revenus
	public int calculContributionExceptionnelle(double rFRef, double nbPtsDecl) {
		
        double contribExceptionnelle = 0;
        
        do {
            if ( rFRef >= limitesCEHR[i] && rFRef < limitesCEHR[i+1] ) {
                if ( nbPtsDecl == 1 ) {
                    contribExceptionnelle += ( rFRef - limitesCEHR[i] ) * tauxCEHRCelibataire[i];
                } else {
                    contribExceptionnelle += ( rFRef - limitesCEHR[i] ) * tauxCEHRCouple[i];
                }
                break;
            } else {
                if ( nbPtsDecl == 1 ) {
                    contribExceptionnelle += ( limitesCEHR[i+1] - limitesCEHR[i] ) * tauxCEHRCelibataire[i];
                } else {
                    contribExceptionnelle += ( limitesCEHR[i+1] - limitesCEHR[i] ) * tauxCEHRCouple[i];
                }
            }
            i++;
        } while( i < 5);

        contribExceptionnelle = Math.round( contribExceptionnelle );
        System.out.println( "Contribution exceptionnelle sur les hauts revenus : " + contribExceptionnelle );
	}
}
