package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.Request;
import com.hackathon.dronedelivery.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public List<Request> findAll(){
        return requestRepository.findAll();
    }
}
