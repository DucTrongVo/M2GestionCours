package m2.miage.m2gestioncours.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;

    @Field("seq_name")
    private String seq_name;

    @Field("seq_count")
    private int seq_count;
}