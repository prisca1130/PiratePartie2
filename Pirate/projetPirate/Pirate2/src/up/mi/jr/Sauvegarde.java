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
	

	public void sauvegarder(Affectation affec) throws NullPointerException{
		/*
		TODO
		 à Revoir car cas déja traité par l'erreur de affichepirateObjet
		*/
		if (affec==null) {
			throw new NullPointerException("Il n'y a rien à sauvegarder, car l'affectation des objets n'a pas eu lieu");
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(sauvegarde))){
			bw.append(affec.affichepirateObjet());
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier de sauvegarde est introuvable");
			e.printStackTrace();
		}catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

}