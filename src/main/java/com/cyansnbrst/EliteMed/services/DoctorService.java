package com.cyansnbrst.EliteMed.services;

import com.cyansnbrst.EliteMed.entities.Doctor;
import com.cyansnbrst.EliteMed.repositories.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Transactional
    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Transactional
    public void deleteDoctor(Integer id) {
        boolean exists = doctorRepository.existsById(id);
        if (exists) {
            doctorRepository.deleteById(id);
        }
    }

    @Transactional
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
