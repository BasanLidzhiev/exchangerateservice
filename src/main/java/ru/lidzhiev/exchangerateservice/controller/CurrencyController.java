package ru.lidzhiev.exchangerateservice.controller;

import feign.FeignException;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lidzhiev.exchangerateservice.service.CurrencyService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CurrencyController {
    private final CurrencyService service;
    private Map<String, Object> data = new HashMap<>();

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> getGif(@RequestParam String exc_app_id, @RequestParam String gif_app_id, @RequestParam String currency,
                         HttpServletResponse response) throws JSONException, IOException {
        try {
            response.sendRedirect(service.getGif(exc_app_id, gif_app_id, currency).getUrl());
            return null;
        } catch (FeignException exception) {
            data.put("exception", exception);
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.resolve(exception.status()));
        } catch (NullPointerException exception) {
            return new ResponseEntity<>("Invalid currency code", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
