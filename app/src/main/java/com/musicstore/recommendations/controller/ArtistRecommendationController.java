package com.musicstore.recommendations.controller;

import com.musicstore.recommendations.model.ArtistRecommendation;
import com.musicstore.recommendations.repository.ArtistRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist-recommendation")
public class ArtistRecommendationController {
    @Autowired
    ArtistRecommendationRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtistRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation getArtistRecommendationById(@PathVariable int id) {
        Optional<ArtistRecommendation> returnValue = repo.findById(id);
        if (returnValue.isPresent() == false) {
            throw new IllegalArgumentException("No artist with id" + id);
        }
        return returnValue.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation addArtistRecommendation(@RequestBody ArtistRecommendation artistRecommendation) { return  repo.save(artistRecommendation);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistRecommendation(@RequestBody ArtistRecommendation artistRecommendation) { repo.save(artistRecommendation);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRecommendation(@PathVariable int id) {repo.deleteById(id);}
}
