package com.fpt.job5project.controller;

import com.fpt.job5project.dto.ApplicationDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IApplicationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/application")
public class ApplicationController {
    IApplicationService applicationService;

    @GetMapping("/getByCandidate/{id}")
    public ResponseObject<List<ApplicationDTO>> listOfApplications(@PathVariable("id") Long id) {

        ResponseObject<List<ApplicationDTO>> responseObject = new ResponseObject<>();

        List<ApplicationDTO> listDTOs = applicationService.listOfApplicationByCandidateId(id);
        responseObject.setData(listDTOs);
        return responseObject;
    }
    @GetMapping("/{id}")
    public ResponseObject<List<ApplicationDTO>> listOfApplicationsByJobId(@PathVariable("id") Long id) {

        ResponseObject<List<ApplicationDTO>> responseObject = new ResponseObject<>();

        List<ApplicationDTO> listDTOs = applicationService.listOfApplicationByJobId(id);
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @GetMapping("/getByCandidateAndJob/")
    public ResponseObject<ApplicationDTO> getApplication(@RequestParam("candidateId") Long candidateId,@RequestParam("jobId") Long jobId) {

        ResponseObject<ApplicationDTO> responseObject = new ResponseObject<>();
        responseObject.setData(applicationService.getApplicationByCandidateAndJob(candidateId,jobId));
        return responseObject;
    }


    @PostMapping("/create")
    public ResponseObject<ApplicationDTO> addApplication(@ModelAttribute @Valid ApplicationDTO newApplication, @ModelAttribute("file") MultipartFile file) {

        ResponseObject<ApplicationDTO> responseObject = new ResponseObject<>();
        responseObject.setData(applicationService.addApplication(newApplication, file));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteApplication(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        applicationService.deleteApplication(id);
        responseObject.setMessage("Application has been deleted");
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<ApplicationDTO> updateApplication(@PathVariable("id") Long id, @ModelAttribute ApplicationDTO applicationDTO, @ModelAttribute("file") MultipartFile file) {

        ResponseObject<ApplicationDTO> responseObject = new ResponseObject<>();
        responseObject.setData(applicationService.updateApplication(id, applicationDTO, file));
        return responseObject;
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseObject<ApplicationDTO> updateApplicationStatus(@PathVariable("id") Long id, @ModelAttribute ApplicationDTO applicationDTO) {
        ResponseObject<ApplicationDTO> responseObject = new ResponseObject<>();
        responseObject.setData(applicationService.updateApplicationStatus(id, applicationDTO));
        return responseObject;

    }

    @GetMapping("/getByJob/{jobId}")
    public ResponseObject<List<ApplicationDTO>> getJobByEmployer(@PathVariable("jobId") Long jobId) {
        ResponseObject<List<ApplicationDTO>> responseObject = new ResponseObject<>();
        responseObject.setData(applicationService.listOfApplicationByJobId(jobId));
        return responseObject;
    }

}
