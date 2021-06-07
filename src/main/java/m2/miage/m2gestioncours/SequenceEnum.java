package m2.miage.m2gestioncours;

public enum SequenceEnum {
    COURS_SEQUENCE("cours_sequence");

    private final String value;
    SequenceEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
