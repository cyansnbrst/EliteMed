package com.cyansnbrst.EliteMed.services;

import com.cyansnbrst.EliteMed.entities.Doctor;
import com.cyansnbrst.EliteMed.repositories.DoctorRepository;
import com.cyansnbrst.EliteMed.specifications.DoctorSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final EmailService emailService;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, EmailService emailService) {
        this.doctorRepository = doctorRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        log.info("Doctor {} was added", doctor);
        emailService.sendMessage("litra1122@gmail.com", "Doctor was added to the database", "id=" + doctor.getId());
    }

    @Transactional
    public void deleteDoctor(Integer id) {
        boolean exists = doctorRepository.existsById(id);
        if (exists) {
            log.info("Doctor {} was deleted", doctorRepository.findById(id));
            doctorRepository.deleteById(id);
            emailService.sendMessage("litra1122@gmail.com", "Doctor was deleted from the database", "id=" + doctorRepository.findById(id));
        }
    }

    @Transactional
    public List<Doctor> getAllDoctors() {
        log.info("The list of doctors was displayed");
        return doctorRepository.findAll();
    }

    @Transactional
    public boolean existsDoctor(String name) {
        return doctorRepository.existsByName(name);
    }

    @Transactional
    public List<Doctor> findDoctors(String criteria) {
        log.info("Filtering by the line {} was performed", criteria);
        return doctorRepository.findAll(new DoctorSpecification(criteria));
    }
}
