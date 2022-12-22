package utilisateur;

//Erreur jetee quand une erreur dans le format du fichier txt est detectee
public class ErrFormatFichier extends Exception{
    public ErrFormatFichier(){}
    public ErrFormatFichier(String s){super(s);}
}
