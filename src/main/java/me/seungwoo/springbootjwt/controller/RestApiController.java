package me.seungwoo.springbootjwt.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by Leo.
 * User: sonseungwoo
 * Date: 2019-02-18
 * Time: 21:57
 */
@RestController
public class RestApiController {

    @Value("${obt.applicationId}")
    private String applicationId;

    @Value("${obt.restApiToken}")
    private String restApiToken;

    @GetMapping("/obt/api")
    public JSONObject test() {
        URI uri = URI.create("http://localhost:8080/obt");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-Obt-Application-Id", applicationId);
        headers.set("X-Obt-Rest-Api-Token", restApiToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            return new JSONObject("{\"result\":" + response.getBody() + "}");
        } catch (Exception e) {
            throw new IllegalArgumentException("Rest 호출중 문제가 발생했습니다.");
        }
    }
}
