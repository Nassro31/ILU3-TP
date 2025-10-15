package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	private List<Carte> main =  new ArrayList<>();
 

	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert main.contains(carte);
		main.remove(carte);
	}

	@Override
	public String toString() { // faire avec un stringbuilder
		String chaine = ""; 
		for (Iterator<Carte> iterator = main.iterator() ; iterator.hasNext();) {
			Carte carte = iterator.next();
			chaine +=  carte.toString(); 
		}
		return chaine; 
	}
	
	
	
}
