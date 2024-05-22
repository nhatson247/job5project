package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.StatsicDTO;

public interface IStatisticsService {
    public long getTotalJobs();

    public long getTotalCompanies();

    public List<StatsicDTO> getTotalPriceFromRanks();

    public List<StatsicDTO> getTopIndustriesByJobCount(int topCount);

    public List<StatsicDTO> getTopIndustriesByJobApplicationCount(int topCount);

    public List<StatsicDTO> getTopLocationJobCount(int topCount);
}
