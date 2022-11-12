package com.musicstore.recommendations.controller;

import com.musicstore.recommendations.model.LabelRecommendation;
import com.musicstore.recommendations.repository.LabelRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/label-recommendation")
public class LabelRecommendationController {
    @Autowired
    LabelRecommendationRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getLabelRecommendations() {return repo.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation getLabelRecommendationById(@PathVariable int id) {
        Optional<LabelRecommendation> returnValue = repo.findById(id);
        if (returnValue.isPresent() == false) {
            throw new IllegalArgumentException("No label with id" + id);
        }
        return returnValue.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation addNewLabelRecommendation(@RequestBody LabelRecommendation labelRecommendation) {return repo.save(labelRecommendation);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelRecommendation(@PathVariable Integer id, @RequestBody LabelRecommendation labelRecommendation) {
        repo.save(labelRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRecommendation(@PathVariable Integer id) { repo.deleteById(id);}
}

