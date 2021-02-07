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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
//        Проверяем, что ответ перенаправляет на гифку.
        Assert.assertTrue(result.getModelAndView().getViewName().contains("giphy.com/media"));
    }

    @Test
    void getAllCurrencies() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/application_error"))
                .andExpect(forwardedUrl(""));
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Assert.assertEquals(302, result.getResponse().getStatus());
    }

    @Test
    void appIdError() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/?exc_app_id=49db3a50dbe246e0ba14dac085273174&gif_app_id=rblojEMNbDdBuJhwoqqM64OevRPAyuUo&currency=USD"))
                .andReturn();
        Assert.assertEquals(302, result.getResponse().getStatus());
//        Проверяем, что ответ перенаправляет на гифку.
        Assert.assertTrue(result.getModelAndView().getViewName().contains("giphy.com/media"));
    }

    @Test
    void gifAppIdError() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/?exc_app_id=49db3a50dbe246e0ba14dac085273174&gif_app_id=rblojEMNbDdBuJhwoqqM64OevRyuUo&currency=USD"))
                .andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals("invalid_app_id", result.getResponse().getErrorMessage());
    }

    @Test
    void currencyError() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/?exc_app_id=49db3a50dbe246e0ba14dac085273174&gif_app_id=rblojEMNbDdBuJhwoqqM64OevRPAyuUo&currency=USD"))
                .andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals("invalid_app_id", result.getResponse().getErrorMessage());
    }




}