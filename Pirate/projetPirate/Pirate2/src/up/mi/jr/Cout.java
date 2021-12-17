package up.mi.jr;

import java.util.ArrayList;
import java.util.Random;

/**
 * Représente le coût (nombre de pirate jaloux) d'une affectation
 * @author Abisha Jeyavel, Lalariniaina Ramanantoanina
 * @version 1.1
 *
 */
public class Cout {
	/**
	 * Objet de type Relation qui représente les differentes relations entre les pirates
	 * et entre les pirates et les objets
	 */
	private Relation rel;
	/**
	 * Le nombre de tentative pour la résolution automatique
	 */
	private int k;

	/**
	 * Constructeur qui initialise le coût à partir d'un objet de type Relation
	 * @param relation Objet de type Relation qui représente les differentes relations entre les pirates
	 * et entre les pirates et les objets
	 */
	public Cout(Relation relation) {
		rel=relation;
		k= 100*rel.getListePirate().size(); // pour éventuellement changer le nombre de tentative pour l'algorithme naif
	}

	/**
	 * Permet de calculer le coût des affectations d'objet à des pirates
	 * @param affec Objet de type affectation qui représente les affectations des objets aux pirates
	 * @return Le coût des affectations d'objet des pirates
	 */
	public int calculCout(Affectation affec)  {
		ArrayList<Pirate> jaloux = new ArrayList<>() ;
		for(Pirate p : affec.getPirateObjet().keySet()) {
			boolean isjaloux = false; //pour savoir si le pirate est jaloux de quelqu'un
			int i=0;
			//On parcourt la liste de préférences d'un pirate, jusqu'à trouver l'objet qui lui est affecté ou lorsqu'on sait qu'il est jaloux
			while(!rel.getPreference().get(p).get(i).equals(affec.getPirateObjet().get(p)) && !isjaloux) {
				//Objet préféré par rapport à l'objet que le pirate p a eu
				Objet o = rel.getPreference().get(p).get(i);
				// On regarde si le pirate qui a eu l'objet fait parti des pirates que le pirate p n'aime pas
				if( rel.getdeteste().get(p).contains(affec.getObjetPirate().get(o))){
					//Un pirate peut être jaloux qu'une seule fois
					isjaloux=true;
					jaloux.add(p);
				}
				i++;
			}
		}
		return jaloux.size();
	}



	/**
	 * Implémentation de l'algorithme naif proposé dans l'énoncé
	 * @param aff Objet de type affectation qui représente les affectations des objets aux pirates
	 * @return Objet de type affectation qui représente les affectations des objets aux pirates
	 */
	public Affectation algoNaif(Affectation aff) {
		int i = 0;
		Random randomGenerateur = new Random();
		Affectation S = aff;
		//S.affectation(rel);
		while (i < k) {
			ArrayList <Pirate> equipage = new ArrayList<>(rel.getListePirate().values());
			ArrayList <Pirate> equipe = new ArrayList<>(rel.getListePirate().values());
			int index = randomGenerateur.nextInt(equipage.size());
			Pirate p = equipage.get(index);
			equipe.remove(index);
			int index1 = randomGenerateur.nextInt(equipe.size());
			Pirate pVoisin = equipe.get(index1);
			Affectation S1 = S.echange(p, pVoisin);
			if (calculCout(S) > calculCout(S1)) {
				S = S1;
			}
			i++;
		}
		return S;
	}


}
