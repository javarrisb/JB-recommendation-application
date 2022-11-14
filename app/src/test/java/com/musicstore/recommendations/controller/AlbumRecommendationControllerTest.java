package com.musicstore.recommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.recommendations.model.AlbumRecommendation;
import com.musicstore.recommendations.repository.AlbumRecommendationRepository;
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

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationController.class)
public class AlbumRecommendationControllerTest {

    @MockBean
    private AlbumRecommendationRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnHttpStatus200andAlbumRecommendationListOnGetAll() throws Exception {
        AlbumRecommendation outputAlbumRecommendation = new AlbumRecommendation(1, 1, 1, true);
        List<AlbumRecommendation> outputRecommendationList = Arrays.asList(outputAlbumRecommendation);
        String outputJson = mapper.writeValueAsString(outputRecommendationList);

        doReturn(outputRecommendationList).when(repo).findAll();

        mockMvc.perform(get("/album-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus200AndGetAlbumRecommendationById() throws Exception {
        Integer id = 1;
        AlbumRecommendation outputAlbumRecommendation = new AlbumRecommendation(1,1,1, false);
        String outputJson = mapper.writeValueAsString(outputAlbumRecommendation);

        doReturn(Optional.of(outputAlbumRecommendation)).when(repo).findById(id);
        mockMvc.perform(get("/album-recommendation/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus201AndPostOnAlbumRecommendation() throws Exception {
        AlbumRecommendation inputAlbumRecommendation = new AlbumRecommendation(1,1,1, true);
        String inputJson = mapper.writeValueAsString(inputAlbumRecommendation);
        AlbumRecommendation outputAlbumRecommendation = new AlbumRecommendation(1,2,2,true);
        String outputJson = mapper.writeValueAsString(outputAlbumRecommendation);

        doReturn(outputAlbumRecommendation).when(repo).save(inputAlbumRecommendation);
        mockMvc.perform(post("/album-recommendation")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus204OnAlbumRecommendationUpdate() throws Exception {
        Integer id = 1;
        AlbumRecommendation inputAlbumRecommendation = new AlbumRecommendation(1,1,1,true);
        String inputJson = mapper.writeValueAsString(inputAlbumRecommendation);

        mockMvc.perform(put("/album-recommendation/{id}", id)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent()) ;
    }

    @Test
    public void shouldReturnHttpStatus204OnAlbumRecommendationDelete() throws Exception {
        mockMvc.perform(delete("/album-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWithHttpStatus422WhenGetAlbumRecommendationByIdNotFound() throws Exception {
        Integer id = 1;

        doReturn(Optional.empty()).when(repo).findById(id);
        mockMvc.perform(get("/album-recommendation/{id}", id))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
