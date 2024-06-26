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

import com.fpt.job5project.dto.FollowDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IFollowService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/follow")
public class FollowController {

    IFollowService iFollowService;

    @GetMapping("/list/{id}")
    public ResponseObject<List<FollowDTO>> listOfJobFollowByCandidateId(@PathVariable("id") Long id) {

        ResponseObject<List<FollowDTO>> responseObject = new ResponseObject<>();

        List<FollowDTO> listDTOs = iFollowService.listOfJobFollowByCandidateId(id);
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @GetMapping("/listFollow/{id}")
    public ResponseObject<Integer> listOfFollowByEnployerId(@PathVariable("id") Long id) {

        ResponseObject<Integer> responseObject = new ResponseObject<>();

        List<FollowDTO> listDTOs = iFollowService.listOfFollowByEmployerId(id);
        responseObject.setData(listDTOs.size());
        return responseObject;
    }

    @GetMapping("/getByCandidateAndEmployer/{candidateId}/{employerId}")
    public ResponseObject<List<FollowDTO>> getlistJobInterests(@PathVariable("candidateId") Long candidateId,
            @PathVariable("employerId") Long employerId) {

        ResponseObject<List<FollowDTO>> responseObject = new ResponseObject<>();
        List<FollowDTO> followDTOS = iFollowService.getJobFollowByCandidateAndEmployer(candidateId, employerId);
        responseObject.setData(followDTOS);
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<FollowDTO> getJobFollow(@PathVariable("id") long id) {

        ResponseObject<FollowDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iFollowService.getJobFollow(id));
        return responseObject;
    }

    @PostMapping("/create")
    public ResponseObject<FollowDTO> addJobFollow(@ModelAttribute @Valid FollowDTO newJobFollow) {

        ResponseObject<FollowDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iFollowService.addJobFollow(newJobFollow));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteJobFollow(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iFollowService.deleteJobFollow(id);
        responseObject.setMessage("Job follow has been deleted");
        return responseObject;
    }

    @PutMapping("/update/{id}")
    public ResponseObject<FollowDTO> updateJobFollow(@PathVariable("id") Long id, @ModelAttribute FollowDTO followDTO) {

        ResponseObject<FollowDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iFollowService.updateJobFollow(id, followDTO));
        return responseObject;
    }
}
