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
		pirateObjet = new HashMap<>();
		objetPirate = new HashMap<>();
		
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
	
	public HashMap<Pirate, Objet> affectation(Relation rel){
		//Pour passer d'un dictionnaire associant à un objet, un pirate 
		//à un dictionnaire associant à un pirate, un objet
		tempAffectation(rel);
		if (objetPirate.isEmpty()) {
			throw new NullPointerException("Il y'a eu un problème au niveau de l'affectation des objets");
		}
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
		StringBuilder buf = new StringBuilder();
		for(Pirate i : pirateObjet.keySet()) {
			buf.append(i.getNom()).append(" : ").append(pirateObjet.get(i).toString()).append("\n");
		}
		return buf.toString();
	}

	
	private HashMap<Pirate, Objet> copPiobj(){
		HashMap <Pirate, Objet> piobj = new HashMap<>();
		for (Map.Entry<Pirate, Objet> entry : pirateObjet.entrySet()){
			Pirate key = entry.getKey();
			Objet value = entry.getValue();
			piobj.put(key, value);
		}
		return piobj;
	}
	
	
	
	private HashMap< Objet,Pirate> copObjpi(){
		HashMap < Objet,Pirate> objpi = new HashMap<>();
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
	public Affectation echange(Pirate p1, Pirate p2) throws NullPointerException{
		HashMap < Objet,Pirate> objpi = copObjpi();
		HashMap<Pirate, Objet>  piobj = copPiobj();
		// TODO vraiment nécessaire??
		if (objpi.isEmpty() && piobj.isEmpty()) {
			throw new NullPointerException("Il y'a eu un problème au niveau de l'échange");
		}
        Affectation affecnew = new Affectation(piobj,objpi);
		Objet temp = affecnew.getPirateObjet().get(p1);
		affecnew.getPirateObjet().replace(p1, affecnew.getPirateObjet().get(p2));
		affecnew.getPirateObjet().replace(p2, temp);
		affecnew.getObjetPirate().replace(affecnew.getPirateObjet().get(p1),p1);
		affecnew.getObjetPirate().replace(affecnew.getPirateObjet().get(p2),p2);
		return affecnew;
	}
	
	
}
