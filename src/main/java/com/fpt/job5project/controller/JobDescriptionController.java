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

import com.fpt.job5project.dto.JobDescriptionDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IJobDescriptionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/jobdecription")
public class JobDescriptionController {
    @Autowired
    IJobDescriptionService iJobDescriptionService;

    @PostMapping("/create")
    public ResponseObject<JobDescriptionDTO> addJobDescription(@ModelAttribute JobDescriptionDTO newJob) {

        ResponseObject<JobDescriptionDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobDescriptionService.addJobDescription(newJob));
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<JobDescriptionDTO> updateJobDescription(@PathVariable("id") long id,
            @ModelAttribute JobDescriptionDTO newJob) {
        ResponseObject<JobDescriptionDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobDescriptionService.updateJobDescription(id, newJob));
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<List<JobDescriptionDTO>> getAllJobDescription(@PathVariable("id") long idJob) {
        ResponseObject<List<JobDescriptionDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobDescriptionService.getAllJobDescription(idJob));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteJobDescription(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobDescriptionService.deleteJobDescription(id);
        responseObject.setMessage("Job Description has been deleted");
        return responseObject;
    }

    @DeleteMapping("/deleteByJobId/{id}")
    public ResponseObject<String> deleteJobDescriptionByJobId(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobDescriptionService.deleteJobDescriptionByJobId(id);
        responseObject.setMessage("Job Description has been deleted");
        return responseObject;
    }
}
