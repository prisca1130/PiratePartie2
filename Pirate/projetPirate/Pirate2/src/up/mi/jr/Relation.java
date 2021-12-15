package up.mi.jr;

import java.util.ArrayList;
import java.util.HashMap;


/** 
 * Représente les differentes relations entre les pirates et les objets, 
 * avec son dictionnaire deteste, son dictionnaire preference, son dictionnaire affectation et son dictionnaire tempAffec 
 *  
 * @author Abisha Jeyavel, Lalariniaina Ramanantoanina
 * @version 2.0
 */


public class Relation {
	/**
	 * Dictionnaire qui associe à un pirate, les pirates qu'il n'aime pas
	 */
	private HashMap<Pirate, ArrayList<Pirate>> deteste;
	
	/**
	 * Dictionnaire qui associe à un pirate, une liste d'objets rangés dans son ordre de préférence
	 */	
	private HashMap<Pirate, ArrayList<Objet>> preference;
	
	/**
	 * Dictionnaire représentant la liste de pirate, 
	 * c'est-à-dire un dictionnaire qui associe à un nom, le pirate qui correspond
	 */
	private HashMap<String,Pirate> listePirate;
	
	/**
	 * Dictionnaire représentant la liste des objets, 
	 * c'est-à-dire un dictionnaire qui associe à un nom, l'objet qui correspond
	 */
	private HashMap<String,Objet> listeObj;
	
	
	
	/**
	 * Constructeur qui initialise les dictionnaires vides
	 */
	public Relation() {
		deteste = new HashMap<>();
		preference = new HashMap<>();
		listeObj = new HashMap<>();
		listePirate = new HashMap<>();
	}
	

	/**
	 * Permet de retourner le dictionnaire deteste qui associe à un pirate, les pirates qu'il n'aime pas
	 * @return le dictionnaire deteste qui associe à un pirate, les pirates qu'il n'aime pas
	 */
	public HashMap<Pirate, ArrayList<Pirate>> getdeteste(){
		return deteste;
	}
	
	/**
	 * Permet de retourner le dictionnaire preference 
	 * qui associe à un pirate, une liste d'objets rangés dans son ordre de préférence
	 * @return le dictionnaire preference qui associe à un pirate, une liste d'objets rangés dans son ordre de préférence
	 */
	public HashMap<Pirate, ArrayList<Objet>> getPreference(){
		return preference;
	}
	
	
    /**
     * Permet de retourner le dictionnaire de la liste des pirates
     * @return la liste des pirate
     */
	public HashMap<String, Pirate> getListePirate(){
		return listePirate;
	}
	
	/**
	 * Permet de retourner le dictionnaire du butin
	 * @return la liste des objets
	 */
	public HashMap<String, Objet> getListeObj(){
		return listeObj;
	}
	
	
	

	
	
	/**
	 * Permet de retourner l'affichage d'un dictionnaire représentant les préférences de chaque pirate
	 * @return La liste des objets rangés dans l'ordre de préférence de chaque pirate
	 */
	public String affichePreference() throws EmptyObject {
		if (preference.isEmpty()) {
			throw new EmptyObject("La liste de préference de tous les pirates n'a pas été fait");
		}
		StringBuilder buf = new StringBuilder();
		for(Pirate i : preference.keySet()) {
			buf.append("\n Voici la liste de preférence du pirate ").append(i.getNom()).append(" : ").append(preference.get(i));
		}
		return buf.toString();
	}
	
	
	/**
	 * Permet de retourner l'affichage d'un dictionnaire qui associe à un pirate, les pirates qu'il n'aime pas
	 * @return La liste des noms des pirates que chaque pirate n'aime pas
	 */
	public String afficheDeteste() throws EmptyObject{
		if (deteste.isEmpty()) {
			throw new EmptyObject("Les relations d'affinité de tous les pirates n'ont pas été fait");
		}
		StringBuilder buf = new StringBuilder();
		for(Pirate i : deteste.keySet()) {
			buf.append("\n").append(i).append(" n'aime pas ").append(deteste.get(i));
		}
		return buf.toString();
	}	
	
	
	
	public String afficheListepirate() throws EmptyObject {
		if (listePirate.isEmpty()) {
			throw new EmptyObject("La liste des pirates est vide");
		}
		StringBuilder buf = new StringBuilder();
		for(String i : listePirate.keySet()) {
			buf.append(listePirate.get(i).toString()).append("  ");
		}
		return buf.toString();
	}
	
	
	public String afficheListeObj() throws EmptyObject {
		if (listeObj.isEmpty()) {
			throw new EmptyObject("La liste des objets est vide");
		}
		StringBuilder buf = new StringBuilder();
		for(String i : listeObj.keySet()) {
			buf.append(listeObj.get(i).toString()).append("  ");
		}
		return buf.toString();
	}
}
