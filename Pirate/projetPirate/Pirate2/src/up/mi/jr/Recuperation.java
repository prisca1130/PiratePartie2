package up.mi.jr;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recuperation {
	private File fichier;
	
	public Recuperation(File fichier) {
		this.fichier = fichier;
	}
	
	public void parser(Relation rel) {
		String nom = null;
		String nomObjet = null;
		String nomsPirate = null;
		String nomsPirObj = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
			String ligne = null;
			while ((ligne = br.readLine()) != null) {
				
				if(ligne.startsWith("pirate") || ligne.startsWith("Pirate")) {
					nom = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
					rel.getListePirate().put(nom,new Pirate(nom));
					System.out.println(rel.afficheListepirate());
				}
				
				
				if(ligne.startsWith("objet") || ligne.startsWith("Objet")) {
					nomObjet = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
					rel.getListeObj().put(nomObjet,new Objet(nomObjet));
					System.out.println(rel.afficheListeObj());
				}
				
				if(ligne.startsWith("deteste") || ligne.startsWith("Deteste")) {
					nomsPirate = (ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")")));
//					//Traiter le cas où il rentre pls desteste ex 3
					ArrayList<Pirate> deteste1 = new ArrayList<Pirate>();
					ArrayList<Pirate> deteste2 = new ArrayList<Pirate>();
					deteste1.add(rel.getListePirate().get(nomsPirate.split(",")[1]));
					deteste2.add(rel.getListePirate().get(nomsPirate.split(",")[0]));
					rel.getdeteste().put(rel.getListePirate().get(nomsPirate.split(",")[0]),deteste1);
					rel.getdeteste().put(rel.getListePirate().get(nomsPirate.split(",")[1]),deteste2);
					System.out.println(rel.afficheDeteste());
					
				}
				
				if(ligne.startsWith("preferences") || ligne.startsWith("Preferences") || ligne.startsWith("preference") || ligne.startsWith("preference")) {
					nomsPirObj = (ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")")));

					//A Traiter le cas où l'utilisateur oublie d'entrer le nom des 3 objets dans preference
					ArrayList<Objet> listObj = new ArrayList<Objet>();
					String [] EnsPirObj = nomsPirObj.split(",");
					for(int i = 1; i<EnsPirObj.length; i++) {
						listObj.add(new Objet(nomsPirObj.split(",")[i]));
						//System.out.println(EnsPirObj[0]);
					}
					rel.getPreference().put(rel.getListePirate().get(EnsPirObj[0]), listObj);
					System.out.println(rel.affichePreference());
				}
			
				
			}
		}catch (FileNotFoundException e) { 
			e. printStackTrace ();
		} catch (IOException e) { 
			e. printStackTrace ();
		}
		
		
//		if (nom == null) {
//			System.err.println("Le nom du pirate n°" + listePirate.size()+1 + " n’a pas été indique"); 
//			System.exit(0);
//		}
//		//return listePirate;
//		
//		if (nom == null) {
//			System.err.println("Le nom de l'objet n°" + listeObjet.size()+1 + " n’a pas été indique"); 
//			System.exit(0);
//		}
//		
//		//A corriger
//		if (nomsPirate == null) {
//			System.err.println("La saisie de deteste est mal indiquée"); 
//			System.exit(0);
//		}
//	
//		//A corriger
//		if (nomsPirObj == null) {
//			System.err.println("La saisie de preference est mal indiquée"); 
//			System.exit(0);
//		}
//		
//		Relation rel = new Relation();
//		rel.setDeteste(seDeteste);
//		rel.setPreference(preferenceObj);
//		return rel;
	}
