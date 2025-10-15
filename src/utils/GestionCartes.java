package utils;
import java.lang.reflect.Array;
import java.util.*;


public class GestionCartes {

	
    public static <T> T extraire(List<T> liste){
        Random rng = new Random();
        Iterable<T> iterable = new ArrayList<>();
        int rindex = rng.nextInt(liste.size());
        T elem = liste.get(rindex);
        liste.remove(elem);
        return elem;
    }
}
