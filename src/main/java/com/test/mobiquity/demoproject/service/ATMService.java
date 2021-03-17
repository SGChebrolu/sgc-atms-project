package com.test.mobiquity.demoproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.mobiquity.demoproject.model.Atm;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public interface ATMService {

    ResponseEntity<Map<Integer, Atm>> getAllAtms() throws IOException;
    ResponseEntity<Map<Integer, Atm>> getAtmsPerCity(String city) throws IOException;

}
