package jeu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebuteLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private List<Limite> limites = new LinkedList<>();
	private List<Bataille> batailles = new LinkedList<>();
	private List<Borne> bornes = new ArrayList<>();

	public int donnerLimitationVitesse() {
		if (limites.isEmpty() || limites.get(0).getClass() == FinLimite.class) { 
			return 200;
		}
		return 50;
	}

	public int donnerKmParcourus() {
		int somme = 0;
		for (Borne borne : bornes) {
			somme += borne.getKm();
		}
		return somme;
	}

	public void deposer(Carte carte) {
		if (carte instanceof Borne borne) {
			bornes.add(borne);
		}
		if (carte instanceof Limite limite) {
			limites.add(0, limite);
		}
		if (carte instanceof Bataille bataille) {
			batailles.add(0, bataille);
		}
	}

	public boolean peutAvancer() {
		return !batailles.isEmpty() && batailles.get(0) == Cartes.FEU_VERT;	
		}

	private boolean estDepotFeuVertAutorise() {
		if (batailles.isEmpty()) {
			return true;
		}
		Bataille sommet = batailles.get(0);

		if (sommet == Cartes.FEU_ROUGE) {
			return true;
		}
		return sommet instanceof Parade && sommet != Cartes.FEU_VERT;
	}

	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm() <= donnerLimitationVitesse() && (donnerKmParcourus() + borne.getKm()) <= 1000;
	}

	private boolean estDepotLimiteAutorise(Limite limite) {

		if (limite instanceof DebuteLimite) {
			if (limites.isEmpty()) {
				return true;
			}
			return limites.get(0) instanceof FinLimite;
		}
		
		if (limite instanceof FinLimite) {
			if (limites.isEmpty()) {
				return false;
			}
			return limites.get(0) instanceof DebuteLimite;
		}
		
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
	    
	    if (bataille instanceof Attaque) {
	    	return peutAvancer();
	    }
	    
	    if (bataille instanceof Parade) {
	        if (bataille == Cartes.FEU_VERT) { 
	            return estDepotFeuVertAutorise();
	        } 
	        else {
	            if (this.batailles.isEmpty()) {
	                return false; 
	            }
	            Bataille sommet = this.batailles.get(0);
	            return sommet instanceof Attaque && bataille.getType() == sommet.getType();
	        }
	    }
	    return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Borne borne) {
            return estDepotBorneAutorise(borne);
        }
        if (carte instanceof Limite limite) {
            return estDepotLimiteAutorise(limite);
        }
        if (carte instanceof Bataille bataille) {
            return estDepotBatailleAutorise(bataille);
        }
        return false; 
    }
}