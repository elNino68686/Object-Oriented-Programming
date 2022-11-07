package com.company;

import java.util.Date;

public abstract class Match {

    private Team teamA;
    private Team teamB;
    private int teamAScore;
    private int teamBScore;
    private Date date;



    // ==== Constructor ====

    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }


    // ==== Getters ====

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public int getTeamAScore(){
        return teamAScore;
    }

    public int getTeamBScore(){
        return teamBScore;
    }

    public Date getDate() {
        return date;
    }


    // ==== Setters ====

    public void setStartingPlayer(Player p, Team t) throws ChangePlayerStateExeption {
        if (t.has(p) && (t.equals(teamA) || t.equals(teamB)) && t.getNumberOfStartingPlayers() < 11) {
            p.setState(new Starting());
        } else throw new ChangePlayerStateExeption();
    }

    public void setSubstitutePlayer(Player p, Team t) throws ChangePlayerStateExeption {
        if (t.has(p) && (t.equals(teamA) || t.equals(teamB))) {
            p.setState(new Substitute());
        } else throw new ChangePlayerStateExeption();
    }

    public void setOutOfGamePlayer(Player p, Team t) throws ChangePlayerStateExeption {
        if (t.has(p) && (t.equals(teamA) || t.equals(teamB))) {
            p.setState(new OutOfGame());
        } else throw new ChangePlayerStateExeption();
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    public void setDate(Date date) {
        this.date = date;
    }


// ==== Methods ===

    public abstract void startMatch();
}





