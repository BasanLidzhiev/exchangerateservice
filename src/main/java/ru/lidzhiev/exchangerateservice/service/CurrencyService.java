package ru.lidzhiev.exchangerateservice.service;

import feign.FeignException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.lidzhiev.exchangerateservice.response.Response;
import ru.lidzhiev.exchangerateservice.client.CurrencyClient;
import ru.lidzhiev.exchangerateservice.client.GifClient;
import ru.lidzhiev.exchangerateservice.entity.Currency;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Service
public class CurrencyService {
    private final CurrencyClient currencyClient;
    private final GifClient gifClient;
    private final DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    private final Random random = new Random();

    public CurrencyService(CurrencyClient currencyClient, GifClient gifClient) {
        this.currencyClient = currencyClient;
        this.gifClient = gifClient;
    }

    public Response getGifUrl(String exc_app_id, String gif_app_id, String currency) throws JSONException, FeignException {
            Currency todayRate = currencyClient.getHistoricalRate(getToday(), exc_app_id, "RUB");
            Currency yesterdayRate = currencyClient.getHistoricalRate(getYesterday(), exc_app_id, "RUB");
            JSONObject object;
            if (todayRate.getRates().get(currency) > yesterdayRate.getRates().get(currency)) {
                object = new JSONObject(gifClient.getGif(gif_app_id, "rich", 25).getBody());
            } else {
                object = new JSONObject(gifClient.getGif(gif_app_id, "broke", 25).getBody());
            }
            return new Response(object.getJSONArray("data").getJSONObject(random.nextInt(25)).
                    getJSONObject("images").getJSONObject("original").getString("mp4"), "", 200);

    }

    private String getToday() {
        LocalDateTime today = LocalDateTime.now();
        return format1.format(today);
    }

    private String getYesterday() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return format1.format(yesterday);
    }
}
