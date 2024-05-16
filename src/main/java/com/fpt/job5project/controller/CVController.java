package com.fpt.job5project.controller;

import com.fpt.job5project.dto.CVDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.ICVService;
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
@RequestMapping("api/cv")
public class CVController {
    ICVService icvService;

    @GetMapping("/list/{id}")
    public ResponseObject<List<CVDTO>> listofCvs(@PathVariable("id") Long id) {

        ResponseObject<List<CVDTO>> responseObject = new ResponseObject<>();

        List<CVDTO> listDTOs = icvService.listOfCv(id);
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<CVDTO> getCv(@PathVariable("id") long id) {

        ResponseObject<CVDTO> responseObject = new ResponseObject<>();
        responseObject.setData(icvService.getCv(id));
        return responseObject;
    }


    @PostMapping("/create")
    public ResponseObject<CVDTO> addCv(@ModelAttribute @Valid CVDTO newCv,@ModelAttribute("file") MultipartFile file) {

        ResponseObject<CVDTO> responseObject = new ResponseObject<>();
        responseObject.setData(icvService.addCv(newCv, file));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteCv(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        icvService.deleteCv(id);
        responseObject.setMessage("Cv has been deleted");
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<CVDTO> updateCv(@PathVariable("id") Long id, @ModelAttribute CVDTO cvdto,@ModelAttribute("file") MultipartFile file) {

        ResponseObject<CVDTO> responseObject = new ResponseObject<>();
        responseObject.setData(icvService.updateCv(id, cvdto, file));
        return responseObject;
    }
}
