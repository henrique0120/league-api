package com.henrique.League_API.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henrique.League_API.dto.RiotAccountDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiotApiService {

    @Value("${riot.api.key}")
    private String riotApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public RiotAccountDto getAccountInfo(String region, String gameName, String tagLine) throws JsonProcessingException {
        String url = "https://" + getAccountRouting(region) + ".api.riotgames.com/riot/account/v1/accounts/by-riot-id/"
                + gameName + "/" + tagLine + "?api_key=" + riotApiKey;

        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response);

        String puuid = root.path("puuid").asText();
        String gameNameResp = root.path("gameName").asText();
        String tagResp = root.path("tagLine").asText();

        return new RiotAccountDto(puuid, gameNameResp, tagResp);
    }

    public String getAccountLevel(String region, String puuid) {
        String url = "https://" + region + ".api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid
                + "?api_key=" + riotApiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getRankedStats(String region, String puuid) {
        String url = "https://" + region + ".api.riotgames.com/lol/league/v4/entries/by-puuid/" + puuid
                + "?api_key=" + riotApiKey;
        return restTemplate.getForObject(url, String.class);
    }

    private String getAccountRouting(String region) {
        switch (region) {
            case "br1":
            case "na1":
            case "la1":
            case "la2":
                return "americas";
            case "euw1":
            case "eun1":
            case "tr1":
            case "ru":
                return "europe";
            case "kr":
            case "jp1":
                return "asia";
            default:
                return "americas"; // fallback
        }
    }
}
