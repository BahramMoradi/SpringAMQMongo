package dk.bam.demo.utils.models;

import lombok.Data;

@Data
public class MongoDocumentCommand {
    private String id;
    private String status;
    private String key;
    private String value;

}
