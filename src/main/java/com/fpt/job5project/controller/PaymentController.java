package com.fpt.job5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.fpt.job5project.repository.EmployerRepository;
import com.fpt.job5project.service.IVNPayService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentController {
    @Autowired
    IVNPayService vnPayService;

    @Autowired
    EmployerRepository employerRepository;

    @GetMapping("/api/vnpay/vnpay-payment")
    public RedirectView handleVnPayPayment(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        System.out.println(orderInfo);

        // Up rank
        String[] parts = orderInfo.split("-");
        orderInfo = parts[0];
        long rankId = Long.parseLong(parts[1]);
        long userId = Long.parseLong(parts[2]);
        System.out.println("rankId: " + rankId + ", userId: " + userId);
        int result = employerRepository.updateRankById(rankId, userId);

        System.out.println("alo" + paymentStatus);

        if (paymentStatus == 1 && result == 1) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://localhost:5173/rank-up/success"
                    + "?vnp_OrderInfo=" + orderInfo
                    + "&vnp_Amount=" + totalPrice
                    + "&vnp_PayDate=" + paymentTime
                    + "&vnp_TransactionNo=" + transactionId);
            return redirectView;
        } else {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("https://www.youtube.com");
            return redirectView;

        }
    }
}
