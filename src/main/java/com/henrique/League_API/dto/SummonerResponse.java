package com.henrique.League_API.dto;

public class SummonerResponse {


    private String gameName;
    private String tagLine;
    private int summonerLevel;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;


    public SummonerResponse(String gameName, String tagLine, int summonerLevel, String queueType, String tier, String rank, int leaguePoints, int wins, int losses) {
<<<<<<< HEAD

=======
        this.gameName = gameName;
        this.tagLine = tagLine;
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
        this.summonerLevel = summonerLevel;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
<<<<<<< HEAD
        this.gameName = gameName;
        this.tagLine = tagLine;
=======
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}