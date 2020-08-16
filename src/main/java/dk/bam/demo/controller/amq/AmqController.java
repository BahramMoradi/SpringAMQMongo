package dk.bam.demo.controller.amq;

import dk.bam.demo.integrations.amq.QueueClient;
import dk.bam.demo.utils.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AmqController {
    private QueueClient client;

    @Autowired
    public void setClient(QueueClient client) {
        this.client = client;
    }

    @PostMapping(value = "/sendToInboundQueue")
    public ResponseEntity<HttpStatus> send(@RequestBody Message message) {
        log.info("Sending message to inbound queue: " + message.toString());
        client.inbound(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
