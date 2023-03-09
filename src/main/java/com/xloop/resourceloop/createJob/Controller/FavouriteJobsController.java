package com.xloop.resourceloop.createJob.Controller;

import com.xloop.resourceloop.createJob.Model.FavouriteJob;
import com.xloop.resourceloop.createJob.Model.Job;
import com.xloop.resourceloop.createJob.Service.FavouriteJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourites")
public class FavouriteJobsController {

    @Autowired
    private FavouriteJobService favouriteJobService;

    @PostMapping("/")
    public ResponseEntity<FavouriteJob> addFavouriteJob(@RequestBody FavouriteJob favouriteJob) {
        FavouriteJob newFavouriteJob = favouriteJobService.addFavouriteJob(favouriteJob);
        return ResponseEntity.ok(newFavouriteJob);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Job>> getFavouriteJobsByUserId(@PathVariable Long userId) {
        List<Job> favouriteJobs = favouriteJobService.getFavouriteJobsByUserId(userId);
        return ResponseEntity.ok(favouriteJobs);
    }

    @DeleteMapping("/remove/{userId}/{jobId}")
    public ResponseEntity<Void> removeFavouriteJob(@PathVariable Long userId, @PathVariable Long jobId) {
        favouriteJobService.removeFavouriteJob(userId, jobId);
        return ResponseEntity.noContent().build();
    }
}
