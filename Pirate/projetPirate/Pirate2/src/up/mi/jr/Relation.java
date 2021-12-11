package up.mi.jr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;

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
		deteste = new HashMap<Pirate, ArrayList<Pirate>>();
		preference = new HashMap<Pirate, ArrayList<Objet>>();
		listeObj = new HashMap<String,Objet>();
		listePirate = new HashMap<String,Pirate>();
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
	public String affichePreference() {
		StringBuffer buf = new StringBuffer();
		for(Pirate i : preference.keySet()) {
			buf.append("\n Voici sa liste de preférence du pirate " +i.getNom()+" : "+ preference.get(i));
		}
		return buf.toString();
	}
	
	
	/**
	 * Permet de retourner l'affichage d'un dictionnaire qui associe à un pirate, les pirates qu'il n'aime pas
	 * @return La liste des noms des pirates que chaque pirate n'aime pas
	 */
	public String afficheDeteste() {
		StringBuffer buf = new StringBuffer();
		for(Pirate i : deteste.keySet()) {
			buf.append("\n"+ i+ " n'aime pas "+ deteste.get(i));
		}
		return buf.toString();
	}	
	
	
	
	public String afficheListepirate() {
		StringBuffer buf = new StringBuffer();
		for(String i : listePirate.keySet()) {
			buf.append(listePirate.get(i).toString()+ "  ");
		}
		return buf.toString();
	}
	
	
	public String afficheListeObj() {
		StringBuffer buf = new StringBuffer();
		for(String i : listeObj.keySet()) {
			buf.append(listeObj.get(i).toString()+ "  ");
		}
		return buf.toString();
	}
	
	public void parser(File fichier) {
		String nom = null;
		String nomObjet = null;
		String nomsPirate = null;
		String nomsPirObj = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
			String ligne = null;
			while ((ligne = br.readLine()) != null) {
				
				if(ligne.startsWith("pirate") || ligne.startsWith("Pirate")) {
					nom = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
					listePirate.put(nom,new Pirate(nom));
					//System.out.println(afficheListepirate());
				}
				//TODO erreur null pour pirate 
				
				
				if(ligne.startsWith("objet") || ligne.startsWith("Objet")) {
					nomObjet = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
					listeObj.put(nomObjet,new Objet(nomObjet));
					//System.out.println(afficheListeObj());
				}
				
				if(ligne.startsWith("deteste") || ligne.startsWith("Deteste")) {
					nomsPirate = (ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")")));
//					//Traiter le cas où il rentre pls desteste ex 3
					Pirate p1 = listePirate.get(nomsPirate.split(",")[0]);
					Pirate p2 = listePirate.get(nomsPirate.split(",")[1]);
					if(deteste.containsKey(p1)) {
						deteste.get(p1).add(p2);
					}else {
						ArrayList<Pirate> deteste1 = new ArrayList<Pirate>();
						deteste1.add(p2);
						deteste.put(p1, deteste1);
					}
					if(deteste.containsKey(p2)) {
						deteste.get(p2).add(p1);
					}else {
						ArrayList<Pirate> deteste2 = new ArrayList<Pirate>();
						deteste2.add(p1);
						deteste.put(p2, deteste2);
					}
					
				}
				
				if(ligne.startsWith("preferences") || ligne.startsWith("Preferences") || ligne.startsWith("preference") || ligne.startsWith("preference")) {
					nomsPirObj = (ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")")));

					//A Traiter le cas où l'utilisateur oublie d'entrer le nom des 3 objets dans preference
					ArrayList<Objet> listObj = new ArrayList<Objet>();
					String [] EnsPirObj = nomsPirObj.split(",");
					for(int i = 1; i<EnsPirObj.length; i++) {
						listObj.add(new Objet(nomsPirObj.split(",")[i]));
						//TODO indexOutOfBonf
						
						//System.out.println(EnsPirObj[0]);
					}
					preference.put(listePirate.get(EnsPirObj[0]), listObj);
					//System.out.println(affichePreference());
				}
			
				
			}
		}catch (FileNotFoundException e) { 
			System.err.println("Le fichier saisi est introuvable");
			e. printStackTrace ();
		} catch (IOException e) { 
			System.err.println(e.getMessage());
			e. printStackTrace ();
		}
		
	}

	
	public void setDeteste(HashMap<Pirate, ArrayList<Pirate>> deteste) {
		this.deteste = deteste;
	}
	
	public void setPreference(HashMap<Pirate, ArrayList<Objet>> preference) {
		this.preference = preference;
	}
}
