package com.cyansnbrst.EliteMed.services;

import com.cyansnbrst.EliteMed.entities.Patient;
import com.cyansnbrst.EliteMed.entities.User;
import com.cyansnbrst.EliteMed.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    @Transactional
    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Transactional
    public void deletePatient(Integer id) {
        boolean exists = patientRepository.existsById(id);
        if (exists) {
            patientRepository.deleteById(id);
        }
    }

    @Transactional
    public Patient findPatientByUserId(User id) {
        return patientRepository.findByUserId(id);
    }
}
