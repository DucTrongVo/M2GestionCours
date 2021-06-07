package m2.miage.m2gestioncours.exception;

public class NotFoundException extends Exception{

    private static final long serialVersionUID = 4535550242598196364L;

    public NotFoundException(String s) { super(s);}

    public NotFoundException(Throwable t) {super(t);}
}
