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

    @Override
    public IndustryDTO addIndustry(IndustryDTO newIndustryDTO) {
        Industry newIndustry = new Industry();
        newIndustry = industryMapper.toEntity(newIndustryDTO);
        return industryMapper.toDTO(industryRepository.save(newIndustry));
    }

    @Override
    public IndustryDTO updateIndustry(long id, IndustryDTO newIndustryDTO) {
        Industry foundIndustry = industryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_NOT_EXIST));
        industryMapper.updateIndustry(foundIndustry, newIndustryDTO);
        return industryMapper.toDTO(industryRepository.save(foundIndustry));
    }

    @Override
    public void deleteIndustry(long id) {
        boolean existsById = industryRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.CANDIDATE_NOT_EXIST);
        } else {
            industryRepository.deleteById(id);
        }
    }

    @Override
    public IndustryDTO getIndustryID(long id) {
        return industryMapper.toDTO(industryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)));
    }
}
