package m2.miage.m2gestioncours.repositories;

import m2.miage.m2gestioncours.entities.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends MongoRepository<Cours, Integer> {
}
