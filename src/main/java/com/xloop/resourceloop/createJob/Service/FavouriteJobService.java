package com.xloop.resourceloop.createJob.Service;

import com.xloop.resourceloop.createJob.Model.FavouriteJob;
import com.xloop.resourceloop.createJob.Model.Job;

import java.util.List;

public interface FavouriteJobService {
    FavouriteJob addFavouriteJob(FavouriteJob favouriteJob);
    void removeFavouriteJob(Long userId, Long jobId);
    List<Job>  getFavouriteJobsByUserId(Long userId);
}
