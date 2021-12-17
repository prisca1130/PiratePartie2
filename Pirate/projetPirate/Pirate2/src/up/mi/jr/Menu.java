package up.mi.jr;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Représente les deux menus du partage de butin,
 * et le main
 * @author Abisha Jeyavel, Lalariniaina Ramanantoanina
 * @version 1.1
 *
 */
public class Menu {
	/**
	 * Méthode principale pour lancer le programme
	 * @param args Le premier argument correspond au chemin du fichier de l'utilisateur saisi en ligne de commande
	 */
	public static void main(String [] args) {
		try {
			System.out.println("Bonjour!!");
			Scanner sc = new Scanner(System.in);
			Relation rel = new Relation();
			Affectation affec = new Affectation();
			Cout cout = new Cout(rel);
			File fichier = new File(args[0]);
			Parse parse = new Parse(fichier,rel);
			parse.parserFichier();

			System.out.println("\n---------------- Liste de l'équipage  -----------------");
			System.out.println(rel.afficheListepirate());
			System.out.println("\n---------------- Liste du butin  -----------------");
			System.out.println(rel.afficheListeObj());
			System.out.println("\n---------------- Relations d'affinité entre les pirates   -----------------");
			System.out.println(rel.afficheDeteste());
			System.out.println("\n---------------- Liste de préférence des pirates  -----------------");
			System.out.println(rel.affichePreference());
			System.out.println("\n---------------- Liste de la première affectation  -----------------");
			affec.affectation(rel);
			System.out.println(affec.afficheAffectation());
			System.out.println("\nle cout est : "+cout.calculCout(affec));

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
				Cout cout1 = new Cout(rel);
				System.out.println(affec.afficheAffectation());
				affec = cout1.algoNaif(affec);
				System.out.println("\n---------------- Résultat de la résolution automatique  -----------------");
				System.out.println(affec.afficheAffectation());
				System.out.println("\nle cout est : "+cout1.calculCout(affec));
				break;
			case 2:
				menu2(sc,rel,affec);
				System.out.println("\n---------------- Résultat de la dernière résolution -----------------");
				System.out.println(affec.afficheAffectation());
				break;
			case 3:
				sauvegarder(affec,sc);
				break;
			case 4 :
				break;
			default :
				System.out.println("Le choix " + choix + " n'est pas valide.");
			
			}
		}while(choix!=4);
		sc.close();
		} catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Veuillez mettre en argument de la ligne de commande le chemin du fichier");
		} catch(PirateException e) {
			System.err.println(e.getMessage());
		}
   }

	/**
	 * Permet de créer le menu2 pour effectuer la résolution en échangeant les objets entre deux pirates et afficher le coût des affectations d'objets des pirates
	 * @param sc Le Scanner pour récuperer le choix de l'utilisateur pour le menu 2
	 * @param rel Un objet de type Relation pour représenter les differentes relations entre les pirates et les objets
	 * @param aff Objet de type affectation qui représente les affectations des objets aux pirates
	 * @throws PirateException on relaie le traitement de l'exception au niveau de la méthode « appelante »
	 */
	
	public static void menu2(Scanner sc,Relation rel, Affectation aff) throws PirateException {
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
			while(!(rel.getListePirate().containsKey(p1) && rel.getListePirate().containsKey(p2))){
				System.out.println("Les pirates saisis n'existent pas");
				System.out.println("Veuillez resaisir Les noms des pirates pour lesquels vous voulez faire l'echange d'objet ?");
				System.out.println("Entrez le nom du premier pirate");
				p1 = sc.next();
				System.out.println("Entrez le nom du second pirate");
				p2 = sc.next();
			}
			if(p1.equals(p2)) {
				System.err.println("Vous voulez échanger les objets d'un même pirate");
			}
			aff.echangeNew(rel.getListePirate().get(p1), rel.getListePirate().get(p2));
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
	 * @param message le message à afficher avant la lecture
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

	/**
	 * Permet de sauvegarder dans un fichier (dont le chemin est donné par l'utilisateur) la dernière solution (la dernière affectation de la résolution manuelle ou automatique)
	 * @param affec Objet de type affectation qui représente l'affectation des objets aux pirates
	 * @param sc le scanner pour récupèrer le chemin du fichier dans lequel sera enregistré la dernière solution
	 * @throws PirateException on relaie le traitement de l'exception au niveau de la méthode « appelante »
	 */
	private static void sauvegarder(Affectation affec, Scanner sc) throws PirateException {
		System.out.println("Veuillez entrer le chemin du fichier dans lequel vous voulez enregistrer la dernière solution");
		String chemin = sc.next();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(chemin))){
			bw.append(affec.afficheAffectation());
			System.out.println("La sauvegarde s'est déroulé avec succès");
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier de sauvegarde est introuvable");
		}catch (IOException e) {
			System.err.println("le sauvegarde a échoué");
		}

	}

	

}