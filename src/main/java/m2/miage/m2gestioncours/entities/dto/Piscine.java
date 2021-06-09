package m2.miage.m2gestioncours.entities.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
public class Piscine implements Serializable {
    private static final long serialVersionUID = -6315647524096088992L;

    @Id
    private String recordId;

    private String nom;

    private String  adresse;

    private List<String> telephones;

    private String longitude;

    private String latitude;

    private String accessibilite;

    private String nomComplet;

}
