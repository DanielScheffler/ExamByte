package com.example.exambyte;

import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.builder.TestBuilder;
import com.example.exambyte.data.FRAGETYP;
import com.example.exambyte.data.Frage;
import com.example.exambyte.data.STATUS;
import com.example.exambyte.data.WochenTest;
import com.example.exambyte.service.WochenTestService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ExamByteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamByteApplication.class, args);
	}

	// Adds a few example WochenTest objects

	@Bean
	@Order(1)
	ApplicationRunner addExampleWochentests(WochenTestService wochenTestService) {
		return args -> {

			Frage frage1woche1 = new FrageBuilder()
					.addFragetyp(FRAGETYP.FRAGETYP_FREITEXT)
					.addName("Routing")
					.addFragestellung("Was versteht man unter Routing im Kontext von SpringWebMVC?")
					.addMaxPunktzahl(2)
					.build();

			Frage frage2woche1 = new FrageBuilder()
					.addFragetyp(FRAGETYP.FRAGETYP_MULTIPLE_CHOICE)
					.addName("HTTP-Verben")
					.addFragestellung("Welches dieser HTTP Verben ist safe?")
					.addAntwortmöglichkeiten(List.of("GET", "POST", "PUT", "DELETE"))
					.addMaxPunktzahl(2)
					.build();

			WochenTest dummyTest1 = new TestBuilder()
					.addName("Woche1")
					.addStartTime(LocalDateTime.now())
					.addEndTime(LocalDateTime.now().plusDays(1))
					.addStatus(STATUS.STATUS_AUSSTEHEND)
					.addFrage(frage1woche1)
					.addFrage(frage2woche1)
					.build();

			Frage frage1woche2 = new FrageBuilder()
					.addFragetyp(FRAGETYP.FRAGETYP_MULTIPLE_CHOICE)
					.addName("Validierung")
					.addFragestellung("Mit welchem Objekt prüft man auf aufgetretene Validierungsfehler?")
					.addAntwortmöglichkeiten(List.of("RedirectAttributes", "Model", "BindingResult"))
					.addMaxPunktzahl(2)
					.build();

			WochenTest dummyTest2 = new TestBuilder()
					.addName("Woche2")
					.addStartTime(LocalDateTime.now().plusDays(2))
					.addEndTime(LocalDateTime.now().plusDays(3))
					.addStatus(STATUS.STATUS_BEARBEITBAR)
					.addFrage(frage1woche2)
					.build();

			Frage frage1woche3 = new FrageBuilder()
					.addFragetyp(FRAGETYP.FRAGETYP_MULTIPLE_CHOICE)
					.addName("Validierung 2")
					.addFragestellung("Mit welcher Annotation muss ein Objekt in der Parameterliste der Handlermethode " +
							"annotiert werden um die Validierunge anzuschalten?")
					.addAntwortmöglichkeiten(List.of("@Valid", "@Validate", "@EnableValidation"))
					.addMaxPunktzahl(2)
					.build();

			WochenTest dummyTest3 = new TestBuilder()
					.addName("Woche3")
					.addStartTime(LocalDateTime.now().plusDays(4))
					.addEndTime(LocalDateTime.now().plusDays(5))
					.addFrage(frage1woche3)
					.addStatus(STATUS.STATUS_NICHT_BESTANDEN)
					.build();

			wochenTestService.addWochenTest(dummyTest1);
			wochenTestService.addWochenTest(dummyTest2);
			wochenTestService.addWochenTest(dummyTest3);
		};
	}

}
