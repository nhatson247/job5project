package com.fpt.job5project.controller;


import com.fpt.job5project.dto.CandidateDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.ICandidateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/Candidate")
public class CandidateController {
    ICandidateService iCandidateService;


    @GetMapping("/")
    public ResponseObject<List<CandidateDTO>> listCandidates() {

        ResponseObject<List<CandidateDTO>> responseObject = new ResponseObject<>();

        List<CandidateDTO> listDTOs = iCandidateService.listOfCandidate();
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<CandidateDTO> getOneCandidate(@PathVariable("id") long id) {

        ResponseObject<CandidateDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iCandidateService.getCandidate(id));
        return responseObject;
    }


//    @PostMapping("/Create")
//    public ResponseObject<CategoryDTO> addCategory(@RequestBody @Valid CategoryDTO newCategory) {
//
//        ResponseObject<CategoryDTO> responseObject = new ResponseObject<>();
//        responseObject.setData(iCategoryService.addCategory(newCategory));
//        return responseObject;
//    }

    @DeleteMapping("/Delete/{id}")
    public ResponseObject<String> deleteCategory(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iCandidateService.deleteCandidate(id);
        responseObject.setMessage("Candidate has been deleted");
        return responseObject;
    }

    @PutMapping("/Update/{id}")
    public ResponseObject<CandidateDTO> updateCategory(@PathVariable("id") Long id, @ModelAttribute CandidateDTO candidateDTO,@ModelAttribute("file") MultipartFile file) {

        ResponseObject<CandidateDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iCandidateService.updateCandidate(id, candidateDTO, file));
        return responseObject;
    }
}
