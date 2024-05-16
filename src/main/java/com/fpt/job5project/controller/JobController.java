package com.fpt.job5project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.JobDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.dto.SearchJobDTO;
import com.fpt.job5project.service.IJobService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/job")
public class JobController {
    @Autowired
    IJobService iJobService;

    @PostMapping("/create")
    public ResponseObject<JobDTO> addJob(@ModelAttribute JobDTO newJob) {

        if (newJob.getJobName() == null || newJob.getJobName().isEmpty()) {
            throw new IllegalArgumentException("jobName không được null hoặc rỗng");
        }
        // System.out.println("alo" + newJob.getJobName());
        ResponseObject<JobDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.addJob(newJob));
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<JobDTO> updateJob(@PathVariable("id") long id, @ModelAttribute JobDTO newJob) {
        ResponseObject<JobDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.updateJob(id, newJob));
        return responseObject;
    }

    @GetMapping()
    public ResponseObject<List<JobDTO>> getAllJob() {
        ResponseObject<List<JobDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.getAllJob());
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<JobDTO> getOneJob(@PathVariable("id") long id) {
        ResponseObject<JobDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.getOneJob(id));
        return responseObject;
    }

    @GetMapping("/getTopJobForHome/{numJobs}")
    public ResponseObject<List<JobDTO>> getTopJobForHome(@PathVariable("numJobs") int numJobs) {
        ResponseObject<List<JobDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.getTopJobForHome(numJobs));
        return responseObject;
    }

    @GetMapping("/getByEmployer/{id}")
    public ResponseObject<List<JobDTO>> getByEmployerId(@PathVariable("id") long id) {
        ResponseObject<List<JobDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.getJobByEmployer(id));
        return responseObject;
    }

    @PostMapping("/searchJob")
    public ResponseObject<List<JobDTO>> resultSearchJob(@ModelAttribute SearchJobDTO searchJobDTO) {
        ResponseObject<List<JobDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.resultSearchJob(searchJobDTO.getIndustryId(), searchJobDTO.getSearchValue(),
                searchJobDTO.getMinSalary(), searchJobDTO.getMaxSalary(), searchJobDTO.getLocation()));
        return responseObject;
    }
}
