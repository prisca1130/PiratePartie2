package up.mi.jr;

import java.io.File;


public class MenuFake {
	public static void main (String args[]) {
		File pirate = new File("/Users/niainaramanantoanina/test.txt");
		Relation rel = new Relation();
		rel.parser(pirate);
		System.out.println(rel.afficheListepirate());
		System.out.println(rel.afficheListeObj());
		System.out.println(rel.afficheDeteste());
		System.out.println(rel.affichePreference());
		Affectation affec = new Affectation();
		affec.tempaffect(rel);
		affec.affectation();
//		System.out.println(affec.getPirateObjet().entrySet());
		
		System.out.println(affec.affichepirateObjet());
		Cout cout = new Cout(rel);
		System.out.println(" Voici le cout de votre affectation : "+cout.calculCout(affec));
		Affectation affecnew = cout.algoNaif(3);
		System.out.println(affecnew.affichepirateObjet());

		System.out.println("ok");

	
	}
}