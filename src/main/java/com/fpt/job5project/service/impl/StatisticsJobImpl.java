package com.fpt.job5project.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.IndustryStatsDTO;
import com.fpt.job5project.repository.EmployerRepository;
import com.fpt.job5project.repository.IndustryRepository;
import com.fpt.job5project.repository.JobRepository;
import com.fpt.job5project.service.IStatisticsService;

@Service
public class StatisticsJobImpl implements IStatisticsService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    IndustryRepository industryRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    public long getTotalJobs() {
        return jobRepository.countByIsExpiredFalse();
    }

    @Autowired
    public long getTotalCompanies() {
        return employerRepository.countDistinctEmployerIds();
    }

    public List<IndustryStatsDTO> getTopIndustriesByJobCount(int topCount) {
        return industryRepository.findTopIndustriesByJobCount(topCount)
                .stream()
                .map(objects -> new IndustryStatsDTO((String) objects[0], ((Number) objects[1]).longValue()))
                .collect(Collectors.toList());
    }

    public List<IndustryStatsDTO> getTopIndustriesByJobApplicationCount(int topCount) {
        return industryRepository.findTopIndustriesByJobApplicationCount(topCount)
                .stream()
                .map(objects -> new IndustryStatsDTO((String) objects[0], ((Number) objects[1]).longValue()))
                .collect(Collectors.toList());
    }

    public List<IndustryStatsDTO> getTopLocationJobCount(int topCount) {
        return jobRepository.findTopLocationJobCount(topCount)
                .stream()
                .map(objects -> new IndustryStatsDTO((String) objects[0], ((Number) objects[1]).longValue()))
                .collect(Collectors.toList());
    }
}
