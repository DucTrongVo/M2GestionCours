package m2.miage.m2gestioncours.entities.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ExceptionResponse {
    private Integer codeErreur;
    private String messageErreur;
}
