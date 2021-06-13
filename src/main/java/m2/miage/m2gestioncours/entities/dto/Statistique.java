package m2.miage.m2gestioncours.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistique implements Serializable
{

    private static final long serialVersionUID = 6902972415999696266L;
    private int nombreCours;
}
