package com.fpt.job5project.controller;

import com.fpt.job5project.dto.JobReportDTO;
import com.fpt.job5project.dto.JobReportDetailDTO;
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

    @GetMapping
    public ResponseObject<List<JobReportDetailDTO>> listJobReport() {

        ResponseObject<List<JobReportDetailDTO>> responseObject = new ResponseObject<>();
        List<JobReportDetailDTO> listDTOs = iJobReportService.listOfJobReports();
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @PostMapping("/create")
    public ResponseObject<JobReportDTO> addJobInterest(@ModelAttribute JobReportDTO jobReportDTO) {
        ResponseObject<JobReportDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iJobReportService.addJobReport(jobReportDTO));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteJobInterest(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobReportService.deleteJobReportID(id);
        responseObject.setMessage("Job report has been deleted");
        return responseObject;
    }

    @GetMapping("/getByCandidateAndJob/{candidateId}/{jobId}")
    public ResponseObject<Boolean> getReportByCandidateIdAndJobId(@PathVariable("candidateId") Long candidateId,
            @PathVariable("jobId") Long jobId) {

        ResponseObject<Boolean> responseObject = new ResponseObject<>();
        Boolean check = iJobReportService.getReportByCandidateIdAndJobId(candidateId, jobId);
        responseObject.setData(check);
        return responseObject;
    }
}
