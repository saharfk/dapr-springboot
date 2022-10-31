//package com.service.controller;
//
//import org.json.JSONObject;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.Duration;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class beanconfig {
//
//    private static final HttpClient httpClient = HttpClient.newBuilder()
//            .version(HttpClient.Version.HTTP_2)
//            .connectTimeout(Duration.ofSeconds(10))
//            .build();
//
//    private static final String DAPR_HTTP_PORT = System.getenv().getOrDefault("DAPR_HTTP_PORT", "3001");
//
//    @PostConstruct
//    public static void config() throws InterruptedException, IOException {
//        String dapr_url = "http://localhost:" + DAPR_HTTP_PORT + "/orders";
//        System.out.println(dapr_url);
//        int orderId = 1;
//        JSONObject obj = new JSONObject();
//        obj.put("orderId", orderId);
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
//                .uri(URI.create(dapr_url))
//                .header("Content-Type", "application/json")
//                .header("dapr-app-id", "order-processor")
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println("Order passed: " + orderId);
////        TimeUnit.MILLISECONDS.sleep(4000);
//
//    }
//}
