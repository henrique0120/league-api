package com.henrique.League_API.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henrique.League_API.dto.SummonerResponse;
import com.henrique.League_API.service.RiotApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/league")
public class SummonerController {

    @Value("${riot.api.key}") // Injeção da chave do application.properties
    private String riotApiKey;

    private final RiotApiService riotApiService;

    public SummonerController(RiotApiService riotApiService) {
        this.riotApiService = riotApiService;
    }

    @GetMapping("/summonerByName")
    public ResponseEntity<?> getSummoner(@RequestParam String gameName, @RequestParam String region,
                                         @RequestParam String tagLine, @RequestParam String puuid) {

        System.out.println("API Key carregada: " + riotApiKey); // Teste para ver se a chave está sendo carregada

        try {

            String riotApiUrl1 = "https://"+region+".api.riotgames.com/riot/account/v1/accounts/by-riot-id/"+gameName+"/"+tagLine+"?api_key=RGAPI-c7234495-73a8-423a-8924-8bee84f01198";

            String riotApiUrl2 = "https://" + tagLine + ".api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid + "?api_key=RGAPI-c7234495-73a8-423a-8924-8bee84f01198";

            String riotApiUrl3 = "https://" + tagLine + ".api.riotgames.com/lol/league/v4/entries/by-puuid/" + puuid + "?api_key=RGAPI-c7234495-73a8-423a-8924-8bee84f01198";

            // Chamadas para a API
            String response1 = riotApiService.getAccountByRiotID(riotApiUrl1);
            String response2 = riotApiService.getAccountLevel(riotApiUrl2);
            String response3 = riotApiService.getRankedStats(riotApiUrl3); // Renomeado para clareza

            // Verificação de resposta vazia
            if (response1 == null || response2 == null || response3 == null ||
                    response1.isEmpty() || response2.isEmpty() || response3.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum dado encontrado para esse Riot ID");
            }

            // Conversão para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root1 = objectMapper.readTree(response1);
            JsonNode root2 = objectMapper.readTree(response2);
            JsonNode root3 = objectMapper.readTree(response3);

            // Extração de dados
            String extractedGameName = root1.has("gameName") ? root1.get("gameName").asText() : "N/A";
            String extractedTagLine = root1.has("tagLine") ? root1.get("tagLine").asText() : "N/A";
            int extractedSummonerLevel = root2.has("summonerLevel") ? root2.get("summonerLevel").asInt() : 0;

            // Acesso ao JSON de ranking
            String extractedQueue = "N/A";
            String extractedTier = "N/A";
            String extractedRank = "N/A";
            int extractedLeaguePoints = 0;
            int extractedWins = 0;
            int extractedLosses = 0;

            if (root3.isArray() && !root3.isEmpty()) { // Certifica-se de que há dados na lista
                JsonNode rankedData = root3.get(0); // Pega o primeiro elemento do array

                extractedQueue = rankedData.has("queueType") ? rankedData.get("queueType").asText() : "N/A";
                extractedTier = rankedData.has("tier") ? rankedData.get("tier").asText() : "N/A";
                extractedRank = rankedData.has("rank") ? rankedData.get("rank").asText() : "N/A";
                extractedLeaguePoints = rankedData.has("leaguePoints") ? rankedData.get("leaguePoints").asInt() : 0;
                extractedWins = rankedData.has("wins") ? rankedData.get("wins").asInt() : 0;
                extractedLosses = rankedData.has("losses") ? rankedData.get("losses").asInt() : 0;

            }

            // Cria o DTO
            SummonerResponse dto = new SummonerResponse(
                    extractedGameName, extractedTagLine, extractedSummonerLevel, extractedQueue ,extractedTier, extractedRank, extractedLeaguePoints, extractedWins, extractedLosses);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao acessar a API da Riot Games: " + e.getMessage());
        }
    }



    @GetMapping("/summonerLevel")
    public ResponseEntity<String> getLevel(@RequestParam String puuid, @RequestParam String tagLine) {
        try {
            String riotApiUrl = "https://" + tagLine + ".api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid + "?api_key=RGAPI-c7234495-73a8-423a-8924-8bee84f01198";
            String response = riotApiService.getAccountByRiotID(riotApiUrl);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao acessar a API da Riot Games");
        }
    }
}