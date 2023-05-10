package com.cyansnbrst.EliteMed.services;

import com.cyansnbrst.EliteMed.entities.Patient;
import com.cyansnbrst.EliteMed.entities.User;
import com.cyansnbrst.EliteMed.repositories.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPatient() {
        Patient patient = new Patient();
        patientService.addPatient(patient);
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testDeletePatient() {
        int id = 1;
        when(patientRepository.existsById(id)).thenReturn(true);
        patientService.deletePatient(id);
        verify(patientRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindPatientByUserId() {
        User user = new User();
        user.setId(1);
        Patient patient = new Patient();
        patient.setUserId(user);
        when(patientRepository.findByUserId(user)).thenReturn(patient);
        Patient result = patientService.findPatientByUserId(user);
        Assertions.assertEquals(patient, result);
    }

}