package m2.miage.m2gestioncours.exception;

public class GeneralErreurException extends Exception{

    private static final long serialVersionUID = 35928888650679218L;

    public GeneralErreurException() {
        String s = "Une erreur est survenue. Veuillez contactez notre développeur pour plus d'informaiton!";
    }

    public GeneralErreurException(Throwable t) {super(t);}
}
