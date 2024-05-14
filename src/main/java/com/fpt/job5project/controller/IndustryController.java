package com.fpt.job5project.controller;

import com.fpt.job5project.dto.IndustryDTO;
import com.fpt.job5project.dto.JobDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IIndustryService;
import com.fpt.job5project.service.IJobsIndustriesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/industry")
public class IndustryController {
    IIndustryService iIndustryService;
    IJobsIndustriesService iJobsIndustriesService;

    @GetMapping()
    public ResponseObject<List<IndustryDTO>> listIndustrys() {

        ResponseObject<List<IndustryDTO>> responseObject = new ResponseObject<>();

        List<IndustryDTO> listDTOs = iIndustryService.getAllIndustry();
        responseObject.setData(listDTOs);
        return responseObject;
    }

    // @PostMapping("/create")
    // public ResponseObject<IndustryDTO> addIndustry(@ModelAttribute IndustryDTO
    // newIndustry) {
    // ResponseObject<IndustryDTO> responseObject = new ResponseObject<>();
    // responseObject.setData(iIndustryService.addIndustry(newIndustry));
    // return responseObject;
    // }

    // @PutMapping("/update/{id}")
    // public ResponseObject<IndustryDTO> updateIndustry(@PathVariable("id") long
    // id,
    // @ModelAttribute IndustryDTO newIndustry) {
    // ResponseObject<IndustryDTO> responseObject = new ResponseObject<>();
    // responseObject.setData(iIndustryService.updateIndustry(id, newIndustry));
    // return responseObject;
    // }

    // @DeleteMapping("/delete/{id}")
    // public ResponseObject<String> deleteJobDescription(@PathVariable("id") Long
    // id) {

    // ResponseObject<String> responseObject = new ResponseObject<>();
    // iJobsIndustriesService.deleteIndustryJobByIndustryId(id);
    // iIndustryService.deleteIndustry(id);
    // responseObject.setMessage("Job Description has been deleted");
    // return responseObject;
    // }
}