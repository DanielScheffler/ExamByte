package com.example.exambyte;

import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.builder.WochenTestBuilder;
import com.example.exambyte.domain.model.*;
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

}
