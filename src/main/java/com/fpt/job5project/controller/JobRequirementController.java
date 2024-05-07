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

import com.fpt.job5project.dto.JobRequirementDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IJobRequirementService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/jobrequirement")
public class JobRequirementController {
    @Autowired
    IJobRequirementService iJobRequirementService;

    @PostMapping("/create")
    public ResponseObject<JobRequirementDTO> addJobRequirement(@ModelAttribute JobRequirementDTO newJob) {
        ResponseObject<JobRequirementDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobRequirementService.addJobRequirement(newJob));
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<JobRequirementDTO> updateJobRequirement(@PathVariable("id") long id,
            @ModelAttribute JobRequirementDTO newJob) {
        ResponseObject<JobRequirementDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobRequirementService.updateJobRequirement(id, newJob));
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<List<JobRequirementDTO>> getAllJobRequirement(@PathVariable("id") long idJob) {
        ResponseObject<List<JobRequirementDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobRequirementService.getAllJobRequirement(idJob));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteJobRequirement(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobRequirementService.deleteJobRequirement(id);
        responseObject.setMessage("Job Requirement has been deleted");
        return responseObject;
    }

    @DeleteMapping("/deleteByJobId/{id}")
    public ResponseObject<String> deleteJobRequirementByJobId(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobRequirementService.deleteJobRequirementByJobId(id);
        responseObject.setMessage("Job Requirement has been deleted");
        return responseObject;
    }
}