//	
	
	
//	public ArrayList<Pirate> parserPirate(File fichier) {
//		ArrayList<Pirate> listePirate = new ArrayList<Pirate>();
//		String nom = null;
//		
//		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
//			String ligne = null;
//			while ((ligne = br.readLine()) != null) {
//				if(ligne.startsWith("pirate") || ligne.startsWith("Pirate")) {
////					if(nom != null) {
////						System.err.println("Il ne peut pas y avoir deux lignes pour le nom d'un pirate");
////						System.exit(0);
////					}else {
////						nom = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
////						listePirate.add(new Pirate(nom));
////					}
//					
//					nom = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
//					listePirate.add(new Pirate(nom));
//				}
//			}
//		}catch (FileNotFoundException e) { 
//			e. printStackTrace ();
//		} catch (IOException e) { 
//			e. printStackTrace ();
//		}
//		if (nom == null) {
//			System.err.println("Le nom du pirate n°" + listePirate.size()+1 + " n’a pas été indique"); 
//			System.exit(0);
//		}
//		return listePirate;
//	}
//	
//	public ArrayList<Objet> parserObjet(File fichier) {
//		ArrayList<Objet> listeObjet = new ArrayList<Objet>();
//		String nom = null;
//		
//		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
//			String ligne = null;
//			while ((ligne = br.readLine()) != null) {
//				if(ligne.startsWith("objet") || ligne.startsWith("Objet")) {
////					if(nom != null) {
////						System.err.println("Il ne peut pas y avoir deux lignes pour le nom d'un pirate");
////						System.exit(0);
////					}else {
////						nom = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
////						listeObjet.add(new Objet(nom));
////					}
//					
//					nom = ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")"));
//					listeObjet.add(new Objet(nom));
//				}
//			}
//		}catch (FileNotFoundException e) { 
//			e. printStackTrace ();
//		} catch (IOException e) { 
//			e. printStackTrace ();
//		}
//		if (nom == null) {
//			System.err.println("Le nom de l'objet n°" + listeObjet.size()+1 + " n’a pas été indique"); 
//			System.exit(0);
//		}
//		return listeObjet;
//	}
//	
//	public HashMap<Pirate, ArrayList<Pirate>> parserDeteste(File fichier) {
//		HashMap<Pirate, ArrayList<Pirate>> seDeteste= new HashMap<Pirate, ArrayList<Pirate>>();
//		//ArrayList<Pirate> seDeteste = new ArrayList<Pirate>();
//		String nomsPirate = null;
//		
//		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
//			String ligne = null;
//			while ((ligne = br.readLine()) != null) {
//				if(ligne.startsWith("deteste") || ligne.startsWith("Deteste")) {
//					nomsPirate = (ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")")));
//
//
//					//A Traiter le cas où l'utilisateur oublie d'entrer le nom du 2eme pirate dans deteste 
//					ArrayList<Pirate> deteste1 = new ArrayList<Pirate>();
//					ArrayList<Pirate> deteste2 = new ArrayList<Pirate>();
//					deteste1.add(new Pirate(nomsPirate.split(",")[1]));
//					deteste2.add(new Pirate(nomsPirate.split(",")[0]));
//					seDeteste.put(new Pirate(nomsPirate.split(",")[0]), deteste1);
//					seDeteste.put(new Pirate(nomsPirate.split(",")[1]), deteste2);
//					
//				}
//			}
//		}catch (FileNotFoundException e) { 
//			e. printStackTrace ();
//		} catch (IOException e) { 
//			e. printStackTrace ();
//		}
//		//A corriger
//		if (nomsPirate == null) {
//			System.err.println("La saisie de deteste est mal indiquée"); 
//			System.exit(0);
//		}
//		return seDeteste;
//	}
//	
//	public HashMap<Pirate, ArrayList<Objet>> parserPreference(File fichier) {
//		HashMap<Pirate, ArrayList<Objet>> preferenceObj= new HashMap<Pirate, ArrayList<Objet>>();
//		String nomsPirObj = null;
//		
//		try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
//			String ligne = null;
//			while ((ligne = br.readLine()) != null) {
//				if(ligne.startsWith("deteste") || ligne.startsWith("Deteste")) {
//					nomsPirObj = (ligne.substring(ligne.indexOf("(")+1, ligne.indexOf(")")));
//
//					//A Traiter le cas où l'utilisateur oublie d'entrer le nom des 3 objets dans preference
//					ArrayList<Objet> listObj = new ArrayList<Objet>();
//					String [] EnsPirObj = nomsPirObj.split(",");
//					for(int i = 1; i<EnsPirObj.length; i++) {
//						listObj.add(new Objet(nomsPirObj.split(",")[i]));
//					}
//					preferenceObj.put(new Pirate(EnsPirObj[0]), listObj);
//					
//				}
//			}
//		}catch (FileNotFoundException e) { 
//			e. printStackTrace ();
//		} catch (IOException e) { 
//			e. printStackTrace ();
//		}
//		//A corriger
//		if (nomsPirObj == null) {
//			System.err.println("La saisie de preference est mal indiquée"); 
//			System.exit(0);
//		}
//		return preferenceObj;
//	}
	
	//A embellir
//	public Relation recupTotal(Relation rel) {
//		//Relation rel = new Relation();
//		rel.setDeteste(parser(fichier));
//		rel.setPreference(parserPreference(fichier));
//		return rel;
//	}
	
	
}
