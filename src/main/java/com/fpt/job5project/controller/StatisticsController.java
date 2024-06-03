package com.fpt.job5project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.StatsicDTO;
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

        // 1 thống kê theo ngành
        @GetMapping("/count/industry")
        public ResponseObject<List<StatsicDTO>> getTopIndustriesByJobCount() {
                List<StatsicDTO> topIndustries = iStatisticsService.getTopIndustriesByJobCount();

                return ResponseObject.<List<StatsicDTO>>builder()
                                .data(topIndustries)
                                .build();
        }

        // 3 thống kê tiền theo hạng
        @GetMapping("/totalPrice")
        public ResponseObject<List<StatsicDTO>> getTotalPriceFromRanks() {
                List<StatsicDTO> totalPrice = iStatisticsService.getTotalPriceFromRanks();
                return ResponseObject.<List<StatsicDTO>>builder()
                                .data(totalPrice)
                                .build();
        }

        // 2 thống kê công việc theo job
        @GetMapping("/count/industryApplications")
        public ResponseObject<List<StatsicDTO>> getTopIndustriesByJobApplicationCount(
                        @RequestParam(name = "topCount", defaultValue = "4") int topCount) {
                List<StatsicDTO> topIndustries = iStatisticsService.getTopIndustriesByJobApplicationCount(topCount);

                return ResponseObject.<List<StatsicDTO>>builder()
                                .data(topIndustries)
                                .build();
        }

        // thống kê theo khu vực
        @GetMapping("/count/locationJob")
        public ResponseObject<List<StatsicDTO>> getTopLocationJob(
                        @RequestParam(name = "topCount", defaultValue = "4") int topCount) {
                List<StatsicDTO> topIndustries = iStatisticsService.getTopLocationJobCount(topCount);

                return ResponseObject.<List<StatsicDTO>>builder()
                                .data(topIndustries)
                                .build();
        }
}
