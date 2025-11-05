package jeu;

import java.util.ArrayList;
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
	private List<Limite> limites = new ArrayList<>();
	private List<Bataille> bataille = new ArrayList<>();
	private List<Borne> borne = new ArrayList<>();

	public int donnerLimitationVitesse() {
		if (limites.isEmpty() || limites.get(0).getClass() == FinLimite.class) { // verifier avec le prof si c le 0 le
																					// sommet ou si c le dernier
																					// bataille.size() - 1
			return 200;
		}
		return 50;
	}

	public int donnerKmParcourus() {
		int somme = 0;
		for (Borne borne : borne) {
			somme += borne.getKm();
		}
		return somme;
	}

	public void deposer(Carte c) {
		if (c instanceof Borne) {
			borne.add(0, (Borne) c);
		}
		if (c instanceof Limite) {
			limites.add(0, (Limite) c);
		}
		if (c instanceof Bataille) {
			bataille.add(0, (Bataille) c);
		}
	}

	public boolean peutAvancer() {
		return !bataille.isEmpty() && bataille.get(0) == Cartes.FEU_VERT;	
		}

	public boolean estDepotFeuVertAutorise() {
		if (bataille.isEmpty()) {
			return true;
		}
		Bataille sommet = bataille.get(0);

		if (sommet == Cartes.FEU_ROUGE) {
			return true;
		}
		return sommet instanceof Parade && sommet != Cartes.FEU_VERT;
	}

	public boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm() <= donnerLimitationVitesse() && (donnerKmParcourus() + borne.getKm()) <= 1000;
	}

	public boolean estDepotLimiteAutorise(Limite limite) {

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
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
	    
	    if (bataille instanceof Attaque) {
	        
	        if (this.bataille.isEmpty()) {
	            return true; //verifier avec la prof pk ca serait false voir test 1 
	        }
	        
	        Bataille sommet = this.bataille.get(0);
	        return sommet instanceof Parade;
	    }
	    
	    if (bataille instanceof Parade) {
	        if (bataille == Cartes.FEU_VERT) { 
	            return estDepotFeuVertAutorise();
	        } 
	        else {
	            if (this.bataille.isEmpty()) {
	                return false; 
	            }
	            Bataille sommet = this.bataille.get(0);
	            return sommet instanceof Attaque && bataille.getType() == sommet.getType();
	        }
	    }
	    return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Borne) {
            return estDepotBorneAutorise((Borne) carte);
        }
        if (carte instanceof Limite) {
            return estDepotLimiteAutorise((Limite) carte);
        }
        if (carte instanceof Bataille) {
            return estDepotBatailleAutorise((Bataille) carte);
        }
        return false; 
    }
}
