package com.service.controller;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@RestController
public class CheckoutServiceController {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final String DAPR_HTTP_PORT = System.getenv().getOrDefault("DAPR_HTTP_PORT", "3001");

    @PostMapping(path = "/checkoutOrders")
    public String processOrders(@RequestBody entity lkplk) {
        System.out.println("I got my message back: " + lkplk);
        return "CID" + lkplk;
    }

    @PostMapping(path = "/sendOrder")
    public void config(@RequestBody entity hi) throws InterruptedException, IOException {
        String dapr_url = "http://localhost:" + "3001" + "/orders";
        System.out.println(dapr_url);
        System.out.println("Order passed: " + hi);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(hi.toString()))
                .uri(URI.create(dapr_url))
                .header("Content-Type", "application/json")
                .header("dapr-app-id", "order-processor")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


    }
}