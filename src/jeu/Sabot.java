package jeu;

import java.util.Iterator;
import cartes.Carte;

public class Sabot implements Iterable<Carte> {
    private int nbCartes;
    private Carte[] cartes;

    public Sabot(Carte[] cartes) {
        this.cartes = cartes;
        this.nbCartes = cartes.length;
    }

    public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(Carte carte) {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("le sabot est plein");
        }
        cartes[nbCartes] = carte;
        nbCartes++;
    }
    public Iterator<Carte> iterator() { return new Iterateur(); }


    private class  Iterateur implements iterator<cartes>{{
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < nbCartes;
            }

            @Override
            public Carte next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return cartes[index++];
            }

            @Override
            public void remove() {

                if (index == 0 || cartes[index - 1] == null) {
                    throw new IllegalStateException("Aucune carte à supprimer");
                }
                System.arraycopy(cartes, index, cartes, index - 1, nbCartes - index);
                cartes[--nbCartes] = null;
            }
        };
    }
}
