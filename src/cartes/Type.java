package cartes;

public enum Type {
	FEU("Rouge", "Vert", "Prioritaire")
	, ESSENCE("Panne d'essence","Essence", null)
	, CREVAISON("Crevaison", "Roue de secours", null),
	ACCIDENT("ACCIDENT", "RÉPARATIONS", null);

	Type(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
}
