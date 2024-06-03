package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.JobsIndustriesDTO;
import com.fpt.job5project.dto.QuantityJobDTO;
import com.fpt.job5project.repository.JobsIndustriesRepository;
import com.fpt.job5project.service.IJobsIndustriesService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobsIndustriesImpl implements IJobsIndustriesService {

    JobsIndustriesRepository jobsIndustriesRepository;

    @Override
    public void addJobsIndustries(JobsIndustriesDTO jobsIndustries) {
        System.out.println(jobsIndustries);
        int checkExists = jobsIndustriesRepository.checkExists(jobsIndustries.getIndustries_industryid(),
                jobsIndustries.getJob_jobid());
        if (checkExists == 0) {
            jobsIndustriesRepository.insertJobsIndustries(jobsIndustries.getIndustries_industryid(),
                    jobsIndustries.getJob_jobid());
        }

    }

    public List<JobsIndustriesDTO> getJobIndustriesByJobId(long jobId) {
        List<Object[]> listIndustries = jobsIndustriesRepository.findIndustriesByJobId(jobId);

        List<JobsIndustriesDTO> list = new ArrayList<>();
        for (Object[] result : listIndustries) {
            System.out.println((Long) result[0]);
            JobsIndustriesDTO a = new JobsIndustriesDTO();
            a.setJob_jobid((Long) result[1]);
            a.setIndustries_industryid((Long) result[0]);
            list.add(a);
        }
        return list;
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

    public List<QuantityJobDTO> quantityJobOfIndustryId() {
        List<Object[]> results = jobsIndustriesRepository.quantityJobOfIndustryId();
        List<QuantityJobDTO> quantityJobDTOs = new ArrayList<>();
        for (Object[] result : results) {
            QuantityJobDTO quantityJobDTO = new QuantityJobDTO();
            quantityJobDTO.setIndustryid((Long) result[0]);
            quantityJobDTO.setIndustryname((String) result[1]);
            quantityJobDTO.setQuantity((Integer) result[2]);
            quantityJobDTOs.add(quantityJobDTO);
        }
        return quantityJobDTOs;
    }
}
