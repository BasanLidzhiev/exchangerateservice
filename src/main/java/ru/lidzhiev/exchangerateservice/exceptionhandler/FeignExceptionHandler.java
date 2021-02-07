package ru.lidzhiev.exchangerateservice.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.BadRequest.class)
    public Map<String, Object> handleFeignStatusException(FeignException e, HttpServletResponse response) throws JSONException, JsonProcessingException {
        response.setStatus(e.status());
        return new ObjectMapper().readValue(new JSONObject(e.contentUTF8()).toString(), HashMap.class);
    }

}