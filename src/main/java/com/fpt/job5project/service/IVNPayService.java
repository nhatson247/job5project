package com.fpt.job5project.service;

import jakarta.servlet.http.HttpServletRequest;

public interface IVNPayService {
    public String createOrder(int total, String orderInfor, String urlReturn);

    public int orderReturn(HttpServletRequest request);
}
