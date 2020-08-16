package dk.bam.demo.integrations.amq;





public interface QueueClient extends Client {

    /**
     * Put element on the queue for handler.
     *
     * @param obj
     * @return
     */
    QueueClient inbound(Object obj);

    /**
     * Put element on queue for sender.
     * @param obj
     * @return
     */
    QueueClient outbound(Object obj);
}

