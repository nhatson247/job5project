package com.fpt.job5project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.job5project.dto.ApplicationDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IApplicationService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/application")
public class ApplicationController {

    IApplicationService applicationService;

    @GetMapping("/getByCandidate/{id}")
    public ResponseObject<List<ApplicationDTO>> listOfApplications(@PathVariable("id") Long id) {

        return ResponseObject.<List<ApplicationDTO>>builder()
                .data(applicationService.listOfApplicationByCandidateId(id))
                .build();

    }

    @GetMapping("/{id}")
    public ResponseObject<List<ApplicationDTO>> listOfApplicationsByJobId(@PathVariable("id") Long id) {

        return ResponseObject.<List<ApplicationDTO>>builder()
                .data(applicationService.listOfApplicationByJobId(id))
                .build();
    }

    @GetMapping("/getByCandidateAndJob/")
    public ResponseObject<ApplicationDTO> getApplication(@RequestParam("candidateId") Long candidateId,
            @RequestParam("jobId") Long jobId) {

        ResponseObject<ApplicationDTO> responseObject = new ResponseObject<>();
        ApplicationDTO applicationDTO = applicationService.getApplicationByCandidateAndJob(candidateId, jobId);

        if (applicationDTO == null) {
            responseObject.setMessage("Not found");
        } else {
            responseObject.setData(applicationDTO);
        }
        return responseObject;
    }

    @PostMapping("/create")
    public ResponseObject<ApplicationDTO> addApplication(@ModelAttribute @Valid ApplicationDTO newApplication) {

        return ResponseObject.<ApplicationDTO>builder()
                .data(applicationService.addApplication(newApplication))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteApplication(@PathVariable("id") Long id) {

        applicationService.deleteApplication(id);
        return ResponseObject.<String>builder()
                .data("Application has been deleted")
                .build();
    }

    @PutMapping("/update/{id}")
    public ResponseObject<ApplicationDTO> updateApplication(@PathVariable("id") Long id,
            @ModelAttribute ApplicationDTO applicationDTO, @ModelAttribute("file") MultipartFile file) {

        return ResponseObject.<ApplicationDTO>builder()
                .data(applicationService.updateApplication(id, applicationDTO, file))
                .build();
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseObject<ApplicationDTO> updateApplicationStatus(@PathVariable("id") Long id,
            @ModelAttribute ApplicationDTO applicationDTO) {

        return ResponseObject.<ApplicationDTO>builder()
                .data(applicationService.updateApplicationStatus(id, applicationDTO))
                .build();
    }

    @GetMapping("/getByJob/{jobId}")
    public ResponseObject<List<ApplicationDTO>> getJobByEmployer(@PathVariable("jobId") Long jobId) {

        return ResponseObject.<List<ApplicationDTO>>builder()
                .data(applicationService.listOfApplicationByJobId(jobId))
                .build();
    }

}
