package com.musicstore.recommendations.repository;

import com.musicstore.recommendations.model.ArtistRecommendation;
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
public class ArtistRecommendationRepositoryTest {

    @Autowired
    ArtistRecommendationRepository artistRecommendationRepository;

    @Before
    public void setUp() throws Exception {
        artistRecommendationRepository.deleteAll();
    }
    @Test
    public void getAllArtistRecommendations() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(1);
        artistRecommendation.setUserId(1);
        artistRecommendation.setLiked(true);
        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);

        artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(2);
        artistRecommendation.setUserId(2);
        artistRecommendation.setLiked(false);
        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);

        List<ArtistRecommendation> artistRecommendationList = artistRecommendationRepository.findAll();

        assertEquals(artistRecommendationList.size(), 2);
    }

    @Test
    public void getArtistRecommendation() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(1);
        artistRecommendation.setUserId(1);
        artistRecommendation.setLiked(true);
        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);

        Optional<ArtistRecommendation> artistRecommendation1 = artistRecommendationRepository.findById(artistRecommendation.getId());

    }

    @Test
    public void addArtistRecommendation() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(1);
        artistRecommendation.setUserId(1);
        artistRecommendation.setLiked(true);
        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);
    }

    @Test
    public void updateArtistRecommendation() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(1);
        artistRecommendation.setUserId(1);
        artistRecommendation.setLiked(true);
        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);

        artistRecommendation.setArtistId(3);
        artistRecommendation.setUserId(3);
        artistRecommendation.setLiked(false);

        artistRecommendationRepository.save(artistRecommendation);

        Optional<ArtistRecommendation> artistRecommendation1 = artistRecommendationRepository.findById(artistRecommendation.getId());
    }

    @Test
    public void deleteArtistRecommendation() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(1);
        artistRecommendation.setUserId(1);
        artistRecommendation.setLiked(true);
        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);

        Optional<ArtistRecommendation> artistRecommendation1 = artistRecommendationRepository.findById(artistRecommendation.getId());

        artistRecommendationRepository.deleteById(artistRecommendation.getId());

        artistRecommendation1 = artistRecommendationRepository.findById(artistRecommendation.getId());

        assertFalse(artistRecommendation1.isPresent());
    }
}
