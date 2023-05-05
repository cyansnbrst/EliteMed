package com.cyansnbrst.pr15.controllers;

import com.cyansnbrst.pr15.entities.Doctor;
import com.cyansnbrst.pr15.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/list")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        if (!doctors.isEmpty()) {
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGameAuthor(@PathVariable("id") Integer id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{criteria}")
    public ResponseEntity<List<Doctor>> findDoctors(@PathVariable("criteria") String criteria) {
        List<Doctor> doctors = doctorService.findDoctors(criteria);
        if (!doctors.isEmpty()) {
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
