package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.RankDTO;
import com.fpt.job5project.entity.Rank;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.RankMapper;
import com.fpt.job5project.repository.RankRepository;
import com.fpt.job5project.service.IRankService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//Autowired, private, final
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RankServiceImpl implements IRankService {

    RankRepository rankRepository;
    RankMapper rankMapper;

    @Override
    public List<RankDTO> getAllRank() {
        List<RankDTO> listDTOs = new ArrayList<>();
        List<Rank> listEntities = rankRepository.findAll();
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_RANKS_IS_NULL);
        }
        for (Rank RankEntity : listEntities) {
            listDTOs.add(rankMapper.toDTO(RankEntity));
        }
        return listDTOs;


    }

    @Override
    public RankDTO getRankById(long rankId) {
        Rank rank = rankRepository.findById(rankId).orElseThrow(() -> new AppException(ErrorCode.RANK_NOT_EXIST));
        return rankMapper.toDTO(rank);
    }


}
