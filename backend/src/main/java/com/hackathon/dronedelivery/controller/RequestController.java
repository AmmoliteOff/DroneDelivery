package com.hackathon.dronedelivery.controller;

import com.hackathon.dronedelivery.model.Request;
import com.hackathon.dronedelivery.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/requests")
    public ResponseEntity<List<Request>> getOrders() {
        return ResponseEntity.ok(requestService.findAll());
    }

}
