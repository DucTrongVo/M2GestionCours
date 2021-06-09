package m2.miage.m2gestioncours.exception;

public class ArgumentErrorException extends IllegalArgumentException {

    private static final long serialVersionUID = -4474605405011960301L;

    public ArgumentErrorException(String s) { super(s);}

    public ArgumentErrorException(Throwable t) {super(t);}
}
