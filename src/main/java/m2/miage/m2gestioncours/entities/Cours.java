package m2.miage.m2gestioncours.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import m2.miage.m2gestioncours.Enum.SequenceEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@SuperBuilder
@Document(collation = "cours")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cours implements Serializable {

    private static final long serialVersionUID = 8492577594429994349L;

    @Transient
    public static final String SEQUENCE_NAME = SequenceEnum.COURS_SEQUENCE.getValue();

    @Id
    private Integer id;

    @Field("nom")
    private String nom;

    @Field("niveauCible")
    private int niveauCible;

    @Field("dateDebut")
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private String dateDebut;

    @Field("idEnseignant")
    private int idEnseignant;

    @Field("idlieu")
    private String idLieu;

    @Field("duree")
    private int duree;

    @Field("placeDisponible")
    private Integer placeDisponible;

    @Field("idEtudiants")
    private List<Integer> idEtudiants;
}
