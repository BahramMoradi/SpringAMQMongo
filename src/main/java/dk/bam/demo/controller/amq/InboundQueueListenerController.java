package dk.bam.demo.controller.amq;

import dk.bam.demo.services.queues.InboundQueueService;
import dk.bam.demo.utils.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InboundQueueListenerController {

    private InboundQueueService inboundQueueService;

    @Autowired
    public void setInboundQueueService(InboundQueueService inboundQueueService) {
        this.inboundQueueService = inboundQueueService;
    }

    @JmsListener(destination = "in.queue")
    public void receiveMessage(Message msg) {
        log.info("Message received from inbound queue: " + msg.toString());
        inboundQueueService.processIncomingMessage(msg);
    }
}
