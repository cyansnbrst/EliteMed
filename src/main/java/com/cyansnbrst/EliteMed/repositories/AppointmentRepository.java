package com.cyansnbrst.EliteMed.repositories;

import com.cyansnbrst.EliteMed.entities.Appointment;
import com.cyansnbrst.EliteMed.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>, JpaSpecificationExecutor<Appointment> {
    List<Appointment> findAppointmentsByPatient(Patient patient);
    Appointment findAppointmentById(Integer id);
}

