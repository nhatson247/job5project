package com.fpt.job5project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.JobBenefitDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IJobBenefitService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/jobbenefit")
public class JobBenefitController {
    @Autowired
    IJobBenefitService iJobBenefitService;

    @PostMapping("/create")
    public ResponseObject<JobBenefitDTO> addJobBenefit(@ModelAttribute JobBenefitDTO newJob) {

        ResponseObject<JobBenefitDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobBenefitService.addJobBenefit(newJob));
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<JobBenefitDTO> updateJobBenefit(@PathVariable("id") long id,
            @ModelAttribute JobBenefitDTO newJob) {
        ResponseObject<JobBenefitDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobBenefitService.updateJobBenefit(id, newJob));
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<List<JobBenefitDTO>> getAllJobBenefit(@PathVariable("id") long idJob) {
        ResponseObject<List<JobBenefitDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobBenefitService.getAllJobBenefit(idJob));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteJobBenefit(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobBenefitService.deleteJobBenefit(id);
        responseObject.setMessage("Job benefit has been deleted");
        return responseObject;
    }

    @DeleteMapping("/deleteByJobId/{id}")
    public ResponseObject<String> deleteJobDescriptionByJobId(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobBenefitService.deleteJobBenefitByJobId(id);
        responseObject.setMessage("Job Description has been deleted");
        return responseObject;
    }
}
