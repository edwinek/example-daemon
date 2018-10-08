package uk.edwinek.messager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessagerImpl implements Messager {

    private static final String RUNNING_MESSAGE_KEY = "${messager.message.running}";
    private static final String RECEIVED_SHUT_DOWN_MESSAGE_KEY = "${messager.message.receivedshutdown}";
    private static final String SHUT_DOWN_MESSAGE_KEY = "${messager.message.shutdown}";

    private static Logger LOGGER = LoggerFactory.getLogger(MessagerImpl.class);

    private final String runningMessage;
    private final String receivedShutDownMessage;
    private final String shutDownMessage;

    @Autowired
    public MessagerImpl(@Value(RUNNING_MESSAGE_KEY) String runningMessage,
                        @Value(RECEIVED_SHUT_DOWN_MESSAGE_KEY) String receivedShutDownMessage,
                        @Value(SHUT_DOWN_MESSAGE_KEY) String shutDownMessage) {
        this.runningMessage = runningMessage;
        this.receivedShutDownMessage = receivedShutDownMessage;
        this.shutDownMessage = shutDownMessage;
    }

    @Override
    public void runningMessage() {
        LOGGER.info(runningMessage);
    }

    @Override
    public void receivedShutDown() {
        LOGGER.info(receivedShutDownMessage);
    }

    @Override
    public void shutDownMessage() {
        LOGGER.info(shutDownMessage);
    }
}
