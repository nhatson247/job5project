package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.ProvinceDTO;
import com.fpt.job5project.entity.Province;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.ProvinceMapper;
import com.fpt.job5project.repository.ProvinceRepository;
import com.fpt.job5project.service.IProvinceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
// Autowired, private, final
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProvinceServiceImpl implements IProvinceService {

    ProvinceRepository provinceRepository;
    ProvinceMapper provinceMapper;

    @Override
    public List<ProvinceDTO> getAllProvince() {
        List<ProvinceDTO> listDTOs = new ArrayList<>();
        List<Province> listEntities = provinceRepository.findAll();
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_PROVINCES_IS_NULL);
        }
        for (Province ProvinceEntity : listEntities) {
            listDTOs.add(provinceMapper.toDTO(ProvinceEntity));
        }
        return listDTOs;

    }
}
