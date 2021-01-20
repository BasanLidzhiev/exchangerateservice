package ru.lidzhiev.exchangerateservice.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void getAllCurrencies() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/?exc_app_id=b81b7ba8b8da4d2fbbbe062831ca171e&gif_app_id=rblojEMNbDdBuJhwoqqM64OevRPAyuUo&currency=USD"))
                .andReturn();
        Assert.assertEquals(302, result.getResponse().getStatus());
    }


}