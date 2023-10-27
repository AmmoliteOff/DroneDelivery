package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.DroneSendRequest;
import com.hackathon.dronedelivery.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public DroneSendRequest save(DroneSendRequest request) {
        return requestRepository.save(request);
    }

    public List<DroneSendRequest> findAll(){
        return requestRepository.findAll();
    }
}
