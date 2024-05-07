package com.fpt.job5project.controller;

import com.fpt.job5project.dto.IndustryDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IIndustryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/industry")
public class IndustryController {
    IIndustryService iIndustryService;
    @GetMapping()
    public ResponseObject<List<IndustryDTO>> listIndustrys() {

        ResponseObject<List<IndustryDTO>> responseObject = new ResponseObject<>();

        List<IndustryDTO> listDTOs = iIndustryService.getAllIndustry();
        responseObject.setData(listDTOs);
        return responseObject;
    }
}
