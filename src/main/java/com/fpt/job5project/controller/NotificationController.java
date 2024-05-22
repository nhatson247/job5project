package com.fpt.job5project.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.NotificationDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IEmployerService;
import com.fpt.job5project.service.INotificationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/notification")
public class NotificationController {

    INotificationService iNotificationService;

    IEmployerService iEmployerService;

    @GetMapping("/{id}")
    public ResponseObject<List<NotificationDTO>> listNotification(@PathVariable("id") long id) {

        ResponseObject<List<NotificationDTO>> responseObject = new ResponseObject<>();

        List<NotificationDTO> listDTOs = iNotificationService.findByUserIdOrderByNotificationIdDesc(id);
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @PostMapping("/create")
    public ResponseObject<NotificationDTO> addNotification(@ModelAttribute NotificationDTO newNotification) {
        ResponseObject<NotificationDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iNotificationService.addNotification(newNotification));
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deleteNotification(@PathVariable("id") Long id) {

        ResponseObject<String> responseObject = new ResponseObject<>();
        iNotificationService.deleteNotification(id);
        responseObject.setMessage("One Notification has been deleted");
        return responseObject;
    }

    @DeleteMapping("/deleteByUser/{id}")
    public ResponseObject<String> deleteAllNotification(@PathVariable("id") Long id) {
        ResponseObject<String> responseObject = new ResponseObject<>();
        iNotificationService.deleteAllNotification(id);
        responseObject.setMessage("All Notification has been deleted");
        return responseObject;
    }

    @PutMapping("/updateByUser/{id}")
    public ResponseObject<Integer> updateEmployer(@PathVariable("id") Long id) {
        ResponseObject<Integer> responseObject = new ResponseObject<>();
        responseObject.setData(iNotificationService.updateNotificationStatus(id));
        return responseObject;
    }

}
