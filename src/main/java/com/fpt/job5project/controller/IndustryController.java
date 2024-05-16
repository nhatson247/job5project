package com.fpt.job5project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.IndustryDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IIndustryService;
import com.fpt.job5project.service.IJobsIndustriesService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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

    @PostMapping("/create")
    public ResponseObject<IndustryDTO> addIndustry(@Valid @ModelAttribute IndustryDTO newIndustry) {
        ResponseObject<IndustryDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iIndustryService.addIndustry(newIndustry));
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<IndustryDTO> updateIndustry(@Valid @PathVariable("id") long id,
            @ModelAttribute IndustryDTO newIndustry) {
        ResponseObject<IndustryDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iIndustryService.updateIndustry(id, newIndustry));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteJobDescription(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iJobsIndustriesService.deleteIndustryJobByIndustryId(id);
        iIndustryService.deleteIndustry(id);
        responseObject.setMessage("Job Description has been deleted");
        return responseObject;
    }

    @GetMapping("/{industryId}")
    ResponseObject<IndustryDTO> getIndustry(@PathVariable("industryId") long industryId) {
        return ResponseObject.<IndustryDTO>builder()
                .data(iIndustryService.getIndustryID(industryId))
                .build();
    }
}
