package m2.miage.m2gestioncours.services;

import m2.miage.m2gestioncours.entities.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceServiceImpl implements ISequenceService {

    @Autowired
    private MongoOperations operations;

    @Override
    public int generateSequence(String seqName) {
        final Query q = new Query(Criteria.where("seq_name").is(seqName));
        final Update u = new Update().inc("seq_count", 1);
        final DatabaseSequence counter = operations.findAndModify(q, u, FindAndModifyOptions.options().returnNew(true).upsert(true), DatabaseSequence.class);

        return !Objects.isNull(counter) ? counter.getSeq_count() : 1;
    }
}
