package com.xloop.resourceloop.createJob.Service;

import com.xloop.resourceloop.createJob.Model.FavouriteJob;
import com.xloop.resourceloop.createJob.Model.Job;
import com.xloop.resourceloop.createJob.Repository.FavouriteJobRepository;
import com.xloop.resourceloop.createJob.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavouriteJobServiceImpl implements FavouriteJobService{

    @Autowired
    private FavouriteJobRepository favouriteJobRepository;

    @Autowired
    private JobRepository jobRepository;
    @Override
    public FavouriteJob addFavouriteJob(FavouriteJob favouriteJob) {
        Long userId = favouriteJob.getUserId();
        Long jobId = favouriteJob.getJobId();

        FavouriteJob existingFavoriteJob = favouriteJobRepository.findByUserIdAndJobId(userId, jobId);
        if (existingFavoriteJob != null) {
            return existingFavoriteJob;
        }


        return favouriteJobRepository.save(favouriteJob);
    }

    @Override
    public void removeFavouriteJob(Long userId, Long jobId) {
        FavouriteJob favouriteJob = favouriteJobRepository.findByUserIdAndJobId(userId, jobId);
        favouriteJobRepository.delete(favouriteJob);
    }

    @Override
    public List<Job> getFavouriteJobsByUserId(Long userId) {
        List<FavouriteJob> favouriteJobs = favouriteJobRepository.findByUserId(userId);
        List<Job> favouriteJobList = new ArrayList<>();

        for(FavouriteJob favouriteJob:favouriteJobs){
                Optional<Job> optionalJob = jobRepository.findById(favouriteJob.getJobId());
                optionalJob.ifPresent(favouriteJobList::add);
        }
        return favouriteJobList;
    }


}
