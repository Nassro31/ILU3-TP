package testsFonctionnels;

import cartes.*;
import jeu.Sabot;

import java.util.Iterator;

public class TestSabot {
    JeuDeCartes jeu = new JeuDeCartes();
    Sabot sabot = new Sabot(jeu.donnerCartes());

    public void debut() {

		while (!sabot.estVide()) {
			Carte carte = sabot.piocher();
			System.out.println("Je pioche " + carte);
		}
    }

    public void suite() {
		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			System.out.println("Je pioche " + iterator.next());
			iterator.remove();
		}
    }

    public void fin() {
		Carte cartePiochee = sabot.piocher();
		System.out.println("Je pioche " + cartePiochee);
		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			System.out.println("Je pioche " + carte);
			iterator.remove();
			cartePiochee = sabot.piocher();
			sabot.ajouterCarte(new Botte(cartes.Type.ACCIDENT));
		}
		Iterator<Carte> iterator = sabot.iterator();
		System.out.println("\nLa pioche contient encore des cartes ? " + iterator.hasNext());
    }

    public static void main(String[] args) {
        TestSabot testPioche = new TestSabot();
		//testPioche.debut();
		//testPioche.suite();
		//testPioche.fin();
    }

}