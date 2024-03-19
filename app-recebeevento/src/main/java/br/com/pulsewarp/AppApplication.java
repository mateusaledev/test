package br.com.pulsewarp;

import br.com.pulsewarp.adapters.config.kafka.KafkaConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.TimeZone;
import java.util.logging.Logger;

@SpringBootApplication
@EnableKafka
@Import(KafkaConfiguration.class)
public class AppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("AppApplication");
        logger.info("Starting application...");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // No operations needed currently
    }
}
