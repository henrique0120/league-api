package com.henrique.League_API.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiotApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccountByRiotID(String apiUrl) {
        // Realiza a requisição HTTP para a API da Riot Games
        return restTemplate.getForObject(apiUrl, String.class);
    }

    public String getAccountLevel(String apiURL){
        return restTemplate.getForObject(apiURL, String.class);
    }

    public String getRankedStats(String apiURL){
        return restTemplate.getForObject(apiURL, String.class);
    }

}