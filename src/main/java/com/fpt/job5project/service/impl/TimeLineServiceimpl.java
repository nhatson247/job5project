package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.TimeLineDTO;
import com.fpt.job5project.entity.Application;
import com.fpt.job5project.entity.TimeLine;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.TimeLineMapper;
import com.fpt.job5project.repository.TimeLineRepository;
import com.fpt.job5project.service.ITimeLineService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeLineServiceimpl implements ITimeLineService {

    TimeLineRepository timeLineRepository;
    TimeLineMapper timeLineMapper;

    @Override
    public List<TimeLineDTO> listOfTimeLine(Long id) {
        List<TimeLineDTO> listDTOs = new ArrayList<>();
        List<TimeLine> listEntities = timeLineRepository.findByCandidateId(id);
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_CV_IS_NULL);
        }
        for (TimeLine timeLineEntity : timeLineRepository.findByCandidateId(id)) {
            listDTOs.add(timeLineMapper.toDTO(timeLineEntity));
        }
        return listDTOs;
    }

    @Override
    public TimeLineDTO addTimeLine(TimeLineDTO timeLineDTO) {
        TimeLine timeLine = timeLineMapper.toEntity(timeLineDTO);
        return timeLineMapper.toDTO(timeLineRepository.save(timeLine));
    }

    @Override
    public TimeLineDTO updateTimeLine(long id, TimeLineDTO timeLineDTO) {
        TimeLine foundApplication = timeLineRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        if (foundApplication != null) {
            timeLineMapper.updateTimeLine(foundApplication, timeLineDTO);
        }
        return timeLineMapper.toDTO(timeLineRepository.save(foundApplication));
    }

    @Override
    public void deleteTimeLine(long id) {
        boolean existsById = timeLineRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.CV_NOT_EXIST);
        } else {
            timeLineRepository.deleteById(id);
        }
    }
}
