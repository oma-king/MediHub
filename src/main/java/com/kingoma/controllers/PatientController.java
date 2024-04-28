package com.kingoma.controllers;

import com.kingoma.dtos.PatientFormData;
import com.kingoma.entities.MedicalFile;
import com.kingoma.entities.Patient;
import com.kingoma.repositories.MedicalFileRepository;
import com.kingoma.repositories.PatientRepository;
import com.kingoma.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

@Controller
public class PatientController {
    private final PatientRepository patientRepository;
    private final MedicalFileRepository medicalFileRepository;
    private final UserRepository userRepository;



    public PatientController(PatientRepository patientRepository,MedicalFileRepository medicalFileRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.medicalFileRepository = medicalFileRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {
        // Get all patients directly from the repository
        model.addAttribute("patients", patientRepository.findAll());
        return "index";
    }
    @GetMapping("/getPatientById/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {

            Optional<Patient> optionalPatient = patientRepository.findById(id);
            if (optionalPatient.isPresent()) {
                Patient patient = optionalPatient.get();
                return new ResponseEntity<>(patient, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

    }
    @PostMapping("/addPatient")
    @ResponseBody
    public String addPatient(@RequestBody PatientFormData formData) {
        // Get the current year and month
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Month starts from 0, so add 1
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

// Extract the first two letters of the last name and first name
        String lastNameInitials = formData.getLastName().substring(0, Math.min(formData.getLastName().length(), 2));
        String firstNameInitials = formData.getFirstName().substring(0, Math.min(formData.getFirstName().length(), 2));

// Extract the last three characters of the ID card
        String idCardLastThree = formData.getIdCard().substring(Math.max(0, formData.getIdCard().length() - 3));

// Construct the content for the MedicalFile
        String medicalFileId  = String.format("D%s%s/%d%d%d/%s", firstNameInitials, lastNameInitials, currentYear, currentMonth, currentDay, idCardLastThree);
        medicalFileId = medicalFileId .toUpperCase();

// Create a new MedicalFile instance with the constructed content
        MedicalFile medicalFile = new MedicalFile();
        medicalFile.setMedicalFileId(medicalFileId);

// Construct the content for the Username
        String Username =  String.format("U%s%d%d%d%s", lastNameInitials, currentYear, currentMonth, currentDay,idCardLastThree);
        Username = Username .toUpperCase();
        // Create a new Patient instance
        Patient patient = new Patient(
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
                formData.getBirthDate(),
                formData.getAddress(),
                formData.getCity(),
                formData.getSocialSecurityType(),
                medicalFile
        );
        // Set the patient for the medical file
        medicalFile.setPatient(patient);

        // Save the Patient entity
        patientRepository.save(patient);

        return "Patient added successfully!";
    }
    @PutMapping("/updatePatient/{id}")
    @ResponseBody
    public String updatePatient(@PathVariable("id") Long id, @RequestBody PatientFormData formData) {
        // Retrieve the existing patient from the database
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            // Update the patient fields with the new data
            patient.setIdCard(formData.getIdCard());
            patient.setGender(formData.getGender());
            patient.setLastName(formData.getLastName());
            patient.setFirstName(formData.getFirstName());
            patient.setPhone1(formData.getPhone1());
            patient.setPhone2(formData.getPhone2());
            patient.setEmail(formData.getEmail());
            patient.setPassword(formData.getPassword());
            patient.setAccountType(formData.getAccountType());
            patient.setBirthDate(formData.getBirthDate());
            patient.setAddress(formData.getAddress());
            patient.setCity(formData.getCity());
            patient.setSocialSecurityType(formData.getSocialSecurityType());

            // Save the updated patient entity
            patientRepository.save(patient);

            return "Patient updated successfully!";
        } else {
            return "Patient not found!";
        }
    }
    @DeleteMapping("/deletePatient/{id}")
    @ResponseBody
    public String deletePatient(@PathVariable Long id) {
        // Check if the patient exists
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            // Patient exists, delete it
            Patient patient = optionalPatient.get();

            // Delete the patient record
            patientRepository.delete(patient);

            // Delete the corresponding user account
            userRepository.deleteById(patient.getId());

            return "Patient and corresponding user account deleted successfully!";
        } else {
            // Patient does not exist
            return "Patient not found!";
        }
    }

}
