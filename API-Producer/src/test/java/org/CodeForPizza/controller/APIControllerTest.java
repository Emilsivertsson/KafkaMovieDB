package org.CodeForPizza.controller;

import java.util.ArrayList;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.producer.KafkaProducer;
import org.CodeForPizza.service.movieImpl.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {APIController.class})
@ExtendWith(SpringExtension.class)
class APIControllerTest {

    @Autowired
    private APIController aPIController;

    @MockBean
    private KafkaProducer kafkaProducer;

    @MockBean
    private MovieService movieService;

    /**
     * Method under test: {@link APIController#deleteMovie(long)}
     */
    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(movieService).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/movie/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie deleted successfully"));
    }

    /**
     * Method under test: {@link APIController#deleteMovie(long)}
     */
    @Test
    void testDeleteMovie2() throws Exception {
        doNothing().when(movieService).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/movie/delete/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie deleted successfully"));
    }

    /**
     * Method under test: {@link APIController#getAllMovies()}
     */
    @Test
    void testGetAllMovies() throws Exception {
        when(movieService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/movie/all");
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link APIController#getAllMovies()}
     */
    @Test
    void testGetAllMovies2() throws Exception {
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

    /**
     * Method under test: {@link APIController#getMovieById(long)}
     */
    @Test
    void testGetMovieById() throws Exception {
        when(movieService.findById(Mockito.<Long>any())).thenReturn(new MovieDTO("Dr", "Year"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/movie/{id}", 1L);
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"title\":\"Dr\",\"year\":\"Year\"}"));
    }

    /**
     * Method under test: {@link APIController#publish(MovieDTO)}
     */
    @Test
    void testPublish() throws Exception {
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

    /**
     * Method under test: {@link APIController#publishApi(MovieDTO)}
     */
    @Test
    void testPublishApi() throws Exception {
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

    /**
     * Method under test: {@link APIController#updateMovie(long, MovieDTO)}
     */
    @Test
    void testUpdateMovie() throws Exception {
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

}

