package m2.miage.m2gestioncours.services;

import m2.miage.m2gestioncours.Enum.SequenceEnum;
import m2.miage.m2gestioncours.controllers.CoursController;
import m2.miage.m2gestioncours.entities.Cours;
import m2.miage.m2gestioncours.exception.ArgumentErrorException;
import m2.miage.m2gestioncours.exception.ForbiddenException;
import m2.miage.m2gestioncours.exception.NotFoundException;
import m2.miage.m2gestioncours.repositories.CoursRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoursServiceImpl implements ICoursService{
    private static final Logger logger = LoggerFactory.getLogger(CoursServiceImpl.class);

    private final int MAX_STUDENT_NUMBER = 15;
    private final int MAX_STUDENT_NUMBER_TEST = 2;

    @Autowired
    CoursRepository coursRepository;

    @Autowired
    ISequenceService sequenceService;

    @Autowired
    IToolService toolService;

    @Override
    public Cours creerCours(Cours cours, boolean isEnseignant, boolean enseignatnApte, int niveauExpEnseignant) {
        LocalDateTime currentTime = LocalDateTime.now();
        // verifier date de début
        if (toolService.getDateDifferent(currentTime, toolService.getDateTimeFromString(cours.getDateDebut())) < 7){
            logger.error("La date d’un cours doit toujours être supérieure de 7 jours calendaires par rapport à la date de saisie!");
            throw new ArgumentErrorException("La date d’un cours doit toujours être supérieure de 7 jours calendaires par rapport à la date de saisie!");
        }
        // veirifier list étudiant non null
        if (cours.getIdEtudiants() == null){
            cours.setIdEtudiants(new ArrayList<>());
        }
        // verifier enseignant
        if (!isEnseignant){
            String err = "La personne d'id "+cours.getIdEnseignant()+" n'est pas un enseignant";
            logger.error(err);
            throw new ArgumentErrorException(err);
        }
        if (!enseignatnApte){
            String err = "L'enseignant' d'id "+cours.getIdEnseignant()+" est NON APTE";
            logger.error(err);
            throw new ArgumentErrorException(err);
        }
        if (niveauExpEnseignant <= cours.getNiveauCible()){
            String err = "Niveau d'expertise de l'enseignant' d'id "+cours.getIdEnseignant()+" insuffisant!";
            logger.error(err);
            throw new ArgumentErrorException(err);
        }
        // verifier nombre étudiants max
        if (cours.getPlaceDisponible() == null){
            cours.setPlaceDisponible(MAX_STUDENT_NUMBER_TEST);
        }
        cours.setId(sequenceService.generateSequence(SequenceEnum.COURS_SEQUENCE.getValue()));
        return coursRepository.save(cours);
    }

    @Override
    public List<Cours> getListAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public Cours inscrit(int idCours, int idEtudiant, int niveauEtudiant, boolean isEtudiant) throws NotFoundException, ForbiddenException {
        Cours cours = verifyAndGetCours(idCours);
        if (!isEtudiant){
            logger.error("Seulement un étudiant peut inscrit dans ce cours!");
            throw new ForbiddenException("Seulement un étudiant peut inscrit dans ce cours!");
        }
        // verifier nombre étudiant max
        if (cours.getIdEtudiants().size() == MAX_STUDENT_NUMBER_TEST){
            logger.error("Maximum nombre d'étudiants atteint!");
            throw new ForbiddenException("Maximum nombre d'étudiants atteint!");
        }
        // verifier date début d'inscription
        if (toolService.getDateTimeFromString(cours.getDateDebut()).compareTo(LocalDateTime.now()) < 0){
            String err = "Impossible d'inscrit dans un cours qui est déja eu lieu (dateDébut est "+cours.getDateDebut()+")!";
            logger.error(err);
            throw new ForbiddenException(err);
        }
        // verifier niveau étudiant
        if (cours.getNiveauCible() != niveauEtudiant){
            String err = "Niveau d'expertise d'étudiant d'id "+idEtudiant+" est NON conforme au niveau du cours";
            logger.error(err);
            throw new ForbiddenException(err);
        }
        List<Integer> listEtudiant = cours.getIdEtudiants();
        if (listEtudiant.contains(idEtudiant)){
            String err = "Etudiant d'id "+idEtudiant+" est déja inscrit dans le cours "+cours.getNom();
            logger.error(err);
            throw new ArgumentErrorException(err);
        }
        listEtudiant.add(idEtudiant);
        cours.setIdEtudiants(listEtudiant);
        return coursRepository.save(cours);
    }

    @Override
    public Cours desinscrit(int idCours, int idEtudiant) throws NotFoundException, ForbiddenException {
        Cours cours = verifyAndGetCours(idCours);
        if (cours.getIdEtudiants().size() == MAX_STUDENT_NUMBER_TEST){
            logger.error("Maximum nombre d'étudiants atteint!");
            throw new ForbiddenException("Maximum nombre d'étudiants atteint!");
        }
        List<Integer> listEtudiant = cours.getIdEtudiants();
        if (!listEtudiant.contains(idEtudiant)){
            String err = "Etudiant d'id "+idEtudiant+" n'est pas inscrit dans le cours "+cours.getNom();
            logger.error(err);
            throw new ArgumentErrorException(err);
        }
        listEtudiant.remove(idEtudiant);
        cours.setIdEtudiants(listEtudiant);
        return coursRepository.save(cours);
    }

    Cours verifyAndGetCours(int idCours) throws NotFoundException {
        Optional<Cours> mayCours = coursRepository.findById(idCours);
        if (mayCours.isEmpty()){
            String err = "Cours d'id "+idCours+" not found!";
            logger.error(err);
            throw new NotFoundException(err);
        }
        return mayCours.get();
    }
}
