package com.fpt.job5project.controller;

import com.fpt.job5project.dto.EmployerApprovedDTO;
import com.fpt.job5project.dto.EmployerDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IEmployerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/employer")
public class EmployerController {

    IEmployerService iEmployerService;

    @GetMapping("")
    public ResponseObject<List<EmployerDTO>> listEmployers() {

        ResponseObject<List<EmployerDTO>> responseObject = new ResponseObject<>();

        List<EmployerDTO> listDTOs = iEmployerService.listOfEmployers();
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<EmployerDTO> getOneEmployer(@PathVariable("id") long id) {

        ResponseObject<EmployerDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iEmployerService.getEmployer(id));
        return responseObject;
    }

    @PostMapping("/Create")
    public ResponseObject<EmployerDTO> addEmployer(@ModelAttribute @Valid EmployerDTO newEmployer) {

        ResponseObject<EmployerDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iEmployerService.addEmployer(newEmployer));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteEmployer(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iEmployerService.deleteEmployer(id);
        responseObject.setMessage("Employer has been deleted");
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<EmployerDTO> updateEmployer(@PathVariable("id") Long id,
            @ModelAttribute EmployerDTO employerDTO, @ModelAttribute("filePhoto") MultipartFile filePhoto,
            @ModelAttribute("fileBackground") MultipartFile fileBackground) {

        ResponseObject<EmployerDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iEmployerService.updateEmployer(id, employerDTO, filePhoto, fileBackground));
        return responseObject;
    }

    @GetMapping("/approved")
    public ResponseObject<List<EmployerApprovedDTO>> getApprovedEmployers() {

        ResponseObject<List<EmployerApprovedDTO>> responseObject = new ResponseObject<>();
        List<EmployerApprovedDTO> approvedEmployers = iEmployerService.listOfApprovedEmployers();
        responseObject.setData(approvedEmployers);
        return responseObject;
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/approvedAccept/{userId}")
    public ResponseObject<String> approvedAcceptEmployer(@PathVariable("userId") long userId) {

        iEmployerService.approveAcceptEmployer(userId);
        return ResponseObject.<String>builder()
                .data("accept is success")
                .build();
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/approvedNotAccept/{id}")
    public ResponseObject<String> approvedNotAcceptEmployer(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iEmployerService.deleteEmployerSendMail(id);
        responseObject.setMessage("not accept is success");
        return responseObject;
    }
}
