package up.mi.jr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Sauvegarde {
	private File sauvegarde;
	
	public Sauvegarde() {
		sauvegarde= new File("SauvegardePirate.txt");
	}
	
//    public void creerFichier() {
//		try {
//			if (sauvegarde.createNewFile()) {
//				System.out.println("Fichier crée: " + sauvegarde.getName());
//			} else {
//				System.out.println("Fichier existant");
//			}
//		} catch (IOException e) {
//			System.out.println("Erreur lors de la création du fichier");
//			e.printStackTrace();
//		}
//	}
//	
	public void sauvegarder(Affectation affec) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(sauvegarde))){
//			StringBuffer buf = new StringBuffer();
//			for(Pirate i : affec.getPirateObjet().keySet()) {
//				buf.append(i.getNom()+ " : " + affec.getPirateObjet().get(i).getNom() + "\n");
//			}
			bw.append(affec.affichepirateObjet());
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier de sauvegarde est introuvable");
			e.printStackTrace();
		}catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}; 
		
	}

}