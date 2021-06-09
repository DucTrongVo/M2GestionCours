package m2.miage.m2gestioncours.services;

import m2.miage.m2gestioncours.entities.Cours;
import m2.miage.m2gestioncours.exception.ForbiddenException;
import m2.miage.m2gestioncours.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICoursService {

//    @Transactional
//    Cours creerCours(Cours cours);
    @Transactional
    Cours creerCours(Cours cours, boolean isEnseignant, boolean enseignatnApt, int niveauExpEnseignant);

    List<Cours> getListAllCours();

    @Transactional
    Cours inscrit(int idCours, int idEtudiant, int niveauEtudiant, boolean isEtudiant) throws NotFoundException, ForbiddenException;

    @Transactional
    Cours desinscrit(int idCours, int idEtudiant) throws NotFoundException, ForbiddenException;
}
