package com.cyansnbrst.EliteMed;

import com.cyansnbrst.EliteMed.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EliteMedApplication {

    public static void main(String[] args) {
        SpringApplication.run(EliteMedApplication.class, args);
    }

}
