package com.fpt.job5project.service;



import com.fpt.job5project.dto.EmployerReviewDTO;

import java.util.List;

public interface IEmployerReviewService {
    public List<EmployerReviewDTO> listOfEmployerReview();

    public EmployerReviewDTO getEmployerReview(long id);

    public EmployerReviewDTO addEmployerReview(EmployerReviewDTO employerReviewDTO);

    public EmployerReviewDTO updateEmployerReview(long id, EmployerReviewDTO employerReviewDTO);

    public void deleteEmployerReview(long id);
}
