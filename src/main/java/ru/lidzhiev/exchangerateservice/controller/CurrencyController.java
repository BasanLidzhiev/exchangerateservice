package ru.lidzhiev.exchangerateservice.controller;

import feign.FeignException;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lidzhiev.exchangerateservice.service.CurrencyService;

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
    public String getGif(@RequestParam String exc_app_id, @RequestParam String gif_app_id, @RequestParam String currency) throws JSONException {
        try {
            return "redirect:" + service.getGifUrl(exc_app_id, gif_app_id, currency).getUrl();
        } catch (FeignException exception) {
            data.put("exception", exception);
            return "redirect:/application_error";
        }
    }

    @GetMapping("/application_error")
    @ResponseBody
    public ResponseEntity<String> applicationError() {
//        FeignException exception = (FeignException) data.get("exception");
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.resolve(exception.status()));
        return new ResponseEntity<>("body", HttpStatus.OK);
    }
}
