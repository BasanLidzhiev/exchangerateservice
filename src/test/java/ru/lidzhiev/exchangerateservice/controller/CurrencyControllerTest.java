package ru.lidzhiev.exchangerateservice.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void getGifSuccess() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/?exc_app_id=49db3a50dbe246e0ba14dac085273174&gif_app_id=rblojEMNbDdBuJhwoqqM64OevRPAyuUo&currency=USD"))
                .andReturn();
        Assert.assertEquals(302, result.getResponse().getStatus());
        Assert.assertTrue(result.getResponse().getRedirectedUrl().contains("giphy.com/media"));
    }

    @Test
    void appIdError() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/?exc_app_id=49db3a50dbe246e0ba14ac085273174&gif_app_id=rblojEMNbDdBuJhwoqqM64OevRPAyuUo&currency=USD")).andReturn();
        Assert.assertTrue(result.getResponse().getContentAsString().contains("Invalid App ID provided"));
        Assert.assertEquals(401, result.getResponse().getStatus());

    }

    @Test
    void gifAppIdError() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/?exc_app_id=49db3a50dbe246e0ba14dac085273174&gif_app_id=rblojEMNbDduJhwoqqM64OevRPAyuUo&currency=USD"))
                .andReturn();
        Assert.assertEquals(403, result.getResponse().getStatus());
        Assert.assertTrue(result.getResponse().getContentAsString().contains("Invalid authentication credentials"));
    }

    @Test
    void currencyError() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/?exc_app_id=49db3a50dbe246e0ba14dac085273174&gif_app_id=rblojEMNbDdBuJhwoqqM64OevRPAyuUo&currency=D"))
                .andReturn();
        Assert.assertEquals(500, result.getResponse().getStatus());
        Assert.assertEquals("Invalid currency code", result.getResponse().getContentAsString());
    }




}