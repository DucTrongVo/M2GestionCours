package m2.miage.m2gestioncours.services;

import m2.miage.m2gestioncours.entities.Cours;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICoursService {

    @Transactional
    Cours creerCours(Cours cours);

    List<Cours> getListAllCours();
}
