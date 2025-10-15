package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private int nbCartes;
	private Carte[] cartes;
	private int nombreOperations =  0;
	

	public Sabot(Carte[] cartes) {
		this.nbCartes = cartes.length;
		this.cartes = new Carte[nbCartes];
		System.arraycopy(cartes, 0, this.cartes, 0, nbCartes);
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new IllegalStateException("Sabot plein : impossible d'ajouter une carte");
		}
		cartes[nbCartes] = carte;
		nbCartes ++;
		nombreOperations++;
	}
	

	public Carte piocher(){
		if (estVide()) {
            throw new NoSuchElementException("Sabot vide : impossible de piocher");
        }
        Iterator<Carte> it = iterator() ;
        Carte carte = it.next();
        it.remove();
        return carte;
    }
	
	@Override
	public Iterator<Carte> iterator() {
		return new SabotIterator();
	}
	
	// class iterable
	
	private class SabotIterator implements Iterator<Carte>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationReference = nombreOperations;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
	
		@Override
		public Carte next() {
			verificationConcurrence();
			if(hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true ;
				return carte;
			}else {
				throw new NoSuchElementException();
			}
		}
	
		@Override
		public void remove() {
			verificationConcurrence();
			if(nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for(int i= indiceIterateur-1; i<nbCartes-1; i++) {
				cartes[i]=cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nombreOperations++;
			nombreOperationReference++;
		}
		
		private void verificationConcurrence() {
			if(nombreOperations != nombreOperationReference) {
				throw new ConcurrentModificationException();
			}
		}
	}

	
}