package com.fpt.job5project.controller;

import com.fpt.job5project.dto.ProvinceDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IProvinceService;
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
@RequestMapping("api/province")
public class ProvinceController {
    IProvinceService iProvinceService;
    @GetMapping()
    public ResponseObject<List<ProvinceDTO>> listProvinces() {
        ResponseObject<List<ProvinceDTO>> responseObject = new ResponseObject<>();
        List<ProvinceDTO> listDTOs = iProvinceService.getAllProvince();
        responseObject.setData(listDTOs);
        return responseObject;
    }
}
