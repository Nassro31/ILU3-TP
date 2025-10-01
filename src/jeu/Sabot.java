package jeu;
import cartes.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Sabot implements Iterable<Carte>{
    private int nbCartes;
    private Carte[] cartes;
    private int nbOperation = 0;

    public Sabot(Carte[] cartes){
        nbCartes = cartes.length;
        this.cartes = new Carte[nbCartes];
        for (int i = 0; i < nbCartes; i++) {
            this.cartes[i] = cartes[i];
        }
    }
    
    public boolean estVide(){
        return nbCartes == 0;
    }
    
    public void ajouterCarte(Carte carte) {
        if (nbCartes >= cartes.length){
            throw new IllegalStateException("Dépassement de capacité");
        }
        cartes[nbCartes] = carte;
        nbCartes++;
        nbOperation++;
    }
    
    public Carte piocher() {
        Iterator<Carte> it = this.iterator();
        if (!it.hasNext()) {
            throw new IllegalStateException("Sabot vide");
        }
        Carte premiereCarte = it.next();
        it.remove();
        return premiereCarte;
    }

    
    public Iterator<Carte> iterator() {
        return new SabotIterator();
    }
    
    private class SabotIterator implements Iterator<Carte> {
        private int indiceIter = 0;
        private int derElt = -1;
        private int opRef = nbOperation;

        @Override
        public boolean hasNext() {
            return indiceIter < nbCartes;
        }
        
        @Override
        public Carte next() {
            verifierConcurrence();
            if (indiceIter >= nbCartes)
                throw new NoSuchElementException();
            derElt = indiceIter;
            return cartes[indiceIter++];
        }

		private void verifierConcurrence() {
			if (nbOperation != opRef)
                throw new ConcurrentModificationException();
		}
        
        @Override
        public void remove() {
            if (derElt < 0)
                throw new IllegalStateException();
            verifierConcurrence();
            for (int i = derElt; i < nbCartes - 1; i++) {
                cartes[i] = cartes[i+1];
            }
            cartes[nbCartes-1] = null;
            nbCartes--;
            nbOperation++;
            opRef++;
            indiceIter = derElt;
            derElt = -1;
        }
    }
}