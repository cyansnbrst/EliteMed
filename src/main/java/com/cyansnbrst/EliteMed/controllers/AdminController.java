package com.cyansnbrst.EliteMed.controllers;

import com.cyansnbrst.EliteMed.entities.*;
import com.cyansnbrst.EliteMed.services.AppointmentService;
import com.cyansnbrst.EliteMed.services.DoctorService;
import com.cyansnbrst.EliteMed.services.PatientService;
import com.cyansnbrst.EliteMed.services.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping()
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/doctors")
    public String showDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors_list", doctors);
        return "doctors";
    }

    @PostMapping("/doctors/add")
    public String addDoctor(@RequestParam String name, @RequestParam String position) {
        Doctor new_doctor = new Doctor();
        new_doctor.setName(name);
        new_doctor.setPosition(position);
        doctorService.addDoctor(new_doctor);
        return "redirect:/admin/doctors";
    }

    @PostMapping("/doctors/delete")
    public String deleteDoctor(@RequestParam Integer doctor_id) {
        doctorService.deleteDoctor(doctor_id);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users_list", users);
        return "users";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam Integer user_id) {
        Patient current_patient = patientService.findPatientByUserId(userService.findUserById(user_id));
        List<Appointment> appointments = appointmentService.getAllPatientAppointments(current_patient);
        for (Appointment appointment:appointments) {
            appointmentService.deleteAppointment(appointment.getId());
        }
        patientService.deletePatient(current_patient.getId());
        userService.deleteUser(user_id);
        return "redirect:/admin/users";
    }
}
