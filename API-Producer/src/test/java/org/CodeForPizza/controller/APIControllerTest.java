package org.CodeForPizza.controller;

import java.util.ArrayList;
import java.util.List;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.producer.KafkaProducer;
import org.CodeForPizza.service.movieImpl.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {APIController.class})
@ExtendWith(SpringExtension.class)
class APIControllerTest {

    @Autowired
    private APIController aPIController;

    @MockBean
    private KafkaProducer kafkaProducer;

    @MockBean
    private MovieService movieService;


    @Test
    void testDeleteMovieSuccess() throws Exception {
        doNothing().when(movieService).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/movie/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie deleted successfully"));
    }

    @Test
    void testDeleteMovieFail() throws Exception {
        doNothing().when(movieService).deleteById(Mockito.<Long>any());
        doThrow(new RuntimeException("Error deleting movie")).when(movieService).deleteById(Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/movie/delete/{id}", 999);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Error deleting movie"));
    }

    @Test
    void testGetAllMoviesSuccess() throws Exception {
        ArrayList<MovieDTO> movieDTOList = new ArrayList<>();
        movieDTOList.add(new MovieDTO("Dr", "get all movies"));
        when(movieService.findAll()).thenReturn(movieDTOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/movie/all");
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("[{\"id\":null,\"title\":\"Dr\",\"year\":\"get all movies\"}]"));
    }

    @Test
    void testGetAllMoviesFail() {
        when(movieService.findAll()).thenThrow(new RuntimeException("Simulated exception"));
        ResponseEntity<List<MovieDTO>> response = aPIController.getAllMovies();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody());
        verify(movieService, times(1)).findAll();
    }

    @Test
    void testGetMovieByIdSuccess() throws Exception {
        when(movieService.findById(Mockito.<Long>any())).thenReturn(new MovieDTO("Dr", "Year"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/movie/{id}", 1L);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"title\":\"Dr\",\"year\":\"Year\"}"));
    }

    @Test
    void testGetMovieByIdFail() throws Exception {
        when(movieService.findById(Mockito.<Long>any())).thenReturn(new MovieDTO("Dr", "Year"));
        doThrow(new RuntimeException(String.valueOf(new MovieDTO()))).when(movieService).findById(Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/movie/{id}", 999);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"title\":null,\"year\":null}"));
    }

    @Test
    void testPublishSuccess() throws Exception {
        doNothing().when(kafkaProducer).sendMessage(Mockito.any());
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Dr");
        movieDTO.setYear("Year");
        String content = (new com.fasterxml.jackson.databind.ObjectMapper()).writeValueAsString(movieDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/movie/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Message sent to Topic"));
    }

    @Test
    void testPublishFail() throws Exception {
        doNothing().when(kafkaProducer).sendMessage(Mockito.any());
        doThrow(new RuntimeException("Error sending message to Topic")).when(kafkaProducer).sendMessage(Mockito.any());
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Dr");
        movieDTO.setYear("Year");
        String content = (new com.fasterxml.jackson.databind.ObjectMapper()).writeValueAsString(movieDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/movie/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Error sending message to Topic"));
    }

    @Test
    void testPublishApiSuccess() throws Exception {
        when(movieService.save(Mockito.<MovieDTO>any())).thenReturn(new MovieDTO("Dr", "Year"));

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Dr");
        movieDTO.setYear("Year");
        String content = (new com.fasterxml.jackson.databind.ObjectMapper()).writeValueAsString(movieDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/movie/saveapi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"title\":\"Dr\",\"year\":\"Year\"}"));
    }

    @Test
    void testPublishApiFail() throws Exception {
        when(movieService.save(Mockito.<MovieDTO>any())).thenReturn(new MovieDTO());
        doThrow(new RuntimeException(String.valueOf(new MovieDTO()))).when(movieService).save(Mockito.any());
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Dr");
        movieDTO.setYear("Year");
        String content = (new com.fasterxml.jackson.databind.ObjectMapper()).writeValueAsString(movieDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/movie/saveapi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void testUpdateMovieSuccess() throws Exception {
        when(movieService.update(Mockito.<Long>any(), Mockito.<MovieDTO>any())).thenReturn(new MovieDTO("Dr", "Year"));
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Dr");
        movieDTO.setYear("Year");
        String content = (new com.fasterxml.jackson.databind.ObjectMapper()).writeValueAsString(movieDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/movie/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"title\":\"Dr\",\"year\":\"Year\"}"));
    }

    @Test //TODO
    void testUpdateMovieFail() throws Exception {
        when(movieService.update(Mockito.<Long>any(), Mockito.<MovieDTO>any())).thenReturn(new MovieDTO("Dr", "Year"));
        doThrow(new RuntimeException(String.valueOf(new MovieDTO()))).when(movieService).update(Mockito.any(), Mockito.any());
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Dr");
        movieDTO.setYear("Year");
        String content = (new com.fasterxml.jackson.databind.ObjectMapper()).writeValueAsString(movieDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/movie/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"title\":null,\"year\":null}"));
    }

}

