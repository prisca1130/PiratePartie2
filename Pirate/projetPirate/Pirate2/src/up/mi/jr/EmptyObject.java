package up.mi.jr;

public class EmptyObject extends Exception{



    /**
     * Crée l'exception
     */
    public EmptyObject() {
        super("Données dans fichier dataInput sont incorrectes");
    }

    /**
     * Crée l'exception avec un message d'erreur
     * @param s	message d'erreur
     */
    public EmptyObject(String s) {
        super(s);
    }
}
