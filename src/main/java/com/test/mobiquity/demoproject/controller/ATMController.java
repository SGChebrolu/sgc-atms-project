package com.test.mobiquity.demoproject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.mobiquity.demoproject.model.Atm;
import com.test.mobiquity.demoproject.service.ATMServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("atms")
public class ATMController {

    @Autowired
    ATMServiceImpl atmService;

    @GetMapping("all")
    public ResponseEntity<Map<Integer, Atm>> getAllAvailableAtms() throws IOException {
        return atmService.getAllAtms();
    }

    @GetMapping("all/{city}")
    public ResponseEntity<Map<Integer, Atm>> getAllAvailableAtmsPerCity(@PathVariable("city") String city) throws IOException {
        return atmService.getAtmsPerCity(city);
    }
}