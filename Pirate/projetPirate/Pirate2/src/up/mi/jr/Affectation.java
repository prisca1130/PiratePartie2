package up.mi.jr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
	
	public Affectation() {
		pirateObjet = new HashMap<Pirate,Objet>();
		objetPirate = new HashMap<Objet,Pirate>();
		
	}
	
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
	
	/**
	 * Permet de retourner le dictionnaire des affectaions du butin
	 * @return le dictionnaire qui associe à un pirate, l'objet qui lui est affecté 
	 */
	public HashMap<Objet,Pirate> getObjetPirate(){
		return objetPirate;
	}
	
	/**
	 * Permet d'attribuer un objet à chaque pirate 
	 * @return Dictionnaire représentant l'attribution d'un objet à chaque pirate
	 */
	public HashMap<Objet, Pirate> tempaffect(Relation rel){
		//TODO Preference pas vide 
		for(Pirate p : rel.getPreference().keySet()) {
			int i=0;
			// l désigne la liste des objets déjà affecté
			ArrayList<Objet> l= new ArrayList<Objet>(objetPirate.keySet());	
			// On parcourt la liste de préférence d'un pirate jusqu'à trouver un objet qui n'est pas encore attribué à quelqu'un
			while( l.contains(rel.getPreference().get(p).get(i)) && i<rel.getPreference().size()-1 ){
				i++;
			}
			objetPirate.put(rel.getPreference().get(p).get(i), p);				
	     }
		return objetPirate;		
	}
	
	public HashMap<Pirate, Objet> affectation(){
		//Pour passer d'un dictionnaire associant à un objet, un pirate 
		//à un dictionnaire associant à un pirate, un objet
		for(Objet i : objetPirate.keySet()) {
			this.pirateObjet.put(objetPirate.get(i), i);
		}
		return pirateObjet;
	}
	
	/**
	 * Permet de retourner l'affichage d'un dictionnaire représentant l'attribution d'un objet à chaque pirate
	 * @return L'affichage d'un dictionnaire représentant l'pirateObjet d'un objet à chaque pirate
	 */
	public String affichepirateObjet() {
		StringBuffer buf = new StringBuffer();
		for(Pirate i : pirateObjet.keySet()) {
			buf.append(i.getNom()+ " : " + pirateObjet.get(i).toString() + "\n");
		}
		return buf.toString();
	}
	
	/**
	 * Permet de retourner l'affichage d'un dictionnaire représentant l'attribution d'un objet à chaque pirate
	 * @return L'affichage d'un dictionnaire représentant l'pirateObjet d'un objet à chaque pirate
	 */
	public String afficheobjetPirate() {
		StringBuffer buf = new StringBuffer();
		for(Objet i : objetPirate.keySet()) {
			buf.append(i.getNom()+ " : " + objetPirate.get(i).toString() + "\n");
		}
		return buf.toString();
	}
	
	private HashMap<Pirate, Objet> copPiobj(){
		HashMap <Pirate, Objet> piobj = new HashMap<Pirate, Objet>(); 
		for (Map.Entry<Pirate, Objet> entry : pirateObjet.entrySet()){
			Pirate key = entry.getKey();
			Objet value = entry.getValue();
			piobj.put(key, value);
		}
		return piobj;
	}
	
	
	
	private HashMap< Objet,Pirate> copObjpi(){
		HashMap < Objet,Pirate> objpi = new HashMap< Objet,Pirate>(); 
		for (Map.Entry< Objet,Pirate> entry : objetPirate.entrySet()){
			Objet key = entry.getKey();
			Pirate value = entry.getValue();
			objpi.put(key, value);
		}
		return objpi;
	}
	

	/**
	 * Permet d'échanger l'affectation d'objet de deux pirates 
	 * @param p1 le premier pirate
	 * @param p2 le second pirate
	 */
	public Affectation echange(Pirate p1, Pirate p2){
		HashMap < Objet,Pirate> objpi = copObjpi();
		HashMap<Pirate, Objet>  piobj = copPiobj();
        Affectation affecnew = new Affectation(piobj,objpi);
		Objet temp = affecnew.getPirateObjet().get(p1);
		affecnew.getPirateObjet().replace(p1, affecnew.getPirateObjet().get(p2));
		affecnew.getPirateObjet().replace(p2, temp);
		affecnew.getObjetPirate().replace(affecnew.getPirateObjet().get(p1),p1);
		affecnew.getObjetPirate().replace(affecnew.getPirateObjet().get(p2),p2);
		return affecnew;
	}
	
	
}
