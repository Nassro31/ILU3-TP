package testsFonctionnels;

import cartes.*;

public class TestMethodeEquals {

	public static void main(String[] args) {
		//borne
		Borne borne1 = new Borne(25);
		Borne borne2 = new Borne(25);
		
		//feu rouge
		Attaque feu1 = new Attaque(Type.FEU);  // Attaque : Feu rouge
		Attaque feu2 = new Attaque(Type.FEU);  // Attaque : Feu rouge
		
		//feu vert  
		Parade feu3 = new Parade(Type.FEU);  // Parade : Feu vert
		Attaque feu4 = new Attaque(Type.FEU); // Attaque : Feu rouge
		
		System.out.println("teste deux borne a 25km : " + borne1.equals(borne2));
		System.out.println("teste deux feu rouge : " + feu1.equals(feu2));
		System.out.println("teste un feu vert avec un feu rouge : " + feu3.equals(feu4));
		
		
	}

}