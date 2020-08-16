package dk.bam.demo.services.validations;

import dk.bam.demo.utils.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * simple validator for AMQ messages
 */
@Slf4j
@Service
public class MessageValidatorService {

    public boolean isMessageIdValid(Message msg) {
        if (msg.getId() == null) {
            log.error("Message id must not be null: " + msg.toString());
            return false;
        }

        if (StringUtils.isEmpty(msg.getId().trim())) {
            log.error("Message id must not be empty: " + msg.toString());
            return false;
        }

        return true;
    }
}
