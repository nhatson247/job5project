package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.FollowDTO;
import com.fpt.job5project.entity.Follow;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.FollowMapper;
import com.fpt.job5project.repository.FollowRepository;
import com.fpt.job5project.service.IFollowService;
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
public class FollowServiceImpl implements IFollowService {
    FollowRepository followRepository;
    FollowMapper followMapper;
    @Override
    public List<FollowDTO> listOfJobFollowByCandidateId(Long id) {
        List<FollowDTO> listDTOs = new ArrayList<>();
//        List<Follow> listEntities = jobFollowRepository.findAll();
//        if (listEntities.isEmpty()) {
//            throw new AppException(ErrorCode.LIST_CV_IS_NULL);
//        }
        for (Follow followEntity : followRepository.findByCandidateId(id)) {
            System.out.println("hello"+followEntity.getCandidateId());
            listDTOs.add(followMapper.toDTO(followEntity));
        }
        return listDTOs;
    }

    @Override
    public FollowDTO getJobFollow(long id) {
        Follow jobFollow = followRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        return followMapper.toDTO(jobFollow);
    }

    @Override
    public FollowDTO addJobFollow(FollowDTO followDTO) {
        Follow jobFollow = followMapper.toEntity(followDTO);
        jobFollow.setFollowDate(new Date());
        return followMapper.toDTO(followRepository.save(jobFollow));
    }

    @Override
    public FollowDTO updateJobFollow(long id, FollowDTO followDTO) {
        Follow foundJobFollow = followRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        if (foundJobFollow != null) {
            followMapper.updateJobFollow(foundJobFollow, followDTO);
        }
        return followMapper.toDTO(followRepository.save(foundJobFollow));
    }

    @Override
    public void deleteJobFollow(long id) {
        boolean existsById = followRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.CV_NOT_EXIST);
        } else {
            followRepository.deleteById(id);
        }
    }
}
