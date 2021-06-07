package m2.miage.m2gestioncours.exception;

public class ForbiddenException extends Exception{

    private static final long serialVersionUID = 2532067348865807636L;

    public ForbiddenException(String s) { super(s);}

    public ForbiddenException(Throwable t) {super(t);}
}
