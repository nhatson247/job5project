package com.fpt.job5project.service;

import com.fpt.job5project.dto.FollowDTO;

import java.util.List;

public interface IFollowService {
   public List<FollowDTO> listOfJobFollowByCandidateId(Long id);
    public List<FollowDTO> listOfFollowByEmployerId(Long id);

    public FollowDTO getJobFollow(long id);

    public List<FollowDTO> getJobFollowByCandidateAndEmployer(Long candidateId, Long employerId);

    public FollowDTO addJobFollow(FollowDTO followDTO);

    public FollowDTO updateJobFollow(long id, FollowDTO followDTO);

    public void deleteJobFollow(long id);
}
