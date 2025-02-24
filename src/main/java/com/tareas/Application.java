package com.tareas;

import com.tareas.entities.Tarea;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//para ejecutar: http://localhost:9090/tareas
	}

}