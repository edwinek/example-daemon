package uk.edwinek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.edwinek.messager.Messager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class MessagerServiceImpl implements MessagerService {

    private final Messager messager;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    public MessagerServiceImpl(Messager messager) {
        this.messager = messager;
    }

    @Override
    public void startMessaging() {
        executor.scheduleWithFixedDelay(messager::runningMessage, 2L, 2L, TimeUnit.SECONDS);
    }

    @Override
    public void stopMessaging() {
        messager.receivedShutDown();
        executor.shutdown();
        messager.shutDownMessage();
    }
}
