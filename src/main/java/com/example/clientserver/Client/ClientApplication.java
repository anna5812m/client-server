package com.example.clientserver.Client;

import com.example.clientserver.model.Repetition;
import com.example.clientserver.model.RepetitionRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication app = new
                SpringApplication(ClientApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    private Logger log = LoggerFactory.getLogger
            (ClientApplication.class);

@Bean
public CommandLineRunner process(RepetitionRestClient client) {
    return args -> {
        Iterable<Repetition> repetitions = client.findAll();
        assert repetitions != null;
        repetitions.forEach(repetition -> log.info(repetition.toString()));
        Repetition newRepetition = client.upsert(new Repetition("Drink plenty of Water daily!"));
        assert newRepetition != null;
        log.info(newRepetition.toString());
        Repetition repetition = client.findById(newRepetition.getId());
        assert repetitions != null;
        log.info(repetitions.toString());
        Repetition completed = client.setCompleted(newRepetition.getId());
        assert completed.isCompleted();
        log.info(completed.toString());
        client.delete(newRepetition.getId());
        assert client.findById(newRepetition.getId()) == null;
    };
}
}
