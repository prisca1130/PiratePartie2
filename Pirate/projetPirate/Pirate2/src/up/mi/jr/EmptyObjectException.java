package up.mi.jr;

public class EmptyObjectException extends Exception{



    /**
     * Crée l'exception
     */
    public EmptyObjectException() {
        super("L'objet manipulé est vide");
    }

    /**
     * Crée l'exception avec un message d'erreur
     * @param s	message d'erreur
     */
    public EmptyObjectException(String s) {
        super(s);
    }
}
