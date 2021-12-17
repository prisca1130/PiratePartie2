package up.mi.jr;

//partie 2

/**
* Représente un objet avec son nom
* 
* @author Abisha Jeyavel, Lalariniaina Ramanantoanina
* @version 1.1
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
	public boolean equals(Object o) {
		if((o == null) || !(o instanceof Objet)) {
			return false;
		}
		if (o == this) {
			return true;
		}
		Objet nvObjet = (Objet) o;
		return (nvObjet.getNom()==(this.nom));
	}
}
