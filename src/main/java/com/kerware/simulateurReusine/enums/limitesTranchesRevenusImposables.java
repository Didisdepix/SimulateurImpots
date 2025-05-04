package com.kerware.simulateurReusine.enums;

/**
 * Enumération définissant les tranches de revenus imposables
 * 
 * Nom de l'élément : 
 * 	L indique "limite"
 * 	Les deux premiers chiffres indiquent dans quelle tranche le revenu se situe
 * 	Les quatres derniers indiquent l'année où la limite était effective
 * 
 * Paramètre du constructeur : 
 * 	Le revenu déclaré minimum pour passer dans cette catégorie
 */
public enum limitesTranchesRevenusImposables {

	// Les limites des tranches de revenus imposables
	L00_2024(0),
	L01_2024(11294),
	L02_2024(28797),
	L03_2024(82341),
	L04_2024(177106),
	L05_2024(Integer.MAX_VALUE);
	
	private int limite;
	
	limitesTranchesRevenusImposables(int limite) {
		this.limite = limite;
	}
	
	public int getLimite() {
		return this.limite;
	}
}
