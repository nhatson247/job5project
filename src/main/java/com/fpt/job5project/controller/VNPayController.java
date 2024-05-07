package com.fpt.job5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.OrderDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IVNPayService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/vnpay")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VNPayController {
    @Autowired
    IVNPayService vnPayService;

    @PostMapping("/create-order")
    public ResponseObject<String> createOrder(@ModelAttribute OrderDTO request, HttpServletRequest http) {
        String baseUrl = http.getScheme() + "://" + http.getServerName() + ":" + http.getServerPort();
        String vnpayUrl = vnPayService.createOrder(request, baseUrl);
        return ResponseObject.<String>builder()
                .data(vnpayUrl)
                .build();
    }

}
