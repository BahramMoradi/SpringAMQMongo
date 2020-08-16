package dk.bam.demo.services.validations;

import dk.bam.demo.utils.models.MongoDocumentCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class MongoDocumentValidator {
    public boolean isMongoDocumentCommandNull(MongoDocumentCommand cmd) {
        return cmd == null;
    }

    public boolean isMongoDocumentCommandIdValid(MongoDocumentCommand cmd) {
        return !isMongoDocumentCommandNull(cmd) && cmd.getId() != null;
    }

    public boolean isIdValid(String id) {
        return id != null && !StringUtils.isEmpty(id);
    }
}
