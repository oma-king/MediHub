package com.kingoma.controllers;

import com.kingoma.dtos.NurseFormData;
import com.kingoma.entities.Nurse;
import com.kingoma.repositories.NurseRepository;
import com.kingoma.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Optional;

@Controller
public class NurseController {
    private final NurseRepository nurseRepository;
    private final UserRepository userRepository;



    public NurseController(NurseRepository nurseRepository, UserRepository userRepository) {
        this.nurseRepository = nurseRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/nurses")
    public String showIndexPage(Model model) {
        // Get all patients directly from the repository
        model.addAttribute("nurses", nurseRepository.findAll());
        return "User/Nurse/nurseIndex";
    }
    @GetMapping("/getNurseById/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable Long id) {

            Optional<Nurse> optionalNurse = nurseRepository.findById(id);
            if (optionalNurse.isPresent()) {
                Nurse Nurse = optionalNurse.get();
                return new ResponseEntity<>(Nurse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

    }
    @PostMapping("/addNurse")
    @ResponseBody
    public String addNurse(@RequestBody NurseFormData formData) {
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
        String Username =  String.format("NU%s%d%d%d%s", lastNameInitials, currentYear, currentMonth, currentDay,idCardLastThree);
        Username = Username .toUpperCase();
        // Create a new Nurse instance
        Nurse Nurse = new Nurse(
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
                formData.getShift()
        );
        // Save the Patient entity
        nurseRepository.save(Nurse);

        return "Nurse added successfully!";
    }
    @PutMapping("/updateNurse/{id}")
    @ResponseBody
    public String updateNurse(@PathVariable("id") Long id, @RequestBody NurseFormData formData) {
        // Retrieve the existing Nurse from the database
        Optional<Nurse> optionalNurse = nurseRepository.findById(id);
        if (optionalNurse.isPresent()) {
            Nurse Nurse = optionalNurse.get();
            // Update the Nurse fields with the new data
            Nurse.setIdCard(formData.getIdCard());
            Nurse.setGender(formData.getGender());
            Nurse.setLastName(formData.getLastName());
            Nurse.setFirstName(formData.getFirstName());
            Nurse.setPhone1(formData.getPhone1());
            Nurse.setPhone2(formData.getPhone2());
            Nurse.setEmail(formData.getEmail());
            Nurse.setPassword(formData.getPassword());
            Nurse.setAccountType(formData.getAccountType());
            Nurse.setShift(formData.getShift());

            // Save the updated patient entity
            nurseRepository.save(Nurse);

            return "Nurse updated successfully!";
        } else {
            return "Nurse not found!";
        }
    }
    @DeleteMapping("/deleteNurse/{id}")
    @ResponseBody
    public String deleteNurse(@PathVariable Long id) {
        // Check if the Nurse exists
        Optional<Nurse> optionalNurse = nurseRepository.findById(id);
        if (optionalNurse.isPresent()) {
            // Nurse exists, delete it
            Nurse Nurse = optionalNurse.get();

            // Delete the Nurse record
            nurseRepository.delete(Nurse);

            // Delete the corresponding user account
            userRepository.deleteById(Nurse.getId());

            return "Nurse and corresponding user account deleted successfully!";
        } else {
            // Nurse does not exist
            return "Nurse not found!";
        }
    }

}
