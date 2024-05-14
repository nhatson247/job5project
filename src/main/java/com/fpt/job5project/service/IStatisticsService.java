package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.IndustryStatsDTO;

public interface IStatisticsService {
    public long getTotalJobs();

    public long getTotalCompanies();

    public List<IndustryStatsDTO> getTopIndustriesByJobCount(int topCount);

    public List<IndustryStatsDTO> getTopIndustriesByJobApplicationCount(int topCount);

    public List<IndustryStatsDTO> getTopLocationJobCount(int topCount);
}
