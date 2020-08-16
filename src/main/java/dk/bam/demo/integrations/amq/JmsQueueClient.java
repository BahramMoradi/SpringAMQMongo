package dk.bam.demo.integrations.amq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class JmsQueueClient extends BaseJmsClient implements QueueClient {

    private JmsTemplate queue;

    @Autowired
    public void setQueue(JmsTemplate queue) {
        this.queue = queue;
    }

    @Override
    public QueueClient send(String address, final Object obj) {
        queue.convertAndSend(address, obj);
        return this;
    }
}

