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

import com.fpt.job5project.dto.JobDTO;
import com.fpt.job5project.dto.JobHomeDTO;
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
    public ResponseObject<List<JobHomeDTO>> getTopJobForHome(@PathVariable("numJobs") int numJobs) {
        ResponseObject<List<JobHomeDTO>> responseObject = new ResponseObject<>();
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
        if (searchJobDTO.getSearchValue().equals("''")) {
            searchJobDTO.setSearchValue(null);
        }
        if (searchJobDTO.getLocation().equals("''")) {
            searchJobDTO.setLocation(null);
        }
        System.out.println(searchJobDTO.getLocation());
        responseObject.setData(iJobService.resultSearchJob(searchJobDTO.getIndustryId(), searchJobDTO.getSearchValue(),
                searchJobDTO.getMinSalary(), searchJobDTO.getMaxSalary(), searchJobDTO.getLocation(),
                searchJobDTO.getExperience(), searchJobDTO.getTypeJob(), searchJobDTO.getSkip(),
                searchJobDTO.getLimit()));
        return responseObject;
    }

    @PutMapping("/updateIsExpired/{id}")
    public ResponseObject<Integer> updateIsExpired(@PathVariable("id") long id) {
        ResponseObject<Integer> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.updateIsExpired(id));
        return responseObject;
    }

    @PutMapping("/updateReup/{id}")
    public ResponseObject<Integer> updateReup(@PathVariable("id") long id) {
        System.out.println(id);
        ResponseObject<Integer> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.updateReup(id));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<Integer> deleteJob(@PathVariable("id") long id) {
        ResponseObject<Integer> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.deleteJob(id));
        return responseObject;
    }

    @PutMapping("/hide/{jobId}/report/{reportId}/employer/{employerId}")
    public ResponseObject<String> hideJob(@PathVariable long jobId, @PathVariable long reportId,
            @PathVariable long employerId) {
        iJobService.hideJob(jobId, reportId, employerId);
        return ResponseObject.<String>builder()
                .data("hide job successfully")
                .build();
    }

    @GetMapping("/numJobOfEmployer/{id}")
    public ResponseObject<Integer> NumJobOfEmployer(@PathVariable("id") long id) {
        ResponseObject<Integer> responseObject = new ResponseObject<>();
        responseObject.setData(iJobService.numJobOfEmployer(id));
        return responseObject;
    }
}
