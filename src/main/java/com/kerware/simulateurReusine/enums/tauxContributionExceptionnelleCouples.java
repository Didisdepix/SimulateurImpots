package com.kerware.simulateurReusine.enums;

/**
 * Enumération définissant les taux de contribution exceptionnelle par tranches de revenus 
 * pour les personnes mariées ou pacsées
 * 
 * Nom de l'élément : 
 * 	T indique "taux"
 * 	Les deux premiers chiffres indiquent dans quelle tranche de revenu le taux est appliqué
 * 	Les quatres derniers indiquent l'année où le taux était effectif
 * 
 * Paramètre du constructeur : 
 * 	Le taux de contribution associé à la tranche de revenu
 */

public enum tauxContributionExceptionnelleCouples {

	// Les taux de la contribution exceptionnelle sur les hauts revenus pour les couples
    TCE00C_2024(0.0),
    TCE01C_2024(0.0),
    TCE02C_2024(0.03),
    TCE03C_2024(0.04);

	private double taux;

	tauxContributionExceptionnelleCouples(double taux) {
		this.taux = taux;
	}
	
	public double getTaux() {
		return this.taux;
	}
    
}
