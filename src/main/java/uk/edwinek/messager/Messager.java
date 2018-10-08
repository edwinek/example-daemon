package uk.edwinek.messager;

public interface Messager {
    /**
     * Logs a message indicating that a service is currently running.
     */
    void runningMessage();

    /**
     * Logs a messaging indicating that a service has received a shut down message.
     */
    void receivedShutDown();

    /**
     * Logs a message indicating that the service is about to terminate, having shut down its components gracefully.
     */
    void shutDownMessage();
}
