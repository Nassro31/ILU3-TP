package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.DebuteLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {
	public static void main(String[] args) {
		// TP3 PARTIE 2
		ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
		System.out.println("Deposer carte 25 km");
		zoneDeJeu.deposer(new Borne(25));
		System.out.println("Deposer carte 50 km");
		zoneDeJeu.deposer(new Borne(50));
		System.out.println("Deposer carte 75 km");
		zoneDeJeu.deposer(new Borne(75));
		System.out.println("Total des bornes : " + zoneDeJeu.donnerKmParcourus());

		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());
		zoneDeJeu.deposer(new DebuteLimite());
		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());
		zoneDeJeu.deposer(new FinLimite());
		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());
	}
}