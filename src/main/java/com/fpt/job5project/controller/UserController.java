package com.fpt.job5project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.ForgetPasswordDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.dto.UserChangeDTO;
import com.fpt.job5project.dto.UserDTO;
import com.fpt.job5project.service.IUserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    @Autowired
    IUserService iuserService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping
    ResponseObject<List<UserDTO>> getUser() {
        return ResponseObject.<List<UserDTO>>builder()
                .data(iuserService.listOfUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ResponseObject<UserDTO> getUser(@PathVariable("userId") long userId) {
        return ResponseObject.<UserDTO>builder()
                .data(iuserService.getUserID(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ResponseObject<UserDTO> getMyInfo() {
        return ResponseObject.<UserDTO>builder()
                .data(iuserService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ResponseObject<UserDTO> updateUser(@PathVariable long userId, @ModelAttribute @Valid UserDTO request) {
        return ResponseObject.<UserDTO>builder()
                .data(iuserService.updateUser(userId, request))
                .build();
    }

    @PostMapping("/{userId}")
    public ResponseObject<String> changePassword(@Valid @ModelAttribute UserChangeDTO request,
            @PathVariable long userId) {
        iuserService.changePassword(userId, request);
        return ResponseObject.<String>builder()
                .data("User has been changed")
                .build();
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{userId}")
    ResponseObject<String> deleteUser(@PathVariable long userId) {
        iuserService.deleteUser(userId);
        return ResponseObject.<String>builder()
                .data("User has been deleted")
                .build();
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/lock/{userId}")
    public ResponseObject<String> lockUserAccount(@PathVariable("userId") long userId) {
        iuserService.lockAccount(userId);
        return ResponseObject.<String>builder()
                .data("Lock success")
                .build();
    }

    @PostMapping("/forget")
    public ResponseObject<String> forgetAccount(@ModelAttribute ForgetPasswordDTO request) {
        iuserService.forgetPassword(request);
        return ResponseObject.<String>builder()
                .data("Sending email successfully")
                .build();
    }

}
