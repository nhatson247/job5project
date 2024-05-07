package com.fpt.job5project.controller;

import com.fpt.job5project.Model.JobsIndustries;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IJobsIndustriesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/jobsIndustries")
public class JobsIndustriesController {
    @Autowired
    IJobsIndustriesService iJobsIndustries;
    @PostMapping("/create")
    public ResponseObject<String> addJobsIndustries(@ModelAttribute JobsIndustries jobsIndustries){
        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobsIndustries.addJobsIndustries(jobsIndustries);
        responseObject.setMessage("JobsIndustries has been added");
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<List<JobsIndustries>> getIndustriesByJobID(@PathVariable("id") long id){
        ResponseObject<List<JobsIndustries>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobsIndustries.getJobIndustriesByJobId(id));
        return  responseObject;
    }

    @DeleteMapping("/delete")
    public ResponseObject<String> deleteIndustryOfJob(@ModelAttribute JobsIndustries jobsIndustries) {
        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobsIndustries.deleteIndustryOfJob(jobsIndustries.getJob_jobid(), jobsIndustries.getIndustries_industryid());
        responseObject.setMessage("JobsIndustries has been deleted");
        return responseObject;
    }

    @DeleteMapping("/deleteByJobId/{id}")
    public ResponseObject<String> deleteJobRequirementByJobId(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobsIndustries.deleteIndustriesByJobId(id);
        responseObject.setMessage("list industries of job has been deleted");
        return responseObject;
    }
}
