package com.creditcard.creditcardapp.controller;

import com.creditcard.creditcardapp.entities.CreditCardBean;
import com.creditcard.creditcardapp.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Before running this test comment out the save in the run method of the application
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardService service;

    @Test
    public void save_InvalidCustomerName_400() throws Exception {

        String inputJson = "{\"customerName\":\"M\",\"creditCardNumber\":79927398713,\"cardLimit\":50}";

        mockMvc.perform(post("/test/add")
                .content(inputJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).save(any(CreditCardBean.class));
    }

    @Test
    public void save_InvalidCreditCardNumber_400() throws Exception {

        String inputJson = "{\"customerName\":\"Meera\",\"creditCardNumber\":79927398710,\"cardLimit\":50}";

        mockMvc.perform(post("/test/add")
                .content(inputJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).save(any(CreditCardBean.class));
    }

    @Test
    public void save_validCreditCardNumber_201() throws Exception {

        String inputJson = "{\"customerName\":\"Meera\",\"creditCardNumber\":79927398713,\"cardLimit\":50}";

        mockMvc.perform(post("/test/add")
                .content(inputJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(service, times(1)).save(any(CreditCardBean.class));
    }

    @Test
    public void getAll_Details_200() throws Exception {

        String inputJson = "{\"customerName\":\"Meera\",\"creditCardNumber\":79927398713,\"cardLimit\":50}";

        mockMvc.perform(post("/test/add")
                .content(inputJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/test/getAll")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).findAll();
    }
}
