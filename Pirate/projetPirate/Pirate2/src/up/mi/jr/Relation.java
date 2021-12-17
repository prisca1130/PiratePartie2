package up.mi.jr;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Représente les differentes relations entre les pirates et les objets, 
 * avec son dictionnaire deteste, son dictionnaire preference, son dictionnaire listePirate et son dictionnaire listeObj
 *
 * @author Abisha Jeyavel, Lalariniaina Ramanantoanina
 * @version 1.1
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
	 * @throws PirateException on relaie le traitement de l'exception au niveau de la méthode « appelante »
	 */
	public String affichePreference() throws PirateException {
		if (preference.isEmpty()) {
			throw new PirateException("La liste de préference de tous les pirates n'a pas été fait");
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
	 * @throws PirateException on relaie le traitement de l'exception au niveau de la méthode « appelante »
	 */
	public String afficheDeteste() throws PirateException {
		if (deteste.isEmpty()) {
			throw new PirateException("Les relations d'affinité de tous les pirates n'ont pas été fait");
		}
		StringBuilder buf = new StringBuilder();
		for(Pirate i : deteste.keySet()) {
			buf.append("\n").append(i).append(" n'aime pas ").append(deteste.get(i));
		}
		return buf.toString();
	}

	/**
	 * Permet de retourner l'affichage de la liste des noms de pirates
	 * @return la liste des noms de pirates
	 * @throws PirateException on relaie le traitement de l'exception au niveau de la méthode « appelante »
	 */
	public String afficheListepirate() throws PirateException {
		if (listePirate.isEmpty()) {
			throw new PirateException("La liste des pirates est vide");
		}
		StringBuilder buf = new StringBuilder();
		for(String i : listePirate.keySet()) {
			buf.append(listePirate.get(i).toString()).append("  ");
		}
		return buf.toString();
	}

	/**
	 * Permet de retourner l'affichage de la liste des noms des objets
	 * @return la liste des noms des objets
	 * @throws PirateException on relaie le traitement de l'exception au niveau de la méthode « appelante »
	 */
	public String afficheListeObj() throws PirateException {
		if (listeObj.isEmpty()) {
			throw new PirateException("La liste des objets est vide");
		}
		StringBuilder buf = new StringBuilder();
		for(String i : listeObj.keySet()) {
			buf.append(listeObj.get(i).toString()).append("  ");
		}
		return buf.toString();
	}
}
