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

import com.xloop.resourceloop.createJob.Model.DropDownModel.Benefits;
import com.xloop.resourceloop.createJob.Service.DropDownService.BenefitsService;

@RestController
@RequestMapping("/benefits")
@CrossOrigin(origins = "${app.cors.origin:'http://localhost:3000'}")
public class BenefitsController {
 
 		//my new test comment
 
    @Autowired
    private BenefitsService benefitsService;

    @PostMapping("/add")
    public ResponseEntity<Benefits> addTechnicalSkill(@RequestBody Benefits benefits) {
            try {
            return ResponseEntity.ok().body(benefitsService.add(benefits));
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        try {
            benefitsService.deactivate(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/reactive/{id}")
    public ResponseEntity<String> reactiveBenefits(@PathVariable Long id) {
        try {
            benefitsService.reactive(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Benefits> updateBenefitPerk(@PathVariable Long id,
            @RequestBody Benefits benefits) {
        try {
            benefits.setId(id);
            return ResponseEntity.ok().body(benefitsService.update(benefits));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Benefits>> viewAllTechnicalSkills() {
        return ResponseEntity.ok().body(benefitsService.viewAll());
    }

}
