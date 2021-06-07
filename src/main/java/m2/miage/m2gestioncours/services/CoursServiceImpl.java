package m2.miage.m2gestioncours.services;

import m2.miage.m2gestioncours.SequenceEnum;
import m2.miage.m2gestioncours.entities.Cours;
import m2.miage.m2gestioncours.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursServiceImpl implements ICoursService{

    @Autowired
    CoursRepository coursRepository;

    @Autowired
    ISequenceService sequenceService;

    @Override
    public Cours creerCours(Cours cours) {
        cours.setId(sequenceService.generateSequence(SequenceEnum.COURS_SEQUENCE.getValue()));
        return coursRepository.save(cours);
    }

    @Override
    public List<Cours> getListAllCours() {
        return coursRepository.findAll();
    }
}
