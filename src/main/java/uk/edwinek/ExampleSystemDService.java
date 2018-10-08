package uk.edwinek;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uk.edwinek.service.MessagerService;

public class ExampleSystemDService implements Daemon {

    private MessagerService messagerService;

    @Override
    public void init(DaemonContext context) {
        messagerService =
                new AnnotationConfigApplicationContext(ExampleSystemDConfig.class)
                        .getBean(MessagerService.class);
    }

    @Override
    public void start() {
        messagerService.startMessaging();
    }

    @Override
    public void stop() {
        messagerService.stopMessaging();
    }

    @Override
    public void destroy() {}
}
