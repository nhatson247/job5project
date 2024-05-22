package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fpt.job5project.dto.JobHomeDTO;
import com.fpt.job5project.dto.QuantityJobDTO;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.JobDTO;
import com.fpt.job5project.entity.Job;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.JobMapper;
import com.fpt.job5project.repository.JobRepository;
import com.fpt.job5project.service.IJobReportService;
import com.fpt.job5project.service.IJobService;
import com.fpt.job5project.service.INotificationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobServiceImpl implements IJobService {
    JobRepository jobRepository;
    JobMapper jobMapper;
    IJobReportService ỉIJobReportService;

    @Override
    public JobDTO addJob(JobDTO newJobDTO) {

        Job newJob = new Job();
        newJob = jobMapper.toEntity(newJobDTO);
        return jobMapper.toDTO(jobRepository.save(newJob));
    }

    @Override
    public JobDTO updateJob(long id, JobDTO newJob) {
        Job foundJob = jobRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_NOT_EXIST));
        jobMapper.updateJob(foundJob, newJob);
        return jobMapper.toDTO(jobRepository.save(foundJob));
    }

    @Override
    public List<JobDTO> getAllJob() {
        List<JobDTO> listDTO = new ArrayList<>();
        List<Job> listEntity = jobRepository.findAll();
        if (listEntity.isEmpty()) {
            throw new AppException(ErrorCode.LIST_CANDIDATE_IS_NULL);
        }
        for (Job a : listEntity) {
            listDTO.add(jobMapper.toDTO(a));
        }
        return listDTO;
    }

    public JobDTO getOneJob(long id) {
        Job foundJob = jobRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_NOT_EXIST));
        return jobMapper.toDTO(foundJob);
    }

    @Override
    public List<JobHomeDTO> getTopJobForHome(int numJobs) {
        List<Tuple> results = jobRepository.getTopJobForHome(numJobs);
        List<JobHomeDTO> listJobs = new ArrayList<>();
        for (Tuple tuple : results) {
            JobHomeDTO jobHomeDTO = new JobHomeDTO(
                    tuple.get("jobId", Long.class),
                    tuple.get("jobName", String.class),
                    tuple.get("employerId", Long.class),
                    tuple.get("minSalary", Long.class),
                    tuple.get("maxSalary", Long.class),
                    tuple.get("employerName", String.class),
                    tuple.get("location", String.class));
            listJobs.add(jobHomeDTO);
        }

        return listJobs;
    }

    @Override
    public List<JobDTO> getJobByEmployer(long employerId) {
        List<JobDTO> listDTO = new ArrayList<>();
        List<Job> listEntity = jobRepository.findByEmployerId(employerId);
        for (Job a : listEntity) {
            listDTO.add(jobMapper.toDTO(a));
        }
        return listDTO;
    }

    @Override
    public List<JobDTO> resultSearchJob(long industryId, String searchValue, long minSalary, long maxSalary,
            String location, int experience, int typeJob) {
        List<JobDTO> listDTO = new ArrayList<>();
        List<Job> listEntity = jobRepository.resultSearch(industryId, searchValue, minSalary, maxSalary, location,
                experience, typeJob);
        for (Job a : listEntity) {
            listDTO.add(jobMapper.toDTO(a));
        }
        return listDTO;
    }

    @Override
    public int updateIsExpired(long id) {
        return jobRepository.updateIsExpired(id);
    }

    @Override
    public int updateReup(long id) {
        return jobRepository.updateReup(id);
    }

    @Override
    public int deleteJob(long id) {
        return jobRepository.deleteJob(id);
    }

    @Override
    public void hideJob(long jobId, long reportId) {
        jobRepository.hideJob(jobId);
        ỉIJobReportService.deleteJobReport(reportId);
    }

}
