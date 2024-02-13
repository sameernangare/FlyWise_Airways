package com.example.airline_reservation;

import io.swagger.models.Model;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AirlineReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineReservationApplication.class, args);
    }

    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD)
                .setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }

}
