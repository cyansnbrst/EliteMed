package com.cyansnbrst.EliteMed.services;

import com.cyansnbrst.EliteMed.entities.Appointment;
import com.cyansnbrst.EliteMed.entities.Patient;
import com.cyansnbrst.EliteMed.repositories.AppointmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {

    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        appointmentService = new AppointmentService(appointmentRepositoryMock);
    }

    @Test
    public void testAddAppointment() {
        Appointment appointment = new Appointment();
        appointmentService.addAppointment(appointment);
        verify(appointmentRepositoryMock, times(1)).save(appointment);
    }

    @Test
    public void testDeleteAppointment() {
        Integer id = 1;
        when(appointmentRepositoryMock.existsById(id)).thenReturn(true);
        appointmentService.deleteAppointment(id);
        verify(appointmentRepositoryMock, times(1)).deleteById(id);
    }

    @Test
    public void testGetAllPatientAppointments() {
        Patient patient = new Patient();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        appointments.add(new Appointment());
        when(appointmentRepositoryMock.findAppointmentsByPatient(patient)).thenReturn(appointments);
        List<Appointment> result = appointmentService.getAllPatientAppointments(patient);
        assertEquals(appointments, result);
    }

    @Test
    public void testGetAppointmentById() {
        Integer id = 1;
        Appointment appointment = new Appointment();
        when(appointmentRepositoryMock.findAppointmentById(id)).thenReturn(appointment);
        Appointment result = appointmentService.getAppointmentById(id);
        assertEquals(appointment, result);
    }
}