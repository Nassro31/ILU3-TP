package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zone;
	private MainJoueur mainJoueur = new MainJoueur(); 
	
	public Joueur(String nom, ZoneDeJeu zone) {
		this.nom = nom;
		this.zone = zone;
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
		return "Joueur [nom=" + nom + "]";
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
	
}
