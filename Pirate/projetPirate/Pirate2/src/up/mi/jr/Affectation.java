package up.mi.jr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Représente les affectations des objets aux pirates
 *
 * @author Abisha Jeyavel, Lalariniaina Ramanantoanina
 * @version 1.1
 */
public class Affectation {
	/**
	 * Dictionnaire qui associe à un pirate, l'objet qui lui est affecté 
	 */
	private HashMap<Pirate,Objet> pirateObjet;
	
	/**
	 * Dictionnaire représentant l'attribution de chaque objet à un pirate, 
	 * c'est-à-dire un dictionnaire qui associe à un objet, le pirate qui détient cet objet 
	 */
	private HashMap<Objet,Pirate> objetPirate;

	/**
	 * Constructeur qui initialise les dictionnaires vides
	 */
	public Affectation() {
		pirateObjet = new HashMap<>();
		objetPirate = new HashMap<>();
		
	}

	/**
	 * Constructeur qui initialise les dictionnaires à partir des dictionnaires donnés en paramètre
	 * @param piobj Dictionnaire qui associe à un pirate, l'objet qui lui est affecté
	 * @param objpi Dictionnaire qui associe à un objet, le pirate qui détient cet objet
	 */
	public Affectation(HashMap<Pirate,Objet> piobj, HashMap<Objet,Pirate> objpi) {
		pirateObjet = piobj;
		objetPirate = objpi;
		
	}
	
	/**
	 * Permet de retourner le dictionnaire des affectaions du butin
	 * @return le dictionnaire qui associe à un pirate, l'objet qui lui est affecté 
	 */
	public HashMap<Pirate,Objet> getPirateObjet(){
		return pirateObjet;
	}

	//TODO revoir la formulation il y a pas cde diff entre les deux et se
	/**
	 * Permet de retourner le dictionnaire des affectaions du butin
	 * @return le dictionnaire qui associe à un pirate, l'objet qui lui est affecté 
	 */
	public HashMap<Objet,Pirate> getObjetPirate(){
		return objetPirate;
	}

	/**
	 * Permet d'attribuer à un objet, le pirate qui détient cet objet
	 * @param rel Objet de type Relation qui représente les differentes relations entre les pirates
	 * et entre les pirates et les objets
	 */
	private void tempAffectation(Relation rel){
		for(Pirate p : rel.getPreference().keySet()) {
			int i=0;
			// l désigne la liste des objets déjà affecté
			ArrayList<Objet> l= new ArrayList<>(objetPirate.keySet());
			// On parcourt la liste de préférence d'un pirate jusqu'à trouver un objet qui n'est pas encore attribué à quelqu'un

			while( l.contains(rel.getPreference().get(p).get(i)) && i<rel.getPreference().size()-1 ){
				i++;
			}

			objetPirate.put(rel.getPreference().get(p).get(i), p);				
	     }
	}

	/**
	 * Permet d'attribuer un objet à chaque pirate
	 * @param rel Objet de type Relation qui représente les differentes relations entre les pirates
	 * et entre les pirates et les objets
	 */
	public void affectation(Relation rel){
		//Pour passer d'un dictionnaire associant à un objet, un pirate 
		//à un dictionnaire associant à un pirate, un objet
		tempAffectation(rel);
		for(Objet i : objetPirate.keySet()) {
			this.pirateObjet.put(objetPirate.get(i), i);
		}
	}

	/**
	 * Permet de retourner l'affichage d'un dictionnaire représentant l'attribution d'un objet à chaque pirate
	 * @return L'affichage d'un dictionnaire qui associe à chaque pirate, l'objet qui lui est affecté
	 */
	public String afficheAffectation() {
		StringBuilder buf = new StringBuilder();
		for(Pirate i : pirateObjet.keySet()) {
			buf.append(i.getNom()).append(" : ").append(pirateObjet.get(i).toString()).append("\n");
		}
		return buf.toString();
	}

	/**
	 * Permet d'échanger l'affectation d'objet de deux pirates
	 * @param p1 le premier pirate
	 * @param p2 le second pirate
	 * @return le dictionnaire représentant l'attribution d'un objet à chaque pirate
	 */
	public Affectation echange(Pirate p1, Pirate p2) {
		HashMap < Objet,Pirate> objpi1 = new HashMap<>(objetPirate);
		HashMap<Pirate, Objet>  piobj1 = new HashMap<>(pirateObjet);
		Affectation affecnew = new Affectation(piobj1,objpi1);
		Objet temp = affecnew.getPirateObjet().get(p1);
		affecnew.getPirateObjet().replace(p1, affecnew.getPirateObjet().get(p2));
		affecnew.getPirateObjet().replace(p2, temp);
		affecnew.getObjetPirate().replace(affecnew.getPirateObjet().get(p1),p1);
		affecnew.getObjetPirate().replace(affecnew.getPirateObjet().get(p2),p2);
		return affecnew;
	}
	/**
	 * Permet d'échanger l'affectation d'objet de deux pirates (sans créer de nouvel objet de type Affectation afin de préserver les échanges d'objets)
	 * @param p1 le premier pirate
	 * @param p2 le second pirate
	 */
	public void echangeNew(Pirate p1, Pirate p2) {
		Objet temp = pirateObjet.get(p1);
		pirateObjet.replace(p1, pirateObjet.get(p2));
		pirateObjet.replace(p2, temp);
		objetPirate.replace(pirateObjet.get(p1),p1);
		objetPirate.replace(pirateObjet.get(p2),p2);
	}
	
	
}
