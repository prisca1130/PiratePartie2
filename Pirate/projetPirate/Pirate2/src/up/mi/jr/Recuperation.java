package up.mi.jr;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recuperation {
	private File fichier;
	private Relation rel;
	
	public Recuperation(File fichier, Relation rel) {
		this.fichier = fichier;
		this.rel=rel;
	}

	public void parserPirate() {
		String nom = null;
		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
			String ligne = null;
			while ((ligne = br.readLine()) != null) {

				if(ligne.startsWith("pirate") || ligne.startsWith("Pirate")) {

					nom = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
					rel.getListePirate().put(nom,new Pirate(nom));
				}
			}
		}catch (FileNotFoundException e) {
			e. printStackTrace ();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e. printStackTrace ();
			System.err.println(e.getMessage());
		}
		if(nom == null) {
			System.err.println("Le nom du pirate n'a pas ete indiqué");
			System.exit(0);
		}
	}

	public void parserObjet() {
		String nomObjet = null;

		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
			String ligne = null;
			while ((ligne = br.readLine()) != null) {

				if(ligne.startsWith("objet") || ligne.startsWith("Objet")) {

					nomObjet = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
					rel.getListeObj().put(nomObjet,new Objet(nomObjet));
				}
			}
		}catch (FileNotFoundException e) {
			e. printStackTrace ();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e. printStackTrace ();
			System.err.println(e.getMessage());
		}
		if(nomObjet == null) {
			System.err.println("Le nom de l'objet n'a pas ete indiqué");
			System.exit(0);
		}
		if (rel.getListeObj().size() != rel.getListePirate().size()) {
			System.err.println("Attention le nombre d'objet saisi est different du nombre de pirate saisi");
			System.err.println("Veuillez revoir votre fichier");
		}

	}


	public void parserDeteste() throws EmptyObject{
		String nomsPirate = null;

		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
			String ligne = null;
			while ((ligne = br.readLine()) != null) {

				if(ligne.startsWith("deteste") || ligne.startsWith("Deteste")) {
					nomsPirate = (ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")")));
//						//Traiter le cas où il rentre pls desteste ex 3
					ArrayList<Pirate> deteste1 = new ArrayList<>();
					ArrayList<Pirate> deteste2 = new ArrayList<>();
					if(!(rel.getListePirate().containsKey(nomsPirate.split(",")[0]) && rel.getListePirate().containsKey(nomsPirate.split(",")[1]))){
						throw new EmptyObject("Veuillez revoir le fichier au niveau de \"deteste\", car le nom du pirate n'existe pas");
					}
					if(nomsPirate.split(",")[0].equals(nomsPirate.split(",")[1])) {
						System.err.println("Veuillez revoir le fichier au niveau de \"deteste\" \nUn pirate ne peut se détester lui-même");
					}
					Pirate p1 = rel.getListePirate().get(nomsPirate.split(",")[0]);
					Pirate p2 = rel.getListePirate().get(nomsPirate.split(",")[1]);
					if(rel.getdeteste().containsKey(p1)){
						rel.getdeteste().get(p1).add(p2);
					}else {
						deteste1.add(p2);
						rel.getdeteste().put(p1, deteste1);
					}
					if(rel.getdeteste().containsKey(p2)){
						rel.getdeteste().get(p2).add(p1);
					}else {
						deteste2.add(p1);
						rel.getdeteste().put(p2, deteste2);
					}

				}
			}
		}catch (FileNotFoundException e) {
			e. printStackTrace ();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e. printStackTrace ();
			System.err.println(e.getMessage());
		}
		if(nomsPirate == null) {
			System.err.println("La relation d'affinité entre les pirates n'a pas ete indiqué");
			System.exit(0);
		}

	}


	public void parserPreference() {
		String nomsPirObj = null;

		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
			String ligne = null;
			while ((ligne = br.readLine()) != null) {

				if(ligne.startsWith("preferences") || ligne.startsWith("Preferences")) {

					nomsPirObj = (ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")")));

					//A Traiter le cas où l'utilisateur oublie d'entrer le nom des 3 objets dans preference
					ArrayList<Objet> listObj = new ArrayList<>();
					String [] EnsPirObj = nomsPirObj.split(",");
					if (EnsPirObj.length != rel.getListePirate().size()+1 ) {
						System.err.println("Attention, tous les objets doivent être ordonner dans la liste de préférence de chaque pirate");
						System.err.println("Veuillez revoir votre fichier, au niveau de preference");
					}

					for(int i = 1; i<EnsPirObj.length; i++) {
						if(listObj.contains(rel.getListeObj().get(nomsPirObj.split(",")[i]))){
							System.err.println("Veuillez revoir votre fichier, au niveau de preference, car vous avez saisi deux fois le même objet");
						}else {
							listObj.add(rel.getListeObj().get(nomsPirObj.split(",")[i]));
						}
						rel.getPreference().put(rel.getListePirate().get(EnsPirObj[0]), listObj);
						//System.out.println(rel.affichePreference());
					}

				}
			}
		}catch (FileNotFoundException e) {
			e. printStackTrace ();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e. printStackTrace ();
			System.err.println(e.getMessage());
		}
		if(nomsPirObj == null) {
			System.err.println("La liste de preferences d'un pirate n'a pas ete indiqué");
			System.exit(0);
		}
		if (rel.getPreference().size() != rel.getListePirate().size()) {
			System.err.println("Attention, il faut saisir autant de relation de préférence que de nombre de pirate");
			System.err.println("Veuillez revoir votre fichier, au niveau de preference");
		}

	}
	
	
}
