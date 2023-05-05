package com.cyansnbrst.pr15.services;

import com.cyansnbrst.pr15.entities.Patient;
import com.cyansnbrst.pr15.entities.User;
import com.cyansnbrst.pr15.repositories.PatientRepository;
import com.cyansnbrst.pr15.specifications.PatientSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatientService {
    private final PatientRepository patientRepository;
    private final EmailService emailService;

    @Autowired
    public PatientService(PatientRepository patientRepository, EmailService emailService) {
        this.patientRepository = patientRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void addPatient(Patient patient) {
        patientRepository.save(patient);
        log.info("Patient {} was added", patient);
        emailService.sendMessage("litra1122@gmail.com", "Patient was added to the database", "id=" + patient.getId());
    }

    @Transactional
    public void deletePatient(Integer id) {
        boolean exists = patientRepository.existsById(id);
        if (exists) {
            log.info("Patient {} was deleted", patientRepository.findById(id));
            patientRepository.deleteById(id);
            emailService.sendMessage("litra1122@gmail.com", "Patient was deleted from the database", "id=" + patientRepository.findById(id));
        }
    }

    @Transactional
    public List<Patient> getAllPatients() {
        log.info("The list of patients was displayed");
        return patientRepository.findAll();
    }

    @Transactional
    public List<Patient> findPatients(String criteria) {
        log.info("Filtering by the line {} was performed", criteria);
        return patientRepository.findAll(new PatientSpecification(criteria));
    }

    @Transactional
    public Patient findPatientByUserId(User id) {
        return patientRepository.findByUserId(id);
    }
}
