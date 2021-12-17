package up.mi.jr;

//Partie 2

/** 
* Représente un pirate avec son nom
*  
* @author Abisha Jeyavel, Lalariniaina Ramanantoanina
* @version 1.1
*/
public class Pirate {
	/**
	 * Le nom du pirate
	 */
	private String nom;
	
	/**
	 * Constructeur pour un pirate 
	 * @param nom Le nom du pirate
	 */
	public Pirate(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Permet d'obtenir le nom du pirate
	 * @return le nom du pirate
	 */
	public String getNom() {
		return nom;
	}


	@Override
	public boolean equals(Object o) {
		if((o == null) || !(o instanceof Pirate)) {
			return false;
		}
		if (o == this) {
			return true;
		}
		Pirate nvPirate = (Pirate) o;
		return (nvPirate.getNom().equals(this.nom));
	}

	@Override
	public String toString() {
		return "Le pirate " + nom ;
	}
}
