package dk.bam.demo.controller.amq;

import dk.bam.demo.utils.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OutboundQueueListenerController {

    @JmsListener(destination = "out.queue")
    public void receiveMessage(Message message) {
        log.info("Message received: " + message.toString());
    }
}
