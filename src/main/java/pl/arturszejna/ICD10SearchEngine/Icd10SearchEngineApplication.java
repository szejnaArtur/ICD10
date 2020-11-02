package pl.arturszejna.ICD10SearchEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.arturszejna.ICD10SearchEngine.service.DiseaseService;

import javax.xml.ws.Service;

@SpringBootApplication
public class Icd10SearchEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(Icd10SearchEngineApplication.class, args);

    }
}
