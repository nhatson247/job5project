package com.fpt.job5project.service.impl;

import com.fpt.job5project.Model.JobsIndustries;
import com.fpt.job5project.repository.JobsIndustriesRepository;
import com.fpt.job5project.service.IJobsIndustriesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobsIndustriesImpl implements IJobsIndustriesService {
    JobsIndustriesRepository jobsIndustriesRepository;
    @Override
    public void addJobsIndustries(JobsIndustries jobsIndustries) {

        int checkExists = jobsIndustriesRepository.checkExists(jobsIndustries.getIndustries_industryid(), jobsIndustries.getJob_jobid());
        if(checkExists == 0){
            jobsIndustriesRepository.insertJobsIndustries(jobsIndustries.getIndustries_industryid(), jobsIndustries.getJob_jobid());
        }

    }

    @Override
    public List<JobsIndustries> getJobIndustriesByJobId(long jobId) {
        List<JobsIndustries> listIndustries = jobsIndustriesRepository.findIndustriesByJobId(jobId);
        return  listIndustries;
    }

    @Override
    public void deleteIndustryOfJob(long jobId, long industryId) {
        jobsIndustriesRepository.deleteIndustryOfJob(jobId, industryId);
    }

    @Override
    public void deleteIndustriesByJobId(long jobId) {
        jobsIndustriesRepository.deleteIndustryByJobId((jobId));
    }

    @Override
    public void deleteIndustryJobByIndustryId(long industryId) {
        jobsIndustriesRepository.deleteIndustryJobByIndustryId(industryId);
    }


}
