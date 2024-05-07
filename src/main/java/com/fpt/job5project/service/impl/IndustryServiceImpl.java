package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.IndustryDTO;
import com.fpt.job5project.entity.Industry;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.IndustryMapper;
import com.fpt.job5project.repository.IndustryRepository;
import com.fpt.job5project.service.IIndustryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndustryServiceImpl implements IIndustryService {
    IndustryMapper industryMapper;
    IndustryRepository industryRepository;

    @Override
    public List<IndustryDTO> getAllIndustry() {
        List<IndustryDTO> listDTO = new ArrayList<>();
        List<Industry> listEntity = industryRepository.findAll();
        if (listEntity.isEmpty()) {
            throw new AppException(ErrorCode.LIST_Industry_IS_NULL);
        }
        for (Industry a : listEntity) {
            listDTO.add(industryMapper.toDTO(a));
        }
        return listDTO;
    }
}
