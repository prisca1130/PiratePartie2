package up.mi.jr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parse {
    private File fichier;
    private Relation rel;
    private int nbligne;

    public Parse(File fichier, Relation rel) {
        this.fichier = fichier;
        this.rel=rel;
        this.nbligne=0;
    }

    public void parserPirate()throws EmptyObjectException {
        String nom = null;
        String nomObjet = null;
        String nomsPirate = null;
        String nomsPirObj = null;
        try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
            String ligne = null;
            String subligne= null;
            while ((ligne = br.readLine()) != null) {
                nbligne++;
                if(ligne.isEmpty()){
                    throw new EmptyObjectException("la ligne "+ nbligne+" est vide");
                }
                if(!ligne.endsWith(".")){
                  throw new EmptyObjectException("Veuillez revoir le fichier car la ligne "+nbligne+" ne se termine pas par un point");
                }
               try {
                subligne = ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"));
               }catch (StringIndexOutOfBoundsException e){
                   System.err.println("Veuillez mettre les parenthèse correctement à la ligne "+nbligne);
               }

               if( !(ligne.startsWith("pirate") || ligne.startsWith("objet") || ligne.startsWith("deteste") || ligne.startsWith("preference"))){
                   throw new EmptyObjectException("Veuillez revoir le fichier car la ligne "+nbligne+" commence par un mot inconnue");
               }
                if (ligne.startsWith("pirate")) {
                    nom = subligne;
                   if(nom.split(",").length != 1){
                       throw new EmptyObjectException("\"Veuillez revoir le fichier car à la ligne "+nbligne+" le pirate a plusieur noms");
                   }
                   if (rel.getListePirate().containsKey(nom)) {
                        throw new EmptyObjectException("Veuillez revoir la ligne "+nbligne+" du fichier car vous avez saisie deux fois le nom d'un pirate");
                   }
                   rel.getListePirate().put(nom, new Pirate(nom));
                }

                if (ligne.startsWith("objet")) {
                    nomObjet = subligne;
                    if(nomObjet.split(",").length != 1){
                        throw new EmptyObjectException("\"Veuillez revoir le fichier car à la ligne "+nbligne+" l'objet a plusieur noms");
                    }
                    if (rel.getListeObj().containsKey(nomObjet)) {
                        throw new EmptyObjectException("Veuillez revoir la ligne "+nbligne+" du fichier car vous avez saisie deux fois le nom d'un objet");
                    }
                    rel.getListeObj().put(nomObjet, new Objet(nomObjet));
                }

                if (ligne.startsWith("deteste") ) {
                    nomsPirate = subligne;
                    if(nomsPirate.split(",").length != 2){
                        throw new EmptyObjectException("\"Veuillez revoir la ligne "+nbligne+" du fichier car deteste peut avoir que deux arguments");
                    }
                    ArrayList<Pirate> deteste1 = new ArrayList<>();
                    ArrayList<Pirate> deteste2 = new ArrayList<>();
                    if (!(rel.getListePirate().containsKey(nomsPirate.split(",")[0]) && rel.getListePirate().containsKey(nomsPirate.split(",")[1]))) {
                        throw new EmptyObjectException("Veuillez revoir la ligne "+nbligne+" du fichier car le nom du pirate n'existe pas");
                    }
                    if (nomsPirate.split(",")[0].equals(nomsPirate.split(",")[1])) {
                        throw new EmptyObjectException("Veuillez revoir la ligne "+nbligne+" du fichier car un pirate ne peut se détester lui-même");
                    }
                    Pirate p1 = rel.getListePirate().get(nomsPirate.split(",")[0]);
                    Pirate p2 = rel.getListePirate().get(nomsPirate.split(",")[1]);
                    if (rel.getdeteste().containsKey(p1)) {
                        rel.getdeteste().get(p1).add(p2);
                    } else {
                        deteste1.add(p2);
                        rel.getdeteste().put(p1, deteste1);
                    }
                    if (rel.getdeteste().containsKey(p2)) {
                        rel.getdeteste().get(p2).add(p1);
                    } else {
                        deteste2.add(p1);
                        rel.getdeteste().put(p2, deteste2);
                    }
                }

                if(ligne.startsWith("preferences")) {
                    nomsPirObj = subligne;
                    String [] EnsPirObj = nomsPirObj.split(",");
                    if(!rel.getListePirate().containsKey(EnsPirObj[0])){
                        throw new EmptyObjectException("Veuillez revoir la ligne "+nbligne+" du fichier  car le premier argument de preference doit être un nom de pirate existant");
                    }
                    if (EnsPirObj.length != rel.getListePirate().size()+1 ) {
                        throw new EmptyObjectException("Veuillez revoir la ligne "+nbligne+ " du fichier car vous avez oublié de saisir un objet dans la liste de préférence d'un pirate");
                    }
                    //A Traiter le cas où l'utilisateur oublie d'entrer le nom des 3 objets dans preference
                    ArrayList<Objet> listObj = new ArrayList<>();
                    for(int i = 1; i<EnsPirObj.length; i++) {
                        if(!rel.getListeObj().containsKey(EnsPirObj[i])){
                            throw new EmptyObjectException("Veuillez revoir la ligne "+nbligne+" du fichier car un nom d'objet n'existe pas dans la liste de preference");
                        }
                        if(listObj.contains(rel.getListeObj().get(EnsPirObj[i]))){
                            throw new EmptyObjectException("Veuillez revoir la ligne "+nbligne+ " du fichier car vous avez saisi deux fois le même objet dans la liste de preference");
                        }else {
                            listObj.add(rel.getListeObj().get(EnsPirObj[i]));
                        }
                        rel.getPreference().put(rel.getListePirate().get(EnsPirObj[0]), listObj);

                    }

                }

            }
        }catch (FileNotFoundException e) {
            System.err.println("Veuillez revoir la saisie du chemin du fichier car le fichier est introuvable");
        } catch (IOException e) {
            e. printStackTrace ();
            System.err.println(e.getMessage());
        }
        if(nom == null) {
            System.err.println("Veuillez revoir le fichier car le nom du pirate n'a pas ete indiqué");
            System.exit(0);
        }
        if(nomObjet == null) {
            System.err.println("Veuillez revoir le fichier car le nom de l'objet n'a pas ete indiqué");
            System.exit(0);
        }
        if (rel.getListeObj().size() != rel.getListePirate().size()) {
            throw new EmptyObjectException("Veuillez revoir le fichier, car le nombre d'objet saisi est different du nombre de pirate saisi");
        }
        if(nomsPirate == null) {
            System.err.println("Veuillez revoir le fichier car la relation d'affinité entre les pirates n'a pas ete indiqué");
            System.exit(0);
        }
        if(nomsPirObj == null) {
            System.err.println("Veuillez revoir le fichier car la liste de preferences d'un pirate n'a pas ete indiqué");
            System.exit(0);
        }
    }

}
