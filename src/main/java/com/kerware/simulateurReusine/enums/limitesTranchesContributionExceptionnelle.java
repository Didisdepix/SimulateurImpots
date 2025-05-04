package com.kerware.simulateurReusine.enums;

/**
 * Enumération définissant les tranches de revenus imposant une contribution exceptionnelle
 * 
 * Nom de l'élément : 
 * 	L indique "limite"
 * 	Les deux premiers chiffres indiquent dans quelle tranche le revenu se situe
 * 	Les quatres derniers indiquent l'année où la limite était effective
 * 
 * Paramètre du constructeur : 
 * 	Le revenu déclaré minimum pour passer dans cette catégorie
 */

public enum limitesTranchesContributionExceptionnelle {
	
	// Les limites des tranches pour la contribution exceptionnelle sur les hauts revenus
    LCE00_2024(0),
    LCE01_2024(250000),
    LCE02_2024(500000),
    LCE03_2024(1000000),
    LCE04_2024(Integer.MAX_VALUE);

	private int limite;
	
	limitesTranchesContributionExceptionnelle(int limite) {
		this.limite=limite;
	}
	
	public int getLimite() {
		return this.limite;
	}
}
