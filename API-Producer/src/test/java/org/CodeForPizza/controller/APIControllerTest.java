package org.CodeForPizza.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {APIController.class})
@ExtendWith(SpringExtension.class)
class APIControllerTest {
    /*
    @Autowired
    private APIController aPIController;

    @MockBean
    private KafkaProducer kafkaProducer;

    @Test
    void testPublish_Success() throws Exception {
        doNothing().when(kafkaProducer).sendMessage(Mockito.<String>any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/v1/movie/save")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Message sent to Topic"));
    }

    @Test
    void testPublish_Fail() throws Exception {
        Mockito.doThrow(new RuntimeException("Simulated Kafka Error")).when(kafkaProducer).sendMessage(Mockito.<String>any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/v1/movie/save")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        MockMvcBuilders.standaloneSetup(aPIController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Error sending message to Topic"));
    }

     */
}

