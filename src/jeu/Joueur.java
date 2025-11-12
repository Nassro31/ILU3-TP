package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zone = new ZoneDeJeu();
	private MainJoueur mainJoueur = new MainJoueur(); 
	
	public Joueur(String nom) {
		this.nom = nom;
	} 
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Joueur joueur){
			return this.nom.equals(joueur.nom);
		}
		return false;
	}

	@Override
	public String toString() {
		return nom;
	}
	
	public void donner(Carte carte) {
		mainJoueur.prendre(carte);
	}

	public Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide()) {
			return null;
		}
		Carte carte = sabot.piocher();
		donner(carte);
		return carte;
	}
	
	public void deposer(Carte carte) {
		zone.deposer(carte);
	}
	
	public int donnerKmParcourus() {
		return zone.donnerKmParcourus();
	}
	
	public boolean estDepotAutoriser(Carte carte) {
		return zone.estDepotAutorise(carte);
	}
}
