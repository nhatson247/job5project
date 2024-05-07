package com.fpt.job5project.service;

import com.fpt.job5project.dto.RankDTO;

import java.util.List;

public interface IRankService {
    public List<RankDTO> getAllRank();

    public RankDTO getRankById(long rankId);
}
