package com.musicstore.recommendations.controller;

import com.musicstore.recommendations.model.AlbumRecommendation;
import com.musicstore.recommendations.repository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album-recommendation")
public class AlbumRecommendationController {
    @Autowired
    AlbumRecommendationRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbumRecommendations() { return repo.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation getAlbumRecommendationById(@PathVariable Integer id) {
        Optional<AlbumRecommendation> returnValue = repo.findById(id);
        if (returnValue.isPresent() == false) {
            throw new IllegalArgumentException("No recommendation with id" + id);
        }
        return returnValue.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation addNewAlbumRecommendation(@RequestBody AlbumRecommendation albumRecommendation) {
        return repo.save(albumRecommendation);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumRecommendation(@PathVariable Integer id, @RequestBody AlbumRecommendation albumRecommendation) {
        repo.save(albumRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRecommendation(@PathVariable Integer id) { repo.deleteById(id);}


}
