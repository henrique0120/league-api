package com.henrique.League_API.service;

<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henrique.League_API.dto.RiotAccountDto;
import org.springframework.beans.factory.annotation.Value;
=======
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiotApiService {

<<<<<<< HEAD
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
=======
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
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
