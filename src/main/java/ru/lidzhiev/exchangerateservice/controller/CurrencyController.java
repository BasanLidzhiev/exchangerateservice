package ru.lidzhiev.exchangerateservice.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.lidzhiev.exchangerateservice.client.CurrencyClient;
import ru.lidzhiev.exchangerateservice.client.GifClient;
import ru.lidzhiev.exchangerateservice.entity.Currency;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@RestController
public class CurrencyController {
    private final CurrencyClient currencyClient;
    private final GifClient gifClient;

    private final DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    private final Random random = new Random();

    public CurrencyController(CurrencyClient currencyClient, GifClient gifClient) {
        this.currencyClient = currencyClient;
        this.gifClient = gifClient;
    }


    @GetMapping
    public ModelAndView getGif(@RequestParam String exc_app_id, @RequestParam String gif_app_id, @RequestParam String base) throws JSONException {
        Currency todayRate = currencyClient.getHistoricalRate(getToday(), exc_app_id, base);
        Currency yesterdayRate = currencyClient.getHistoricalRate(getYesterday(), exc_app_id, base);
        JSONObject object;
        if (todayRate.getRates().get(base) > yesterdayRate.getRates().get(base)) {
            object = new JSONObject(gifClient.getGif(gif_app_id, "rich", 25).getBody());
        } else {
            object = new JSONObject(gifClient.getGif(gif_app_id, "broke", 25).getBody());
        }
        return new ModelAndView("redirect:" + object.getJSONArray("data").getJSONObject(random.nextInt(25)).
                getJSONObject("images").getJSONObject("original").getString("mp4"));

    }

    private String getToday() {
        LocalDateTime today = LocalDateTime.now().minusDays(1);
        return format1.format(today);
    }

    private String getYesterday() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(2);
        return format1.format(yesterday);
    }
    
}
