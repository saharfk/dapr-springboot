package com.service.controller;

import com.service.model.Order;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderProcessingServiceController {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final String DAPR_HTTP_PORT = System.getenv().getOrDefault("DAPR_HTTP_PORT", "3003");


    @PostMapping(path = "/orders")
    public void processOrders(@RequestBody entity body) throws InterruptedException, IOException {
        String dapr_url = "http://192.168.25.90:" + "3003" + "/checkoutOrders";
        System.out.println("sending order to "+ dapr_url+ "     and the body is"+body);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .uri(URI.create(dapr_url))
                .header("Content-Type", "application/json")
                .header("dapr-app-id", "checkout")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("sent?");
//        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
