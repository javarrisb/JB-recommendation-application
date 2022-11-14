package com.musicstore.recommendations.repository;

import com.musicstore.recommendations.model.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRecommendationRepository extends JpaRepository<TrackRecommendation, Integer> {
}
