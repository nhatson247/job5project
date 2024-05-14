package com.fpt.job5project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.IndustryStatsDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IStatisticsService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/statistics")
public class StatisticsController {

    IStatisticsService iStatisticsService;

    @GetMapping("/total/job")
    ResponseObject<Long> getTotalJobs() {

        long totalJobs = iStatisticsService.getTotalJobs();

        return ResponseObject.<Long>builder()
                .data(totalJobs)
                .build();

    }

    @GetMapping("/total/companies")
    ResponseObject<Long> getTotalCompanies() {
        long totalCompanies = iStatisticsService.getTotalCompanies();

        return ResponseObject.<Long>builder()
                .data(totalCompanies)
                .build();

    }

    @GetMapping("/count/industry")
    public ResponseObject<List<IndustryStatsDTO>> getTopIndustriesByJobCount(
            @RequestParam(name = "topCount", defaultValue = "4") int topCount) {
        List<IndustryStatsDTO> topIndustries = iStatisticsService.getTopIndustriesByJobCount(topCount);

        return ResponseObject.<List<IndustryStatsDTO>>builder()
                .data(topIndustries)
                .build();
    }

    @GetMapping("/count/industryApplications")
    public ResponseObject<List<IndustryStatsDTO>> getTopIndustriesByJobApplicationCount(
            @RequestParam(name = "topCount", defaultValue = "4") int topCount) {
        List<IndustryStatsDTO> topIndustries = iStatisticsService.getTopIndustriesByJobApplicationCount(topCount);

        return ResponseObject.<List<IndustryStatsDTO>>builder()
                .data(topIndustries)
                .build();
    }

    @GetMapping("/count/locationJob")
    public ResponseObject<List<IndustryStatsDTO>> getTopLocationJob(
            @RequestParam(name = "topCount", defaultValue = "4") int topCount) {
        List<IndustryStatsDTO> topIndustries = iStatisticsService.getTopLocationJobCount(topCount);

        return ResponseObject.<List<IndustryStatsDTO>>builder()
                .data(topIndustries)
                .build();
    }
}
