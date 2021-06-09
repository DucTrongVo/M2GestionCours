package m2.miage.m2gestioncours.repositories;

import m2.miage.m2gestioncours.entities.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursRepository extends MongoRepository<Cours, Integer> {
    Optional<Cours> findById(int idCours);
}
