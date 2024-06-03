package com.fpt.job5project.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.StatsicDTO;
import com.fpt.job5project.repository.EmployerRepository;
import com.fpt.job5project.repository.IndustryRepository;
import com.fpt.job5project.repository.JobRepository;
import com.fpt.job5project.repository.RankRepository;
import com.fpt.job5project.service.IStatisticsService;

@Service
public class StatisticsJobImpl implements IStatisticsService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private RankRepository rankRepository;

    public long getTotalJobs() {
        return jobRepository.countTotalJobs();
    }

    public long getTotalCompanies() {
        return employerRepository.countDistinctEmployerIds();
    }

    public List<StatsicDTO> getTotalPriceFromRanks() {
        return rankRepository.getRankPrices()
                .stream()
                .map(objects -> new StatsicDTO((String) objects[0], ((Number) objects[1]).longValue()))
                .collect(Collectors.toList());
    }

    public List<StatsicDTO> getTopIndustriesByJobCount() {
        return industryRepository.findTopIndustriesByJobCount()
                .stream()
                .map(objects -> new StatsicDTO((String) objects[0], ((Number) objects[1]).longValue()))
                .collect(Collectors.toList());
    }

    public List<StatsicDTO> getTopIndustriesByJobApplicationCount(int topCount) {
        return industryRepository.findTopIndustriesByJobApplicationCount(topCount)
                .stream()
                .map(objects -> new StatsicDTO((String) objects[0], ((Number) objects[1]).longValue()))
                .collect(Collectors.toList());
    }

    public List<StatsicDTO> getTopLocationJobCount(int topCount) {
        return jobRepository.findTopLocationJobCount(topCount)
                .stream()
                .map(objects -> new StatsicDTO((String) objects[0], ((Number) objects[1]).longValue()))
                .collect(Collectors.toList());
    }
}
