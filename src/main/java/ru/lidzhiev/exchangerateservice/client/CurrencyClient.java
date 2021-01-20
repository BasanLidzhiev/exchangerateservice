package ru.lidzhiev.exchangerateservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lidzhiev.exchangerateservice.entity.Currency;

@FeignClient(url = "https://openexchangerates.org/api/historical", name = "User")
public interface CurrencyClient {

    @GetMapping("/{date}.json")
    public Currency getHistoricalRate(@PathVariable String date, @RequestParam String app_id, @RequestParam String base);
}
