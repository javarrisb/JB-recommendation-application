package com.musicstore.recommendations.controller;

import com.musicstore.recommendations.model.TrackRecommendation;
import com.musicstore.recommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/track-recommendation")
public class TrackRecommendationController {
    @Autowired
    TrackRecommendationRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getTrackRecommendations() { return repo.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation getTrackRecommendationById(@PathVariable Integer id) {
        Optional<TrackRecommendation> returnAmount = repo.findById(id);
        if (returnAmount.isPresent() == false) {
            throw new IllegalArgumentException("No track with id" + id);
        }
        return returnAmount.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation addNewTrackRecommendation(@RequestBody TrackRecommendation trackRecommendation) {return  repo.save(trackRecommendation);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRecommendation(@PathVariable Integer id, @RequestBody TrackRecommendation trackRecommendation) {
        repo.save(trackRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable Integer id) { repo.deleteById(id);}


}
