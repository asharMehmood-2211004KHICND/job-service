package com.xloop.resourceloop.createJob.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xloop.resourceloop.createJob.Model.DropDownModel.Education;
import com.xloop.resourceloop.createJob.Service.DropDownService.EducationService;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = "${app.cors.origin:'http://localhost:3000'}")
public class EducationController {
    // git test comment
    @Autowired
    private EducationService educationService;

    @PostMapping("/add")
    public ResponseEntity<Education> addEducation(@RequestBody Education education){

        try{
            return ResponseEntity.ok().body(educationService.add(education));
        } catch(DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  deleteJob(@PathVariable Long id){
        try {
            educationService.deactivate(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/reactive/{id}")
    public ResponseEntity<String>  reactiveSoftSkill(@PathVariable Long id){
        try {
            educationService.reactive(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<Education> updateSoftSkill(@PathVariable Long id,
            @RequestBody Education education) {
        try {
            education.setId(id);
            return ResponseEntity.ok().body(educationService.update(education));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Education>> viewAllSoftSkills(){
        return ResponseEntity.ok().body(educationService.viewAll());
    }

    
}
