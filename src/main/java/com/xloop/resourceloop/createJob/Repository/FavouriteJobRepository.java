package com.xloop.resourceloop.createJob.Repository;

import com.xloop.resourceloop.createJob.Model.FavouriteJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteJobRepository extends JpaRepository<FavouriteJob,Long> {

    FavouriteJob findByUserIdAndJobId(Long userId, Long jobId);

    List<FavouriteJob> findByUserId(Long userId);



}
