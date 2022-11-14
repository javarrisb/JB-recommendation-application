package com.musicstore.recommendations.repository;


import com.musicstore.recommendations.model.LabelRecommendation;
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
public class LabelRecommendationRepositoryTest {

    @Autowired
    LabelRecommendationRepository labelRecommendationRepository;

    @Before
    public void setUp() throws Exception {
        labelRecommendationRepository.deleteAll();
    }

    @Test
    public void getAllLabelRecommendations() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(1);
        labelRecommendation.setUserId(1);
        labelRecommendation.setLiked(true);
        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);

        labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(2);
        labelRecommendation.setUserId(2);
        labelRecommendation.setLiked(false);
        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);

        List<LabelRecommendation> labelRecommendationList = labelRecommendationRepository.findAll();

        assertEquals(labelRecommendationList.size(), 2);
    }

    @Test
    public void getLabelRecommendation() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(1);
        labelRecommendation.setUserId(1);
        labelRecommendation.setLiked(true);
        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);

        Optional<LabelRecommendation> labelRecommendation1 = labelRecommendationRepository.findById(labelRecommendation.getId());

    }

    @Test
    public void addLabelRecommendation() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(1);
        labelRecommendation.setUserId(1);
        labelRecommendation.setLiked(true);
        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);
    }

    @Test
    public void updateLabelRecommendation() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(1);
        labelRecommendation.setUserId(1);
        labelRecommendation.setLiked(true);
        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);

        labelRecommendation.setLabelId(4);
        labelRecommendation.setUserId(4);
        labelRecommendation.setLiked(false);

        labelRecommendationRepository.save(labelRecommendation);

        Optional<LabelRecommendation> labelRecommendation1 = labelRecommendationRepository.findById(labelRecommendation.getId());
    }

    @Test
    public void deleteLabelRecommendation() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(1);
        labelRecommendation.setUserId(1);
        labelRecommendation.setLiked(true);
        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);

        Optional<LabelRecommendation> labelRecommendation1 = labelRecommendationRepository.findById(labelRecommendation.getId());

        labelRecommendationRepository.deleteById(labelRecommendation.getId());

        labelRecommendation1 = labelRecommendationRepository.findById(labelRecommendation.getId());

        assertFalse(labelRecommendation1.isPresent());
    }
}
