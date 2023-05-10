package com.cyansnbrst.EliteMed.controllers;

import com.cyansnbrst.EliteMed.entities.Appointment;
import com.cyansnbrst.EliteMed.entities.Patient;
import com.cyansnbrst.EliteMed.entities.User;
import com.cyansnbrst.EliteMed.services.AppointmentService;
import com.cyansnbrst.EliteMed.services.DoctorService;
import com.cyansnbrst.EliteMed.services.PatientService;
import com.cyansnbrst.EliteMed.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping("/home")
    public String showHomepage(Model model, Principal principal) {
        return "index";
    }

    @GetMapping("/account")
    public String showAccount(Model model, Principal principal) {
        model.addAttribute("logged_in_user", principal.getName());
        model.addAttribute("patient", new Patient());
        model.addAttribute("appointment", new Appointment());
        List<String> availableTimes = Arrays.asList("9:00", "11:00", "13:00", "15:00", "17:00");
        model.addAttribute("availableTimes", availableTimes);
        model.addAttribute("doctors_list", doctorService.getAllDoctors());
        User current_user = userService.getUserByPrincipal(principal);
        Patient current_patient = patientService.findPatientByUserId(current_user);
        if (current_patient == null) {
            model.addAttribute("patient_doesnt_exist", "Введите свое ФИО для возможности записи к врачу");
        } else {
            model.addAttribute("patient_exists", current_patient.getName());
        }
        return "account";
    }

    @PostMapping("/account.patient")
    public String enterFIO(@ModelAttribute("patient") Patient patient, Principal principal) {
        patient.setUserId(userService.getUserByPrincipal(principal));
        patientService.addPatient(patient);
        return "redirect:/account";
    }

    @PostMapping("/account.appointment")
    public String createAppointment(@ModelAttribute("appointment") Appointment appointment, Principal principal, Model model) {
        User current_user = userService.getUserByPrincipal(principal);
        Patient current_patient = patientService.findPatientByUserId(current_user);
        if (current_patient != null) {
            appointment.setPatient(patientService.findPatientByUserId(userService.getUserByPrincipal(principal)));
            appointmentService.addAppointment(appointment);
            return "redirect:/appointment_success";
        }
        return "redirect:/account";
    }

    @GetMapping("/appointment_success")
    public String successfulAppointment() {
        return "appointment_success";
    }

    @GetMapping("/appointment_list")
    public String showAppointments(Principal principal, Model model) {
        List<Appointment> patientAppointments = appointmentService.getAllPatientAppointments(patientService.findPatientByUserId(userService.getUserByPrincipal(principal)));
        model.addAttribute("appointment_list", patientAppointments);
        return "appointment_list";
    }

    @PostMapping("/appointment_list")
    public String deleteAppointment(@RequestParam Integer appointment_id, Principal principal, Model model) {
        User current_user = userService.getUserByPrincipal(principal);
        if (appointmentService.getAppointmentById(appointment_id).getPatient().getUserId() == current_user) {
            appointmentService.deleteAppointment(appointment_id);
            return "redirect:/appointment_list";
        }
        else {
            model.addAttribute("error", "incorrect_id");
            return "redirect:/appointment_list";
        }
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
