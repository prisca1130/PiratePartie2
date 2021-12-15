package up.mi.jr;

//partie 2

/**
* Représente un objet avec son nom (qui est un entier)
* 
* @author Abisha Jeyavel, Lalariniaina Ramanantoanina
* @version 2.1
*/
public class Objet {
	/**
	 * Le nom de l'objet 
	 */
	private String nom;

	/**
	 * Constructeur pour un objet
	 * @param nom Le nom de l'objet 
	 */
	public Objet(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Permet d'obtenir le nom de l'objet
	 * @return le nom de l'objet
	 */
	public String getNom() {
		return nom;
	}
	
	@Override
	public String toString(){
		return "l'objet : "+ this.getNom();
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
		Objet other = (Objet) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
