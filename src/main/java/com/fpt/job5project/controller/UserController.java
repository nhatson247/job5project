package com.fpt.job5project.controller;

import com.fpt.job5project.dto.UserDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IUserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    @Autowired
    IUserService iuserService;

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

    @PostMapping
    ResponseObject<UserDTO> createUser(@ModelAttribute @Valid UserDTO request) throws Exception {
        return ResponseObject.<UserDTO>builder()
                .data(iuserService.addUser(request))
                .build();
    }

    @PutMapping("/{userId}")
    ResponseObject<UserDTO> updateUser(@PathVariable long userId, @ModelAttribute UserDTO request) {
        return ResponseObject.<UserDTO>builder()
                .data(iuserService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ResponseObject<String> deleteUser(@PathVariable long userId) {
        iuserService.deleteUser(userId);
        return ResponseObject.<String>builder()
                .data("Category has been deleted")
                .build();
    }
}
