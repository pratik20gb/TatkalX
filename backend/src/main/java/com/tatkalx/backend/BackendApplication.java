package com.tatkalx.backend;

import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.repository.TrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
//	@Bean
//	CommandLineRunner testTrainRepository(TrainRepository trainRepository) {
//		return args->{
//			Train train = new Train(
//					"12301",
//					"Rajdhani Express",
//					"Howrah",
//					"New Delhi"
//			);
//			Train savedTrain =trainRepository.save(train);
//			System.out.println("Train saved successfully");
//			System.out.println("Generated Train id: "+savedTrain.getId());
//			System.out.println("Train Number: " + savedTrain.getTrainNumber());
//		};
//	}

}
