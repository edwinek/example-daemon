package uk.edwinek;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("config.properties")
@ComponentScan({"uk.edwinek.messager", "uk.edwinek.service"})
public class ExampleSystemDConfig {}
