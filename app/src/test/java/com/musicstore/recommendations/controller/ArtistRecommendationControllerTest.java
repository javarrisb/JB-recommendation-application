package com.musicstore.recommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.recommendations.model.ArtistRecommendation;;
import com.musicstore.recommendations.repository.ArtistRecommendationRepository;
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
@WebMvcTest(ArtistRecommendationController.class)
public class ArtistRecommendationControllerTest {

    @MockBean
    private ArtistRecommendationRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnHttpStatus200andArtistRecommendationListOnGetAll() throws Exception {
        ArtistRecommendation outputArtistRecommendation = new ArtistRecommendation(1, 1, 1, true);
        List<ArtistRecommendation> outputRecommendationList = Arrays.asList(outputArtistRecommendation);
        String outputJson = mapper.writeValueAsString(outputRecommendationList);

        doReturn(outputRecommendationList).when(repo).findAll();

        mockMvc.perform(get("/artist-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus200AndGetArtistRecommendationById() throws Exception {
        Integer id = 1;
        ArtistRecommendation outputArtistRecommendation = new ArtistRecommendation(1,1,1, false);
        String outputJson = mapper.writeValueAsString(outputArtistRecommendation);

        doReturn(Optional.of(outputArtistRecommendation)).when(repo).findById(id);
        mockMvc.perform(get("/artist-recommendation/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus201AndPostOnArtistRecommendation() throws Exception {
        ArtistRecommendation inputArtistRecommendation = new ArtistRecommendation(1,1,1, true);
        String inputJson = mapper.writeValueAsString(inputArtistRecommendation);
        ArtistRecommendation outputArtistRecommendation = new ArtistRecommendation(1,2,2,true);
        String outputJson = mapper.writeValueAsString(outputArtistRecommendation);

        doReturn(outputArtistRecommendation).when(repo).save(inputArtistRecommendation);
        mockMvc.perform(post("/artist-recommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus204OnArtistRecommendationUpdate() throws Exception {
        Integer id = 1;
        ArtistRecommendation inputArtistRecommendation = new ArtistRecommendation(1,1,1,true);
        String inputJson = mapper.writeValueAsString(inputArtistRecommendation);

        mockMvc.perform(put("/artist-recommendation/{id}", id)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent()) ;
    }

    @Test
    public void shouldReturnHttpStatus204OnArtistRecommendationDelete() throws Exception {
        mockMvc.perform(delete("/artist-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWithHttpStatus422WhenGetArtistRecommendationByIdNotFound() throws Exception {
        Integer id = 1;

        doReturn(Optional.empty()).when(repo).findById(id);
        mockMvc.perform(get("/artist-recommendation/{id}", id))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}