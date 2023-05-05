package com.cyansnbrst.pr15.services;

import com.cyansnbrst.pr15.entities.Appointment;
import com.cyansnbrst.pr15.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EmailService emailService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        log.info("Appointment {} was added", appointment);
        emailService.sendMessage("litra1122@gmail.com", "Appointment was added to the database", "id=" + appointment.getId());
    }

    @Transactional
    public void deleteAppointment(Integer id) {
        boolean exists = appointmentRepository.existsById(id);
        if (exists) {
            log.info("Doctor {} was deleted", appointmentRepository.findById(id));
            appointmentRepository.deleteById(id);
            emailService.sendMessage("litra1122@gmail.com", "Appointment was deleted from the database", "id=" + appointmentRepository.findById(id));
        }
    }

    @Transactional
    public List<Appointment> getAllAppointments() {
        log.info("The list of appointments was displayed");
        return appointmentRepository.findAll();
    }

}

