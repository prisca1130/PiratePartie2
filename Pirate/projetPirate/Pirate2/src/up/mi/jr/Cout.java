package up.mi.jr;

import java.util.ArrayList;
import java.util.Random;

public class Cout {
	
	Relation rel;
	
	public Cout(Relation relation) {
		rel=relation;
	}
	
	/**
	 * Permet de calculer le coût des affectations d'objet des pirates
	 * @return Le coût des affectations d'objet des pirates
	 */
	public int calculCout( Affectation affec) {
		ArrayList<Pirate> jaloux = new ArrayList<Pirate>() ;
		
		for(Pirate p : affec.getPirateObjet().keySet()) { 
			boolean isjaloux = false; //pour savoir si le pirate est jaloux de quelqu'un
			int i=0;
			//On parcourt la liste de préférences d'un pirate, jusqu'à trouver l'objet qui lui est affecté ou lorsqu'on sait qu'il est jaloux
			while(!rel.getPreference().get(p).get(i).equals(affec.getPirateObjet().get(p)) && !isjaloux) {
				//Objet préféré par rapport à l'objet que le pirate p a eu
				Objet o = rel.getPreference().get(p).get(i);
				//temp désigne le pirate qui a eu l'objet préféré que le pirate p aurait aimé avoir
				Pirate temp = new Pirate("temp");
				for (Objet j : affec.getObjetPirate().keySet()) {
					if(j.equals(o)) {
						temp = affec.getObjetPirate().get(j);
					}
				// On regarde si le pirate temp fait parti des pirates que le pirate p n'aime pas
				}
				for(int k = 0; k<rel.getdeteste().get(p).size(); k++) {
					if (rel.getdeteste().get(p).get(k).equals(temp)) {
						//Un pirate peut être jaloux qu'une seule fois
						isjaloux=true;
						jaloux.add(p);				
					}
				}
				i++;
				}
			}	
		return jaloux.size();
	}	
	
	
	///////////
	
	public Affectation algoNaif(int k) {
		int i = 0;
//		ArrayList <Pirate> equipe = equipage;
		Random randomGenerateur = new Random();
		
		Affectation affec1 = new Affectation();
		affec1.tempaffect(rel);
		affec1.affectation();
		//System.out.println("cout de la première affec : "+calculCout(affec1));
		//HashMap<Pirate,Objet> S = this.affectation();
		//int coutS = this.calculCout();
		while (i < k) {
			ArrayList <Pirate> equipage = new ArrayList<>(rel.getListePirate().values());
			ArrayList <Pirate> equipe = new ArrayList<>(rel.getListePirate().values());
			int index = randomGenerateur.nextInt(equipage.size());
			Pirate p = equipage.get(index);
			//System.out.println(" pirate p : "+p);
			equipe.remove(index);
			//TODO OutofBOND 
			int index1 = randomGenerateur.nextInt(equipe.size());
			Pirate pVoisin = equipe.get(index1);
			//System.out.println("pirate voisin : "+pVoisin);
			Affectation affec2 = affec1.echange(p, pVoisin);
			//System.out.println(" cout affec2 :"+calculCout(affec2));
//			int c1 =(calculCout(affec1));
//			System.out.println("cout de la première affec : "+calculCout(affec1));
//			int c2 =(calculCout(affec2));
//			System.out.println((c1> c2));
			if (calculCout(affec1) > calculCout(affec2)) {
				System.out.println("hello");
				affec1 = affec2;
			}
			i++;
		}
		System.out.println(calculCout(affec1));
		return affec1; 
	}


}
