package up.mi.jr;

public class EmptyObject extends Exception{



    /**
     * Crée l'exception
     */
    public EmptyObject() {
        super("L'objet manipulé est vide");
    }

    /**
     * Crée l'exception avec un message d'erreur
     * @param s	message d'erreur
     */
    public EmptyObject(String s) {
        super(s);
    }
}
