package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {
	private List<Limite> limites =  new ArrayList<>();
	private List<Bataille> bataille =  new ArrayList<>();
	private List<Borne> borne =  new ArrayList<>();

	
	
	
	public int donnerLimitationVitesse() {
		if( limites.isEmpty() ||limites.get(0).getClass() == FinLimite.class) {
			return 200;
		}
		return 50;
	}
	
	
	public int donnerKmParcourus() {
		int somme=0;
		for (Borne borne : borne) {
			somme += borne.getKm();
		}
		return somme;
	}
	
	public void deposer(Carte c) {
		if(c instanceof Borne) {
			borne.add((Borne) c);
		}
		if(c instanceof Limite) {
			limites.add((Limite) c);
		}
		if(c instanceof Bataille) {
			bataille.add((Bataille) c);
		}
	}
	
}
