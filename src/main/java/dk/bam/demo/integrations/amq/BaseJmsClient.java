package dk.bam.demo.integrations.amq;

/**
 * The BaseJmsClient is used to communicate between the CommonRequestHandler
 * and an arbitrary node. This class determines how namespacing works
 * on your queue implementation.
 */
public abstract class BaseJmsClient {

    /**
     * The inbound channel scope.
     */
    private static final String INBOUND_QUEUE_SCOPE = "in";

    /**
     * The outbound channel scope.
     */
    private static final String OUTBOUND_QUEUE_SCOPE = "out";

    /**
     * Namespace delimiter to separate channel parts.
     */
    private static final String NAMESPACE_DELIMITER = ".";

    /**
     * Constructs the namespace given an object and a scope.
     *
     * @param scope
     * @param obj
     * @return
     */
    public static String resolveNamespace(String scope, Object obj) {
        return scope + NAMESPACE_DELIMITER + "queue";//obj.getClass().getSimpleName();
    }

    /**
     * Implement how to send a message with the queue.
     *
     * @param address
     * @param obj
     * @return
     */
    public abstract QueueClient send(String address, final Object obj);

    /**
     * Send a message into the CommonRequestHandler.
     *
     * @param obj
     */
    public QueueClient inbound(Object obj) {
        return send(resolveNamespace(INBOUND_QUEUE_SCOPE, obj), obj);
    }

    /**
     * Sends a message out of the CommonRequestHandler.
     *
     * @param obj
     */
    public QueueClient outbound(Object obj) {
        return send(resolveNamespace(OUTBOUND_QUEUE_SCOPE, obj), obj);
    }

}

