package utilisateur;

/**
 * Erreur jetee quand une erreur dans le format du fichier txt est detectee
 * 
 * @author Gaétan De Castellane
 * @since 1.0
 */
public class ErrFormatFichier extends Exception{
    public ErrFormatFichier(){}
    public ErrFormatFichier(String s){super(s);}
}
