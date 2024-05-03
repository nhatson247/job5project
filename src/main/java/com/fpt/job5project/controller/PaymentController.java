package com.fpt.job5project.controller;

import com.fpt.job5project.dto.EmployerDTO;
import com.fpt.job5project.dto.OrderDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IVNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentController {
    @Autowired
    IVNPayService vnPayService;

    @GetMapping("/api/vnpay/vnpay-payment")
    public RedirectView handleVnPayPayment(HttpServletRequest request, Model model) {

        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        if (paymentStatus == 1) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://localhost:5173/home" +
                    "?vnp_Amount=" + totalPrice + "&vnp_OrderInfo=" + orderInfo);
            return redirectView;
        } else {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("https://www.youtube.com");
            return redirectView;

        }
    }
}