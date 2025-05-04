package com.kerware.simulateurReusine.calculators;

import com.kerware.simulateur.SituationFamiliale;

/**
 * Classe permettant de calculer le nombre de parts pour le foyer ou les déclarants
 * 
 * Paramètres:
 * 	La situation (marié, célibataire etc)
 * 	Le nombre d'enfants
 * 	Le cas échéant, si le déclarant est un parent isolé
 */

public class PartsCalculator {
    public double calculer(SituationFamiliale situation, int nbEnfants, int nbEnfantsHandicapes, boolean parentIsol) {
        
    	//Parts selon le nombre de déclarants 
    	double parts = switch (situation) {
            case MARIE, PACSE -> 2;
            default -> 1;
        };

        //Attribution des parts en fonction du nombre d'enfants 
        parts += (nbEnfants <= 2) ? nbEnfants * 0.5 : 1 + (nbEnfants - 2);
        parts += nbEnfantsHandicapes * 0.5;
        
        //Attribution des parts en fonction de la situation familiale
        if (parentIsol && nbEnfants > 0) parts += 0.5;
        if (situation == SituationFamiliale.VEUF && nbEnfants > 0) parts += 1;

        return parts;
    }
}