package com.fpt.job5project.service;

import com.fpt.job5project.dto.OrderDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface IVNPayService {
    public String createOrder(OrderDTO request, String urlReturn);

    public int orderReturn(HttpServletRequest request);
}
