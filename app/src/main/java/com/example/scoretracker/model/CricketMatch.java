package com.example.scoretracker.model;

import java.io.Serializable;

public class CricketMatch implements Serializable {
    private String team1;
    private String team2;
    private String currentinnings;//Team batting in the inning
    private int targetscore;
    private int currentscore;
    private int overs;//overs i.e 4 overs done
    private int totalovers;
    private int wickets;

    public CricketMatch(String team1,String team2,int targetscore,String currentinnings,int currentscore,int overs,int totalovers, int wickets){
        this.team1 = team1;
        this.team2 = team2;
        this.targetscore = targetscore;
        this.currentinnings = currentinnings;
        this.currentscore = currentscore;
        this.overs = overs;
        this.totalovers = totalovers;
        this.wickets = wickets;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getTargetscore() {
        return targetscore;
    }

    public String getCurrentinnings() {
        return currentinnings;
    }

    public int getCurrentscore() {
        return currentscore;
    }

    public int getOvers() {
        return overs;
    }

    public int getTotalovers() {
        return totalovers;
    }

    public int getWickets() { return wickets; }
}
