package com.musicstore.recommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.recommendations.model.LabelRecommendation;
import com.musicstore.recommendations.repository.LabelRecommendationRepository;
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
@WebMvcTest(LabelRecommendationController.class)
public class LabelRecommendationControllerTest {

    @MockBean
    private LabelRecommendationRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnHttpStatus200andLabelRecommendationListOnGetAll() throws Exception {
        LabelRecommendation outputLabelRecommendation = new LabelRecommendation(2, 2, 2, true);
        List<LabelRecommendation> outputRecommendationList = Arrays.asList(outputLabelRecommendation);
        String outputJson = mapper.writeValueAsString(outputRecommendationList);

        doReturn(outputRecommendationList).when(repo).findAll();

        mockMvc.perform(get("/label-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus200AndGetLabelRecommendationById() throws Exception {
        Integer id = 1;
        LabelRecommendation outputLabelRecommendation = new LabelRecommendation(1,1,1, false);
        String outputJson = mapper.writeValueAsString(outputLabelRecommendation);

        doReturn(Optional.of(outputLabelRecommendation)).when(repo).findById(id);
        mockMvc.perform(get("/label-recommendation/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus201AndPostOnLabelRecommendation() throws Exception {
        LabelRecommendation inputLabelRecommendation = new LabelRecommendation(1,1,1, true);
        String inputJson = mapper.writeValueAsString(inputLabelRecommendation);
        LabelRecommendation outputLabelRecommendation = new LabelRecommendation(1,2,2,true);
        String outputJson = mapper.writeValueAsString(outputLabelRecommendation);

        doReturn(outputLabelRecommendation).when(repo).save(inputLabelRecommendation);
        mockMvc.perform(post("/label-recommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus204OnLabelRecommendationUpdate() throws Exception {
        Integer id = 1;
        LabelRecommendation inputLabelRecommendation = new LabelRecommendation(1,1,1,true);
        String inputJson = mapper.writeValueAsString(inputLabelRecommendation);

        mockMvc.perform(put("/label-recommendation/{id}", id)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent()) ;
    }

    @Test
    public void shouldReturnHttpStatus204OnLabelRecommendationDelete() throws Exception {
        mockMvc.perform(delete("/label-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWithHttpStatus422WhenGetLabelRecommendationByIdNotFound() throws Exception {
        Integer id = 1;

        doReturn(Optional.empty()).when(repo).findById(id);
        mockMvc.perform(get("/label-recommendation/{id}", id))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}