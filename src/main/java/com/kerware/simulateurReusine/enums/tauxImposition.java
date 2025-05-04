package com.kerware.simulateurReusine.enums;

/**
 * Enumération définissant les taux d'impositions par tranches de revenus
 * 
 * Nom de l'élément : 
 * 	T indique "taux"
 * 	Les deux premiers chiffres indiquent dans quelle tranche de revenu le taux est appliqué
 * 	Les quatres derniers indiquent l'année où le taux était effectif
 * 
 * Paramètre du constructeur : 
 * 	Le taux d'imposition associé à la tranche de revenu
 */

public enum tauxImposition {
	
	// Les taux d'imposition par tranche
    T00_2024(0.0),
    T01_2024(0.11),
    T02_2024(0.3),
    T03_2024(0.41),
    T04_2024(0.45);

	private double taux;
	
	tauxImposition(double taux) {
		this.taux = taux;
	}
	
	public double getTaux() {
		return this.taux;
	}
}
