package com.cyansnbrst.EliteMed.services;

import com.cyansnbrst.EliteMed.entities.Doctor;
import com.cyansnbrst.EliteMed.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@SpringBootTest
public class DoctorServiceTest {

    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepositoryMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorService = new DoctorService(doctorRepositoryMock);
    }

    @Test
    public void testAddDoctor() {
        Doctor doctor = new Doctor();
        doctorService.addDoctor(doctor);
        verify(doctorRepositoryMock, times(1)).save(doctor);
    }

    @Test
    public void testDeleteDoctor() {
        Integer id = 1;
        when(doctorRepositoryMock.existsById(id)).thenReturn(true);
        doctorService.deleteDoctor(id);
        verify(doctorRepositoryMock, times(1)).deleteById(id);
    }

    @Test
    public void testGetAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor());
        doctors.add(new Doctor());
        when(doctorRepositoryMock.findAll()).thenReturn(doctors);
        List<Doctor> result = doctorService.getAllDoctors();
        assertEquals(doctors, result);
    }
}