package com.mprog.reputation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reputation")
@RequiredArgsConstructor
public class ReputationController {

    @Value("${mprog.negative.host}")
    private String negativeServiceHost;

    private final RestTemplate restTemplate;

    @GetMapping
    public List<Reputation> getReputation() {
        log.info("getReputation: {}", LocalDateTime.now());
        String url = String.format("http://%s/api/negative", negativeServiceHost);
        Negative[] negatives = this.restTemplate.getForObject(url, Negative[].class);
        return List.of(
                new Reputation(1, negatives[0]),
                new Reputation(1, negatives[1])
        );
    }

    record Negative(int id, int reliability, String text) {
    }

    record Reputation(int id, Negative negative) {
    }
}
