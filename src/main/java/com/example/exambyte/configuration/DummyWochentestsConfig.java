package com.example.exambyte.configuration;

import com.example.exambyte.ExamByteApplication;
import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.builder.WochenTestBuilder;
import com.example.exambyte.domain.model.*;
import com.example.exambyte.application.service.WochenTestService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = ExamByteApplication.class)
public class DummyWochentestsConfig {

    // Adds a few example WochenTest objects
    @Bean
    @Order(1)
    ApplicationRunner addExampleWochentests(WochenTestService wochenTestService) {
        return args -> {

            Frage frage1woche1 = new FrageBuilder()
                    .addFragetyp(FRAGENTYP.FRAGENTYP_FREITEXT)
                    .addName("Routing")
                    .addFragestellung("Was versteht man unter Routing im Kontext von SpringWebMVC?")
                    .addMaxPunktzahl(2)
                    .build();

            Frage frage2woche1 = new FrageBuilder()
                    .addFragetyp(FRAGENTYP.FRAGENTYP_MULTIPLECHOICE)
                    .addName("HTTP-Verben")
                    .addFragestellung("Welches dieser HTTP Verben ist safe?")
                    .addAntwortmoeglichkeiten(List.of(
                            new AntwortMoeglichkeit("GET", true),
                            new AntwortMoeglichkeit("POST", false),
                            new AntwortMoeglichkeit("PUT", false),
                            new AntwortMoeglichkeit("DELETE", false)))
                    .addMaxPunktzahl(2)
                    .build();

            WochenTest dummyTest1 = new WochenTestBuilder()
                    .addName("Woche1")
                    .addStartTime(LocalDateTime.now())
                    .addEndTime(LocalDateTime.now().plusDays(1))
                    .addStatus(STATUS.STATUS_AUSSTEHEND)
                    .addFrage(frage1woche1)
                    .addFrage(frage2woche1)
                    .build();

            Frage frage1woche2 = new FrageBuilder()
                    .addFragetyp(FRAGENTYP.FRAGENTYP_MULTIPLECHOICE)
                    .addName("Validierung")
                    .addFragestellung("Mit welchem Objekt prüft man auf aufgetretene Validierungsfehler?")
                    .addAntwortmoeglichkeiten(List.of(
                            new AntwortMoeglichkeit("RedirectAttributes", true),
                            new AntwortMoeglichkeit("Model", false),
                            new AntwortMoeglichkeit("BindingResult", false)))
                    .addMaxPunktzahl(2)
                    .build();

            WochenTest dummyTest2 = new WochenTestBuilder()
                    .addName("Woche2")
                    .addStartTime(LocalDateTime.now().plusDays(2))
                    .addEndTime(LocalDateTime.now().plusDays(3))
                    .addStatus(STATUS.STATUS_BEARBEITBAR)
                    .addFrage(frage1woche2)
                    .build();

            Frage frage1woche3 = new FrageBuilder()
                    .addFragetyp(FRAGENTYP.FRAGENTYP_MULTIPLECHOICE)
                    .addName("Validierung 2")
                    .addFragestellung("Mit welcher Annotation muss ein Objekt in der Parameterliste der Handlermethode " +
                            "annotiert werden um die Validierunge anzuschalten?")
                    .addAntwortmoeglichkeiten(List.of(
                            new AntwortMoeglichkeit("@Valid", true),
                            new AntwortMoeglichkeit("@Validate", false),
                            new AntwortMoeglichkeit("@EnableValidation", false)))
                    .addMaxPunktzahl(2)
                    .build();

            WochenTest dummyTest3 = new WochenTestBuilder()
                    .addName("Woche3")
                    .addStartTime(LocalDateTime.now().plusDays(4))
                    .addEndTime(LocalDateTime.now().plusDays(5))
                    .addStatus(STATUS.STATUS_NICHT_BESTANDEN)
                    .build();

            wochenTestService.addWochenTest(dummyTest1);
            wochenTestService.addWochenTest(dummyTest2);
            wochenTestService.addWochenTest(dummyTest3);
        };
    }

}
