package dk.bam.demo.services.queues;

import dk.bam.demo.domain.MongoDocument;
import dk.bam.demo.integrations.amq.QueueClient;
import dk.bam.demo.services.validations.MessageValidatorService;
import dk.bam.demo.utils.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class InboundQueueService {

    private QueueClient queueClient;
    private MongoTemplate mongoTemplate;
    private MessageValidatorService messageValidator;

    @Autowired
    public void setQueueClient(QueueClient queueClient) {
        this.queueClient = queueClient;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    public void setMessageValidator(MessageValidatorService messageValidator) {
        this.messageValidator = messageValidator;
    }

    /**
     * This method update en existing document in the mongo with the information in the message.
     * This method is only for updating purpose and not for saving new document in mongo database
     *
     * @param msg
     */
    @Transactional
    public void processIncomingMessage(Message msg) {
        if (messageValidator.isMessageIdValid(msg)) {
            MongoDocument document = mongoTemplate.findById(msg.getId(), MongoDocument.class);
            if (document != null) {
                updateMongoDocument(msg, document);
                queueClient.outbound(msg);
            } else {
                log.error("Can not find mongo document with id " + msg.getId());
            }
        } else {
            log.error("AMQ Message not valid " + msg.toString());
        }

    }

    public void updateMongoDocument(Message msg, MongoDocument document) {
        BeanUtils.copyProperties(msg, document);
        mongoTemplate.save(document);
    }
}
