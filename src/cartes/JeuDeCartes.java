package cartes;

public class JeuDeCartes {
    private Configuration[] typesDeCartes = { new Configuration(new Borne(25), 10),
    	 new Configuration(new Borne(50), 10),
    	new Configuration(new Borne(75), 10),
    	new Configuration(new Borne(100), 12),
        new Configuration(new Borne(200), 4),
        new Configuration(new DebuteLimite(), 6),
        new Configuration(new FinLimite(), 4),
         new Configuration(new Parade(Type.FEU), 14),
        new Configuration(new Parade(Type.ESSENCE), 6),
        new Configuration(new Parade(Type.CREVAISON), 6),
        new Configuration(new Parade(Type.ACCIDENT), 6),
        new Configuration(new Attaque(Type.FEU), 5),
        new Configuration(new Attaque(Type.ESSENCE), 3),
        new Configuration(new Attaque(Type.CREVAISON), 3),
        new Configuration(new Attaque(Type.ACCIDENT), 3),
        new Configuration(new Botte(Type.FEU), 1),
         new Configuration(new Botte(Type.ESSENCE), 1),
        new Configuration(new Botte(Type.ACCIDENT), 1),
        new Configuration(new Botte(Type.CREVAISON), 1),
    };
    
    public boolean checkCount(){
        int somme = 0;
	    for (Configuration config : typesDeCartes) {
            somme += config.getNbExemplaires();  
        }
        return somme == 106;
    }
	
	public Carte[] donnerCartes() {
	    int total = 0;
	    for (Configuration config : typesDeCartes) {
	        total += config.getNbExemplaires();
	    }

	    Carte[] toutesLesCartes = new Carte[total];

	    for (int i = 0, index = 0; i < typesDeCartes.length; i++) {
	        Carte carte = typesDeCartes[i].getCarte();
	        int nb = typesDeCartes[i].getNbExemplaires();
	        for (int j = 0; j < nb; j++) {
	            toutesLesCartes[index++] = carte; 
	        }
	    }

	    return toutesLesCartes;
	}

	public String affichageJeuDeCartes() {
        StringBuilder sb = new StringBuilder();
        for (Configuration config : typesDeCartes) {
            sb.append(config.getCarte().toString())
              .append(" nb:  ")
              .append(config.getNbExemplaires())
              .append("\n");
        }
        return sb.toString();
    }
	
	
	
    private static class Configuration {
        private int nbExemplaires;
        private Carte carte;

        private Configuration(Carte carte, int nbExemplaires){
            this.nbExemplaires = nbExemplaires;
            this.carte = carte;
        }
        public Carte getCarte(){
            return carte;
        }
        public int getNbExemplaires(){
            return nbExemplaires;
        }
    }
	
}

