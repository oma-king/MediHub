package com.kingoma.controllers;

import com.kingoma.dtos.DoctorFormData;
import com.kingoma.entities.Doctor;
import com.kingoma.repositories.DoctorRepository;
import com.kingoma.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Optional;

@Controller
public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;



    public DoctorController(DoctorRepository doctorRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/doctors")
    public String showIndexPage(Model model) {
        // Get all patients directly from the repository
        model.addAttribute("doctors", doctorRepository.findAll());
        return "User/Doctor/doctorIndex";
    }
    @GetMapping("/getDoctorById/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {

            Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
            if (optionalDoctor.isPresent()) {
                Doctor doctor = optionalDoctor.get();
                return new ResponseEntity<>(doctor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

    }
    @PostMapping("/addDoctor")
    @ResponseBody
    public String addDoctor(@RequestBody DoctorFormData formData) {
        // Get the current year and month
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Month starts from 0, so add 1
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

// Extract the first two letters of the last name
        String lastNameInitials = formData.getLastName().substring(0, Math.min(formData.getLastName().length(), 2));

// Extract the last three characters of the ID card
        String idCardLastThree = formData.getIdCard().substring(Math.max(0, formData.getIdCard().length() - 3));

// Construct the content for the Username
        String Username =  String.format("DU%s%d%d%d%s", lastNameInitials, currentYear, currentMonth, currentDay,idCardLastThree);
        Username = Username .toUpperCase();
        // Create a new Doctor instance
        Doctor doctor = new Doctor(
                formData.getIdCard(),
                formData.getGender(),
                formData.getLastName(),
                formData.getFirstName(),
                formData.getPhone1(),
                formData.getPhone2(),
                formData.getEmail(),
                Username,
                formData.getPassword(),
                formData.getAccountType(),
                formData.getSpeciality()
        );
        // Save the Patient entity
        doctorRepository.save(doctor);

        return "Doctor added successfully!";
    }
    @PutMapping("/updateDoctor/{id}")
    @ResponseBody
    public String updateDoctor(@PathVariable("id") Long id, @RequestBody DoctorFormData formData) {
        // Retrieve the existing Doctor from the database
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            // Update the doctor fields with the new data
            doctor.setIdCard(formData.getIdCard());
            doctor.setGender(formData.getGender());
            doctor.setLastName(formData.getLastName());
            doctor.setFirstName(formData.getFirstName());
            doctor.setPhone1(formData.getPhone1());
            doctor.setPhone2(formData.getPhone2());
            doctor.setEmail(formData.getEmail());
            doctor.setPassword(formData.getPassword());
            doctor.setAccountType(formData.getAccountType());
            doctor.setSpeciality(formData.getSpeciality());

            // Save the updated patient entity
            doctorRepository.save(doctor);

            return "Doctor updated successfully!";
        } else {
            return "Doctor not found!";
        }
    }
    @DeleteMapping("/deleteDoctor/{id}")
    @ResponseBody
    public String deleteDoctor(@PathVariable Long id) {
        // Check if the Doctor exists
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            // Doctor exists, delete it
            Doctor doctor = optionalDoctor.get();

            // Delete the Doctor record
            doctorRepository.delete(doctor);

            // Delete the corresponding user account
            userRepository.deleteById(doctor.getId());

            return "Doctor and corresponding user account deleted successfully!";
        } else {
            // Doctor does not exist
            return "Doctor not found!";
        }
    }

}
