package jeu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	//TODO class ListIt
	private List<Carte> main =  new LinkedList<>();
 

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

	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();
	}
	
	

	
	
}
