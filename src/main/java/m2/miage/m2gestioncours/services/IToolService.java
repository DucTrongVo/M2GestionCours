package m2.miage.m2gestioncours.services;

import java.time.LocalDateTime;

public interface IToolService {
    LocalDateTime getDateTimeFromString(String dateTime);

    String getStringFromDateTime(LocalDateTime dateTime);

    int getDateDifferent(LocalDateTime dateBefore, LocalDateTime dateAfter);
}
