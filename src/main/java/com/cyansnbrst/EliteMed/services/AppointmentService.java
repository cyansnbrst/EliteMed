package com.cyansnbrst.EliteMed.services;

import com.cyansnbrst.EliteMed.entities.Appointment;
import com.cyansnbrst.EliteMed.entities.Patient;
import com.cyansnbrst.EliteMed.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Transactional
    public void deleteAppointment(Integer id) {
        boolean exists = appointmentRepository.existsById(id);
        if (exists) {
            appointmentRepository.deleteById(id);
        }
    }

    @Transactional
    public List<Appointment> getAllPatientAppointments(Patient patient) {
        return appointmentRepository.findAppointmentsByPatient(patient);
    }

    @Transactional
    public Appointment getAppointmentById(Integer id) {
        return appointmentRepository.findAppointmentById(id);
    }
}

