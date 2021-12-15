package up.mi.jr;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	public static void main(String [] args) {
		try {
		System.out.println("Bonjour!!");
		Scanner sc = new Scanner(System.in);
		Relation rel = new Relation();
		Affectation affec = new Affectation();
		Cout cout = new Cout(rel);
		System.out.println("Veillez entrez le chemin du fichier descriptif de l'équipage");
		String chemin = sc.nextLine();
		File fichier = new File(chemin);
		Recuperation recup = new Recuperation(fichier,rel);
		recup.parserPirate();
		recup.parserObjet();
		recup.parserDeteste();
		recup.parserPreference();
		affec.affectation(rel);
			System.out.println("\n---------------- Liste de l'équipage  -----------------");
			System.out.println(rel.afficheListepirate());
			System.out.println("\n---------------- Liste du butin  -----------------");
			System.out.println(rel.afficheListeObj());
			System.out.println("\n---------------- Relations d'affinité entre les pirates   -----------------");
			System.out.println(rel.afficheDeteste());
			System.out.println("\n---------------- Liste de préférence des pirates  -----------------");
			System.out.println(rel.affichePreference());
			System.out.println("\n---------------- Liste de la première affectation  -----------------");
			System.out.println(affec.afficheAffectation());
			System.out.println("\nle cout est : "+cout.calculCout1111(affec));

		int choix;
		do {
		System.out.println("\n---------------- Menu  -----------------");
		System.out.println("Quelle action souhaitez-vous effectuer ?");
		System.out.println("1 Résolution automatique");
		System.out.println("2 Résolution manuelle");
		System.out.println("3 Sauvegarder");
		System.out.println("4 Quitter le menu");
		choix = lireEntierAuClavier(sc, "\nChoix = ");
		switch (choix) {
		case 1:
			System.out.println("Pour la résolution automatique, combien de tentative d'échange voulez vous faire?");
			int k = lireEntierAuClavier(sc, "\nNombre tentative = ");
			affec = cout.algoNaif(k);
			System.out.println("\n---------------- Résultat de la résolution automatique  -----------------");
			System.out.println(affec.afficheAffectation());
			System.out.println("\nle cout est : "+cout.calculCout1111(affec));
			break;
		case 2:
			menu2(sc,rel,affec);
			break;
			
		case 3:
			sauvegarder(affec);
			break;
			
		case 4 :
			break;
			
		default :
			System.out.println("Le choix " + choix + " n'est pas valide.");
			
		}
		}while(choix!=4);
		sc.close();
		}catch(EmptyObject e) {
			System.err.println(e.getMessage());
		}
   }
	
	/**
	 * Permet de créer le menu2 pour échanger les objets entre deux pirates et afficher le coût des affectations d'objets des pirates
	 * @param sc Le Scanner pour récuperer le choix de l'utilisateur pour le menu 2
	 * @param rel Un objet de type Relation pour représenter les differentes relations entre les pirates et les objets
	 */
	
	public static void menu2(Scanner sc,Relation rel, Affectation aff) throws EmptyObject{
		System.out.println("\n---------------- Résultat de la dernière résolution -----------------");
		System.out.println(aff.afficheAffectation());
		int choix;
		do {
		System.out.println("\n---------------- Résolution Manuelle -----------------");
		System.out.println("Quelle action souhaitez-vous effectuer ?");
		System.out.println("1 Echanger des objets");
		System.out.println("2 Afficher les coûts");
		System.out.println("3 Quitter");
		choix = lireEntierAuClavier(sc, "Choix = ");

		switch (choix) {
		case 1:
			System.out.println("Les objets de quels pirates voulez-vous echanger ?");
			System.out.println("Entrez le nom du premier pirate");
			String p1 = sc.next();
			System.out.println("Entrez le nom du second pirate");
			String p2 = sc.next();
			if(!(rel.getListePirate().containsKey(p1) && rel.getListePirate().containsKey(p2))){
				throw new EmptyObject("Les pirates saisis n'existent pas");
			}
			if(p1.equals(p2)) {
				System.err.println("Vous voulez échanger les objets d'un même pirate");
			}
			aff = aff.echange(rel.getListePirate().get(p1), rel.getListePirate().get(p2));
			System.out.println("\n---------------- Résultat de votre résolution -----------------");
			System.out.println(aff.afficheAffectation());
			break;
		case 2:
			Cout cout = new Cout(rel);
			System.out.println("Voici le coût suite aux affectations : " + cout.calculCout(aff));
			break;
		case 3 :
				break;
		default :
				System.out.println("Le choix " + choix + " n'est pas valide.");

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

	private static void sauvegarder(Affectation affec) throws NullPointerException{

		try(BufferedWriter bw = new BufferedWriter(new FileWriter("SauvegardePirate.txt"))){
			bw.append(affec.afficheAffectation());
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier de sauvegarde est introuvable");
			e.printStackTrace();
		}catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

	

}