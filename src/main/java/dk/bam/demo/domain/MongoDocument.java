package dk.bam.demo.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class MongoDocument {
    @Id
    private String id;
    private String status;
    private String key;
    private String value;
}
