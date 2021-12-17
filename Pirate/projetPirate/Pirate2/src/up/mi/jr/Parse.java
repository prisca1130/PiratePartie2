package up.mi.jr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Permet de récupérer des données du fichier (donné par l'utilisateur) pour créer des objets
 * @author Abisha Jeyavel, Lalariniaina Ramanantoanina
 * @version 1.1
 *
 */
public class Parse {
    /**
     * Le fichier associé au fichier de l'utilisateur
     */
    private File fichier;

    /**
     * Objet de type Relation représentant les différentes relation entre pirates et entre pirates et objets
     */
    private Relation rel;

    /**
     * Le numéro de la ligne dans le fichier donné par l'utilisateur
     */
    private int nbligne;

    /**
     * Constructeur pour une recuperation de données à partir d'un fichier
     * @param fichier Le fichier associé au fichier de l'utilisateur
     * @param rel Objet de type Relation représentant les différentes relation entre pirates et entre pirates et objets
     */
    public Parse(File fichier, Relation rel) {
        this.fichier = fichier;
        this.rel=rel;
        this.nbligne=0;
    }

    /**
     * Permet de parser les pirates, les objets, les relations d'affinité entre les pirates et les ordres de préférences des objets pour chaque pirate
     * @throws PirateException on relaie le traitement de l'exception au niveau de la méthode « appelante »
     */
    public void parserFichier()throws PirateException {
        String nom = null;
        String nomObjet = null;
        String nomsPirate = null;
        String nomsPirObj = null;
        try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
            String ligne;
            String subligne= null;
            while ((ligne = br.readLine()) != null) {
                nbligne++;
                //Gestion des erreurs liées à la forme du fichier
                if(ligne.isEmpty()){
                    throw new PirateException("la ligne "+ nbligne+" est vide");
                }

                if(!ligne.endsWith(".")){
                  throw new PirateException("Veuillez revoir le fichier car la ligne "+nbligne+" ne se termine pas par un point");
                }
               try {
                subligne = ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"));
               }catch (StringIndexOutOfBoundsException e){
                   System.err.println("Veuillez mettre les parenthèse correctement à la ligne "+nbligne);
               }

               if(!ligne.split("\\)")[1].equals(".")){
                   throw new PirateException("Veuillez revoir la ligne "+nbligne+" du fichier car il y a au moins un caractère incorrect entre la parenthèse fermante et le point");
               }

               if( !(ligne.startsWith("pirate") || ligne.startsWith("objet") || ligne.startsWith("deteste") || ligne.startsWith("preference"))){
                   throw new PirateException("Veuillez revoir le fichier car la ligne "+nbligne+" commence par un mot inconnu");
               }

               //Partie pour récuperer les pirates
               if (ligne.startsWith("pirate")) {
                   nom = subligne;
                   if(nom.split(",").length != 1){
                       throw new PirateException("\"Veuillez revoir le fichier car à la ligne "+nbligne+" le pirate a plusieurs noms");
                   }
                   if (rel.getListePirate().containsKey(nom)) {
                        throw new PirateException("Veuillez revoir la ligne "+nbligne+" du fichier car vous avez saisi deux fois le nom d'un pirate");
                   }
                   rel.getListePirate().put(nom, new Pirate(nom));
                }

                //Partie pour récuperer les objets
               if (ligne.startsWith("objet")) {
                    nomObjet = subligne;
                    if(nomObjet.split(",").length != 1){
                        throw new PirateException("\"Veuillez revoir le fichier car à la ligne "+nbligne+" l'objet a plusieurs noms");
                    }
                    if (rel.getListeObj().containsKey(nomObjet)) {
                        throw new PirateException("Veuillez revoir la ligne "+nbligne+" du fichier car vous avez saisi deux fois le nom d'un objet");
                    }
                    rel.getListeObj().put(nomObjet, new Objet(nomObjet));
                }

                //Partie pour récuperer les affinités entre les pirates
               if (ligne.startsWith("deteste") ) {
                    nomsPirate = subligne;
                    if(nomsPirate.split(",").length != 2){
                        throw new PirateException("\"Veuillez revoir la ligne "+nbligne+" du fichier car deteste peut avoir que deux arguments");
                    }
                    ArrayList<Pirate> deteste1 = new ArrayList<>();
                    ArrayList<Pirate> deteste2 = new ArrayList<>();
                    if (!(rel.getListePirate().containsKey(nomsPirate.split(",")[0]) && rel.getListePirate().containsKey(nomsPirate.split(",")[1]))) {
                        throw new PirateException("Veuillez revoir la ligne "+nbligne+" du fichier car le nom du pirate n'existe pas");
                    }
                    if (nomsPirate.split(",")[0].equals(nomsPirate.split(",")[1])) {
                        throw new PirateException("Veuillez revoir la ligne "+nbligne+" du fichier car un pirate ne peut se détester lui-même");
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

                //Partie pour récuperer les preferences des pirates
               if(ligne.startsWith("preferences")) {
                    nomsPirObj = subligne;
                    String [] EnsPirObj = nomsPirObj.split(",");
                    if(!rel.getListePirate().containsKey(EnsPirObj[0])){
                        throw new PirateException("Veuillez revoir la ligne "+nbligne+" du fichier  car le premier argument de preference doit être un nom de pirate existant");
                    }
                    if (EnsPirObj.length != rel.getListePirate().size()+1 ) {
                        throw new PirateException("Veuillez revoir la ligne "+nbligne+ " du fichier car vous avez oublié de saisir un objet dans la liste de préférence d'un pirate");
                    }
                    ArrayList<Objet> listObj = new ArrayList<>();
                    for(int i = 1; i<EnsPirObj.length; i++) {
                        if(!rel.getListeObj().containsKey(EnsPirObj[i])){
                            throw new PirateException("Veuillez revoir la ligne "+nbligne+" du fichier car un nom d'objet n'existe pas dans la liste de preference");
                        }
                        if(listObj.contains(rel.getListeObj().get(EnsPirObj[i]))){
                            throw new PirateException("Veuillez revoir la ligne "+nbligne+ " du fichier car vous avez saisi deux fois le même objet dans la liste de preference");
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
            throw new PirateException("Veuillez revoir le fichier, car le nombre d'objet saisi est different du nombre de pirate saisi");
        }
        if(nomsPirate == null) {
            System.err.println("Veuillez revoir le fichier car la relation d'affinité entre les pirates n'a pas ete indiqué");
            System.exit(0);
        }
        if(nomsPirObj == null) {
            System.err.println("Veuillez revoir le fichier car la liste de preferences d'un pirate n'a pas ete indiqué");
            System.exit(0);
        }
        if (rel.getPreference().size() != rel.getListePirate().size()) {
            throw new PirateException("Veuillez revoir le fichier au niveau de \"preference\" \n car il faut saisir autant de relation de préférence que de nombre de pirate");
        }
    }

}
