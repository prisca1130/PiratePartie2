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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pirate other = (Pirate) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Le pirate " + nom ;
	}
}
