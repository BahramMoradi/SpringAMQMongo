package dk.bam.demo.services.crud;

import com.mongodb.client.result.DeleteResult;
import dk.bam.demo.domain.MongoDocument;
import dk.bam.demo.utils.models.MongoDocumentCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MongoCrudService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoDocument create(MongoDocumentCommand cmd) {
        return mongoTemplate.save(createMongoDocument(cmd));
    }

    public MongoDocument update(MongoDocumentCommand cmd) {
        MongoDocument doc = mongoTemplate.findById(cmd.getId(), MongoDocument.class);
        if (doc == null) {
            log.error("Could not find document to update: cmd command " + cmd.toString());
            return null;
        }
        BeanUtils.copyProperties(cmd, doc);
        log.info("Update action");
        log.info("CMD data: " + cmd.toString());
        log.info("Document data: " + doc.toString());
        return mongoTemplate.save(doc);
    }

    public DeleteResult delete(String id) {
        MongoDocument doc = mongoTemplate.findById(id, MongoDocument.class);
        if (doc != null) {
            return mongoTemplate.remove(doc);
        } else {
            return null;
        }
    }

    public MongoDocument get(String id) {
        return mongoTemplate.findById(id, MongoDocument.class);
    }

    public List<MongoDocument> all() {
        return mongoTemplate.findAll(MongoDocument.class);
    }


    public MongoDocument createMongoDocument(MongoDocumentCommand cmd) {
        MongoDocument document = new MongoDocument();
        BeanUtils.copyProperties(cmd, document);
        return document;
    }
}
