package cz.cvut.fit.tjv.ohraband.farming_monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Needs to be in root package because of automatic component scan

@SpringBootApplication
public class RestServer {
    public static void main(String... args) {
        SpringApplication.run(RestServer.class, args);
    }
}

