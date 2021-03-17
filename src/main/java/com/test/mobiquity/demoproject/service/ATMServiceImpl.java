package com.test.mobiquity.demoproject.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.mobiquity.demoproject.model.Atm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ATMServiceImpl implements ATMService {

    @Value("${api.url}")
    String apiUrl;

    @Override
    public ResponseEntity<Map<Integer, Atm>> getAllAtms() throws IOException {

        AtomicInteger atomicInteger = new AtomicInteger();
        List<Atm> atmList = getAllATMs();
        return new ResponseEntity<>(atmList.stream().collect(Collectors.toMap(s -> atomicInteger.getAndIncrement(), Function.identity())), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Integer, Atm>> getAtmsPerCity(String city) throws IOException {

        AtomicInteger atomicInteger = new AtomicInteger();
        List<Atm> atmList = getAllATMs();


            return new ResponseEntity<>(atmList.stream()
                    .filter(x -> x.getAddress().getCity().equalsIgnoreCase(city))
                    .collect(Collectors.toMap(s -> atomicInteger.getAndIncrement(), Function.identity())), HttpStatus.OK);
    }

    private List<Atm> getAllATMs() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> restResponse = restTemplate.getForEntity(apiUrl, String.class);

        if (restResponse.getStatusCode().is2xxSuccessful()) {
            String toBeParsed = restResponse.getBody().substring(6);
            //Removed garbage data from response body
            return objectMapper.readValue(toBeParsed, new TypeReference<List<Atm>>() {
            });
        } else {
            return Collections.emptyList();
        }
    }
}