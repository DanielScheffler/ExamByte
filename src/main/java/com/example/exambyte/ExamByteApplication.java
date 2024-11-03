package com.example.exambyte;

import com.example.exambyte.builder.TestBuilder;
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

	@Bean
	@Order(1)
	ApplicationRunner addExampleWochentests(WochenTestService wochenTestService) {
		return args -> {
			WochenTest dummyTest1 = new TestBuilder()
					.addName("Woche1")
					.addStartTime(LocalDateTime.now())
					.addEndTime(LocalDateTime.now().plusDays(1))
					.addStatus(STATUS.STATUS_AUSSTEHEND)
					.build();

			WochenTest dummyTest2 = new TestBuilder()
					.addName("Woche2")
					.addStartTime(LocalDateTime.now().plusDays(2))
					.addEndTime(LocalDateTime.now().plusDays(3))
					.addStatus(STATUS.STATUS_BEARBEITBAR)
					.build();

			WochenTest dummyTest3 = new TestBuilder()
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
