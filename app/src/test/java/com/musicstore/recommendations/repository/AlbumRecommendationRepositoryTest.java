package com.musicstore.recommendations.repository;

import com.musicstore.recommendations.model.AlbumRecommendation;
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
public class AlbumRecommendationRepositoryTest {

    @Autowired
    AlbumRecommendationRepository albumRecommendationRepository;

    @Before
    public void setUp() throws Exception {
        albumRecommendationRepository.deleteAll();
    }

    @Test
    public void getAllAlbumRecommendations() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(1);
        albumRecommendation.setUserId(1);
        albumRecommendation.setLiked(true);
        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);

        albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(2);
        albumRecommendation.setUserId(2);
        albumRecommendation.setLiked(false);
        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);

        List<AlbumRecommendation> albumRecommendationList = albumRecommendationRepository.findAll();

        assertEquals(albumRecommendationList.size(), 2);
    }

    @Test
    public void getAlbumRecommendation() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(1);
        albumRecommendation.setUserId(1);
        albumRecommendation.setLiked(true);
        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);

        Optional<AlbumRecommendation> albumRecommendation1 = albumRecommendationRepository.findById(albumRecommendation.getId());

    }

    @Test
    public void addAlbumRecommendation() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(1);
        albumRecommendation.setUserId(1);
        albumRecommendation.setLiked(true);
        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);
    }

    @Test
    public void updateAlbumRecommendation() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(1);
        albumRecommendation.setUserId(1);
        albumRecommendation.setLiked(true);
        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);

        albumRecommendation.setAlbumId(3);
        albumRecommendation.setUserId(3);
        albumRecommendation.setLiked(false);

        albumRecommendationRepository.save(albumRecommendation);

        Optional<AlbumRecommendation> albumRecommendation1 = albumRecommendationRepository.findById(albumRecommendation.getId());
    }

    @Test
    public void deleteAlbumRecommendation() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(1);
        albumRecommendation.setUserId(1);
        albumRecommendation.setLiked(true);
        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);

        Optional<AlbumRecommendation> albumRecommendation1 = albumRecommendationRepository.findById(albumRecommendation.getId());

        albumRecommendationRepository.deleteById(albumRecommendation.getId());

        albumRecommendation1 = albumRecommendationRepository.findById(albumRecommendation.getId());

        assertFalse(albumRecommendation1.isPresent());
    }
}
