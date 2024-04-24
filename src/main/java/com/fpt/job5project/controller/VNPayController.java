package com.fpt.job5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.OrderDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IVNPayService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VNPayController {
    @Autowired
    IVNPayService vnPayService;

    @PostMapping("/create-order")
    public ResponseObject<String> createOrder(@ModelAttribute OrderDTO request, HttpServletRequest http) {
        String baseUrl = http.getScheme() + "://" + http.getServerName() + ":" + http.getServerPort();
        String vnpayUrl = vnPayService.createOrder(request.getAmount(), request.getOrderInfo(),
                baseUrl);
        return ResponseObject.<String>builder()
                .data(vnpayUrl)
                .build();
    }

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }

}
