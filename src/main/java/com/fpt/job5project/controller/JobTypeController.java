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

import com.fpt.job5project.dto.JobTypeDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IJobTypeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/jobType")
public class JobTypeController {
    @Autowired
    IJobTypeService iJobTypeService;

    @PostMapping("/create")
    public ResponseObject<JobTypeDTO> addJobType(@ModelAttribute JobTypeDTO newJob) {
        ResponseObject<JobTypeDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobTypeService.addJobType(newJob));
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<JobTypeDTO> updateJobType(@PathVariable("id") int id, @ModelAttribute JobTypeDTO newJob) {
        ResponseObject<JobTypeDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobTypeService.updateJobType(id, newJob));
        return responseObject;
    }

    @GetMapping()
    public ResponseObject<List<JobTypeDTO>> getAllJobType() {
        ResponseObject<List<JobTypeDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobTypeService.getAllJobType());
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteJobType(@PathVariable("id") int id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobTypeService.deleteJobType(id);
        responseObject.setMessage("Job Type has been deleted");
        return responseObject;
    }

}
