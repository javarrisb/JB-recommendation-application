package com.musicstore.recommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.recommendations.model.TrackRecommendation;
import com.musicstore.recommendations.repository.TrackRecommendationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackRecommendationController.class)
public class TrackRecommendationControllerTest {
    @MockBean
    private TrackRecommendationRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnHttpStatus200andTrackRecommendationListOnGetAll() throws Exception {
        TrackRecommendation outputTrackRecommendation = new TrackRecommendation(1, 1, 1, true);
        List<TrackRecommendation> outputRecommendationList = Arrays.asList(outputTrackRecommendation);
        String outputJson = mapper.writeValueAsString(outputRecommendationList);

        doReturn(outputRecommendationList).when(repo).findAll();

        mockMvc.perform(get("/track-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus200AndGetTrackRecommendationById() throws Exception {
        Integer id = 1;
        TrackRecommendation outputTrackRecommendation = new TrackRecommendation(1,1,1, false);
        String outputJson = mapper.writeValueAsString(outputTrackRecommendation);

        doReturn(Optional.of(outputTrackRecommendation)).when(repo).findById(id);
        mockMvc.perform(get("/track-recommendation/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus201AndPostOnTrackRecommendation() throws Exception {
        TrackRecommendation inputTrackRecommendation = new TrackRecommendation(1,1,1, true);
        String inputJson = mapper.writeValueAsString(inputTrackRecommendation);
        TrackRecommendation outputTrackRecommendation = new TrackRecommendation(1,2,2,true);
        String outputJson = mapper.writeValueAsString(outputTrackRecommendation);

        doReturn(outputTrackRecommendation).when(repo).save(inputTrackRecommendation);
        mockMvc.perform(post("/track-recommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus204OnTrackRecommendationUpdate() throws Exception {
        Integer id = 1;
        TrackRecommendation inputTrackRecommendation = new TrackRecommendation(1,1,1,true);
        String inputJson = mapper.writeValueAsString(inputTrackRecommendation);

        mockMvc.perform(put("/track-recommendation/{id}", id)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent()) ;
    }

    @Test
    public void shouldReturnHttpStatus204OnTrackRecommendationDelete() throws Exception {
        mockMvc.perform(delete("/track-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWithHttpStatus422WhenGetTrackRecommendationByIdNotFound() throws Exception {
        Integer id = 1;

        doReturn(Optional.empty()).when(repo).findById(id);
        mockMvc.perform(get("/track-recommendation/{id}", id))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}