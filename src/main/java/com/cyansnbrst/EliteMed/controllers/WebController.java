package com.cyansnbrst.EliteMed.controllers;

import com.cyansnbrst.EliteMed.entities.Patient;
import com.cyansnbrst.EliteMed.entities.Role;
import com.cyansnbrst.EliteMed.entities.User;
import com.cyansnbrst.EliteMed.services.PatientService;
import com.cyansnbrst.EliteMed.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;
    private final PatientService patientService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistrationForm(@ModelAttribute("user") User user, Model model) {
        User userFromDb = userService.getUserByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "Пользователь уже существует");
            return "/registration";
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Collections.singleton(Role.USER));
        userService.addUser(user);
        return loginSubmit(user.getUsername(), user.getPassword(), model);
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/home")
    public String showHomepage(Model model, Principal principal) {
        model.addAttribute("logged_in_user", "неавторизованный пользователь");
        if (principal != null) {
            model.addAttribute("logged_in_user", principal.getName());
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException {
        request.logout();
        return "redirect:/login";
    }

    @GetMapping("/account")
    public String showAccount(Model model, Principal principal) {
        model.addAttribute("logged_in_user", principal.getName());
        model.addAttribute("patient", new Patient());
        User current_user = userService.getUserByPrincipal(principal);
        Patient current_patient = patientService.findPatientByUserId(current_user);
        if (current_patient == null) {
            model.addAttribute("patient_doesnt_exist", "Введите свое ФИО для возможности записи к врачу");
        }
        else
            model.addAttribute("patient_exists", current_patient.getName());
        return "account";
    }

    @PostMapping("/account")
    public String enterFIO(@ModelAttribute("patient") Patient patient, Principal principal) {
        patient.setUserId(userService.getUserByPrincipal(principal));
        patientService.addPatient(patient);
        return "redirect:/account";
    }

}
