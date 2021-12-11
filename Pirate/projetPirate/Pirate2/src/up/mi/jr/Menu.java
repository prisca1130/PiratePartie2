package up.mi.jr;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	

	public static void main(String [] args) {
		System.out.println("Bonjour!!");
		Scanner sc = new Scanner(System.in);
		Relation rel = new Relation();
		Affectation affec = new Affectation();
		System.out.println("Veillez entrez le chemin du fichier descriptif de l'équipage");
		String chemin = sc.nextLine();
		File fichier = new File(chemin);
		rel.parser(fichier);
		int choix;
		do {
		System.out.println("\n---------------- Menu  -----------------");
		System.out.println("Quelle action souhaitez-vous effectuer ?");
		System.out.println("1 Résolution automatique");
		System.out.println("2 Résolution manuelle");
		System.out.println("3 Sauvegarder");
		System.out.println("4 Quitter le menu");
		choix = lireEntierAuClavier(sc, "Choix = ");	
		switch (choix) {
		case 1:
			Cout cout = new Cout(rel);
			System.out.println("Pour la résolution automatique, combien de tentative d'échange voulez vous faire?");
			int k = lireEntierAuClavier(sc, "Nombre tentative = ");
			affec = cout.algoNaif(k);
			System.out.println(affec.affichepirateObjet());
			break;
		case 2:
			menu2(sc,rel,affec);
			break;
			
		case 3:
			Sauvegarde sauvegarde = new Sauvegarde();
			//sauvegarde.creerFichier();
			sauvegarde.sauvegarder(affec);
			break;
			
		case 4 :
			break;
			
		default :
			System.out.println("Le choix " + choix + " n'est pas valide.");
			
		}
		}while(choix!=4);
		sc.close();
   }
	
	/**
	 * Permet de créer le menu2 pour échanger les objets entre deux pirates et afficher le coût des affectations d'objets des pirates
	 * @param sc Le Scanner pour récuperer le choix de l'utilisateur pour le menu 2
	 * @param rel Un objet de type Relation pour représenter les differentes relations entre les pirates et les objets
	 */
	
	public static void menu2(Scanner sc,Relation rel, Affectation aff) {
		
		System.out.println("Voici une idée d'affectation ");
		aff.tempaffect(rel);
		aff.affectation();
		System.out.println(aff.affichepirateObjet());
		int choix;
		do {
		System.out.println("\n---------------- Résolution Manuelle -----------------");
		System.out.println("Quelle action souhaitez-vous effectuer ?");
		System.out.println("1 Echanger des objets");
		System.out.println("2 Afficher les coûts");
		System.out.println("3 Quitter");
		choix = lireEntierAuClavier(sc, "Choix = ");
		if ((choix < 1) || (choix > 3)) {
			System.err.println("Le choix " + choix + " n'est pas valide.");
			System.exit(1);
		}	
		switch (choix) {
		case 1:
			
			System.out.println("Les objets de quels pirates voulez-vous echanger ?");
			System.out.println("Entrez le nom du premier pirate");
			String p1 = sc.next();
			System.out.println("Entrez le nom du second pirate");
			String p2 = sc.next();
			aff = aff.echange(rel.getListePirate().get(p1), rel.getListePirate().get(p2));
			System.out.println("Voici les conséquences de vos affectations ");
			System.out.println(aff.affichepirateObjet());
			break;
		case 2:
			Cout cout = new Cout(rel);
			System.out.println("Voici le coût suite aux affectations : " + cout.calculCout(aff));
			break;
		case 3:
			break;
			
		}
		}while(choix!=3);
	}
	
	/**
	 * Lit un entier au clavier
	 * 
	 * @param sc      le scanner dans lequel lire l'entier
	 * @param message le message a afficher avant la lecture
	 * @return l'entier lu
	 */
	private static int lireEntierAuClavier(Scanner sc, String message) {
		boolean lectureOK = false;
		int nb = 0;
		while (!lectureOK) {
			try {
				System.out.print(message);
				nb = sc.nextInt();
				lectureOK = true ;
			} catch (InputMismatchException e) {
				System.out.println("Il faut taper un nombre entier");
				sc.nextLine();
			}
		}
		return nb;
	}

	

}