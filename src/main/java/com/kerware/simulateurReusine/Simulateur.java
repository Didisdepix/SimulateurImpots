package com.kerware.simulateurReusine;

import com.kerware.simulateur.SituationFamiliale;
import com.kerware.simulateurReusine.calculators.AbattementCalculator;
import com.kerware.simulateurReusine.calculators.BaisseImpotCalculator;
import com.kerware.simulateurReusine.calculators.ContributionExceptionnelleCalculator;
import com.kerware.simulateurReusine.calculators.DecoteCalculator;
import com.kerware.simulateurReusine.calculators.ImpotBrutCalculator;
import com.kerware.simulateurReusine.calculators.PartsCalculator;

/**
 *  Cette classe permet de simuler le calcul de l'impôt sur le revenu
 *  en France pour l'année 2024 sur les revenus de l'année 2023 pour
 *  des cas simples de contribuables célibataires, mariés, divorcés, veufs
 *  ou pacsés avec ou sans enfants à charge ou enfants en situation de handicap
 *  et parent isolé.
 **/

public class Simulateur {

	//Informations données par les fonctions et récupérables avec les getters
	private double abattement =0;
	private double revenuFiscal = 0;
	private double nbParts = 0;
	private double nbPartsDecl = 0;
	private double impotsDecl = 0;
	private double impotsFoyer = 0;
	private double impotsPlafonnes = 0;
	private double contributionExceptionnelle = 0;
	private int impotNet = 0;

    // Fonction de calcul de l'impôt sur le revenu net en France en 2024 sur les revenus 2023

    public int calculImpot( int revNetDecl1, int revNetDecl2, SituationFamiliale sitFam, int nbEnfants, int nbEnfantsHandicapes, boolean parentIsol) {

    	 //Test au préalable si les paramètres sont bons et soulève un exception si ce n'est pas le cas
    	 TesteurParametres testeur = new TesteurParametres(revNetDecl1,  revNetDecl2,  sitFam,  nbEnfants,  nbEnfantsHandicapes,  parentIsol);
  
    	 
    	 this.abattement = new AbattementCalculator().calculer(revNetDecl1, revNetDecl2, sitFam);
        
        //Revenu fiscal de référence
    	 this.revenuFiscal = revNetDecl1 + revNetDecl2 - abattement;

    	 //Nombre de parts du foyer
         this.nbParts = new PartsCalculator().calculer(sitFam, nbEnfants, nbEnfantsHandicapes, parentIsol);
         
         //Nombre de parts qui concerne les déclarants adultes
         this.nbPartsDecl = (sitFam == SituationFamiliale.MARIE || sitFam == SituationFamiliale.PACSE) ? 2 : 1;

         //Impots payé par les déclarants uniquement
         this.impotsDecl = new ImpotBrutCalculator().calculer(revenuFiscal, nbPartsDecl);
         
         //Impots payé par le foyer entier
         this.impotsFoyer = new ImpotBrutCalculator().calculer(revenuFiscal, nbParts);

         //Impot payé après application de la baisse
         this.impotsPlafonnes = new BaisseImpotCalculator().appliquerPlafond(impotsDecl, impotsFoyer, nbParts, nbPartsDecl);
         
         //Contribution éventuelle en cas de gros revenus
         this.contributionExceptionnelle = new ContributionExceptionnelleCalculator().calculer(revenuFiscal, nbPartsDecl);

         //Impot net sur le revenu
         this.impotNet = (int)new DecoteCalculator().appliquer(impotsPlafonnes, nbPartsDecl, this.contributionExceptionnelle);
        System.out.println("Impôt net à payer : " + impotNet + " €");
         
        return this.impotNet;
    }
    
    //Getters

	public double getContribExceptionnelle() {
		return this.contributionExceptionnelle;
	}

	public double getAbattement() {
		return this.abattement;
	}

	public double getImpotAvantDecote() {
		return this.impotsPlafonnes;
	}

	public double getNbParts() {
		return this.nbParts;
	}

	public int getImpotNet() {
		return this.impotNet;
	}

	public double getRevenuReference() {
		return this.revenuFiscal;
	}

	public double getDecote() {
		return new DecoteCalculator().getDecote(impotNet, nbPartsDecl, contributionExceptionnelle);
	}





}
