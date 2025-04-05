package com.henrique.League_API.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henrique.League_API.dto.RiotAccountDto;
import com.henrique.League_API.dto.SummonerResponse;
import com.henrique.League_API.service.RiotApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/league")
public class SummonerController {

    private final RiotApiService riotApiService;

    public SummonerController(RiotApiService riotApiService) {
        this.riotApiService = riotApiService;
    }

    @GetMapping("/summoner")
    public ResponseEntity<?> getSummoner(@RequestParam String region,
                                         @RequestParam String gameName,
                                         @RequestParam String tagLine) {
        try {
            // Passo 1: Buscar puuid com base no Riot ID
            RiotAccountDto account = riotApiService.getAccountInfo(region, gameName, tagLine);

            // Passo 2: Definir plataforma com base no region (ex: br1 -> br1, lan -> la1)
            String platformRegion = region;

            // Passo 3: Buscar nível e ranked
            String summonerJson = riotApiService.getAccountLevel(platformRegion, account.getPuuid());
            String rankedJson = riotApiService.getRankedStats(platformRegion, account.getPuuid());

            // Parsear respostas
            ObjectMapper mapper = new ObjectMapper();
            JsonNode summonerNode = mapper.readTree(summonerJson);
            JsonNode rankedArray = mapper.readTree(rankedJson);

            int level = summonerNode.path("summonerLevel").asInt();

            List<SummonerResponse> responses = new ArrayList<>();

            if (rankedArray.isArray()) {
                for (JsonNode ranked : rankedArray) {
                    responses.add(new SummonerResponse(
                            account.getGameName(),
                            account.getTagLine(),
                            level,
                            ranked.path("queueType").asText("N/A"),
                            ranked.path("tier").asText("N/A"),
                            ranked.path("rank").asText("N/A"),
                            ranked.path("leaguePoints").asInt(0),
                            ranked.path("wins").asInt(0),
                            ranked.path("losses").asInt(0)
                    ));
                }
            }

            return ResponseEntity.ok(responses);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao consultar dados do jogador: " + e.getMessage());
        }
    }
}