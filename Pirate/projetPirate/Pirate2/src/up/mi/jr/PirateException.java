package up.mi.jr;

/**
 * Classe d'exception pour gérer les erreurs
 *
 * @author Abisha Jeyavel, Lalariniaina Ramanantoanina
 * @version 1.1
 */
public class PirateException extends Exception{

    /**
     * Crée l'exception avec un message d'erreur
     * @param s	message d'erreur
     */
    public PirateException(String s) {
        super(s);
    }
}
