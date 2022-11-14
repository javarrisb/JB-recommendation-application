package com.musicstore.recommendations.repository;

import com.musicstore.recommendations.model.TrackRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TrackRecommendationRepositoryTest {

    @Autowired
    TrackRecommendationRepository trackRecommendationRepository;

    @Before
    public void setUp() throws Exception {
        trackRecommendationRepository.deleteAll();
    }

    @Test
    public void getAllTrackRecommendations() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(1);
        trackRecommendation.setUserId(1);
        trackRecommendation.setLiked(true);
        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);

        trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(2);
        trackRecommendation.setUserId(2);
        trackRecommendation.setLiked(false);
        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);

        List<TrackRecommendation> trackRecommendationList = trackRecommendationRepository.findAll();

        assertEquals(trackRecommendationList.size(), 2);
    }

    @Test
    public void getTrackRecommendation() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(1);
        trackRecommendation.setUserId(1);
        trackRecommendation.setLiked(true);
        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);

        Optional<TrackRecommendation> trackRecommendation1 = trackRecommendationRepository.findById(trackRecommendation.getId());

    }

    @Test
    public void addTrackRecommendation() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(1);
        trackRecommendation.setUserId(1);
        trackRecommendation.setLiked(true);
        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);
    }

    @Test
    public void updateTrackRecommendation() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(1);
        trackRecommendation.setUserId(1);
        trackRecommendation.setLiked(true);
        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);

        trackRecommendation.setTrackId(5);
        trackRecommendation.setUserId(5);
        trackRecommendation.setLiked(false);

        trackRecommendationRepository.save(trackRecommendation);

        Optional<TrackRecommendation> trackRecommendation1 = trackRecommendationRepository.findById(trackRecommendation.getId());
    }

    @Test
    public void deleteTrackRecommendation() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(1);
        trackRecommendation.setUserId(1);
        trackRecommendation.setLiked(true);
        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);

        Optional<TrackRecommendation> trackRecommendation1 = trackRecommendationRepository.findById(trackRecommendation.getId());

        trackRecommendationRepository.deleteById(trackRecommendation.getId());

        trackRecommendation1 = trackRecommendationRepository.findById(trackRecommendation.getId());

        assertFalse(trackRecommendation1.isPresent());
    }
}
