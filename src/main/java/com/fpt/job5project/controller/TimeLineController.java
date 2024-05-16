package com.fpt.job5project.controller;


import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.dto.TimeLineDTO;
import com.fpt.job5project.service.ITimeLineService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/timeLine")
public class TimeLineController {
    ITimeLineService iTimeLineService;

    @GetMapping("/{id}")
    public ResponseObject<List<TimeLineDTO>> listofTimeLine(@PathVariable("id") Long id) {

        ResponseObject<List<TimeLineDTO>> responseObject = new ResponseObject<>();

        List<TimeLineDTO> listDTOs = iTimeLineService.listOfTimeLine(id);
        responseObject.setData(listDTOs);
        return responseObject;
    }

    /*@GetMapping("/{id}")
    public ResponseObject<ApplicationDTO> getApplication(@PathVariable("id") long id) {

        ResponseObject<ApplicationDTO> responseObject = new ResponseObject<>();
        responseObject.setData(applicationService.getApplication(id));
        return responseObject;
    }*/


    @PostMapping("/Create")
    public ResponseObject<TimeLineDTO> addTimeLine(@ModelAttribute @Valid TimeLineDTO newApplication) {

        ResponseObject<TimeLineDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iTimeLineService.addTimeLine(newApplication));
        return responseObject;
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseObject<String> deleteTimeLine(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iTimeLineService.deleteTimeLine(id);
        responseObject.setMessage("Time line has been deleted");
        return responseObject;
    }

    @PutMapping("/Update/{id}")
    public ResponseObject<TimeLineDTO> updateTimeLine(@PathVariable("id") Long id, @ModelAttribute TimeLineDTO timeLineDTO) {

        ResponseObject<TimeLineDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iTimeLineService.updateTimeLine(id, timeLineDTO));
        return responseObject;
    }
}
