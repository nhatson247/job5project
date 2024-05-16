package com.fpt.job5project.controller;


import com.fpt.job5project.dto.JobReportDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IJobReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/JobReport")
public class JobReportController {

    IJobReportService iJobReportService;

    @GetMapping("/")
    public ResponseObject<List<JobReportDTO>> listJobReport() {

        ResponseObject<List<JobReportDTO>> responseObject = new ResponseObject<>();
        List<JobReportDTO> listDTOs = iJobReportService.listOfJobReports();
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @PostMapping("/Create")
    public ResponseObject<JobReportDTO> addJobInterest(@ModelAttribute JobReportDTO jobReportDTO) {
        ResponseObject<JobReportDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobReportService.addJobReport(jobReportDTO));
        return responseObject;
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseObject<String> deleteJobInterest(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobReportService.deleteJobReport(id);
        responseObject.setMessage("Job report has been deleted");
        return responseObject;
    }
}
