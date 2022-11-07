package com.company;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private List<Player> players;
    private int winCount;
    private int drawCount;
    private int defeatCount;
    private int scoredGoalsCount;
    private int receivedGoalsCount;
    private int points;
    private int matchesPlayed;


    // ==== Constructors ====

    public Team() {
        this.name = "New team";
        this.players = new ArrayList<>();
        this.winCount = 0;
        this.drawCount = 0;
        this.defeatCount = 0;
        this.scoredGoalsCount = 0;
        this.receivedGoalsCount = 0;
        this.points = 0;
        this.matchesPlayed = 0;
    }

    public Team(String name){
        this.name = name;
        this.players = new ArrayList<>();
        this.winCount = 0;
        this.drawCount = 0;
        this.defeatCount = 0;
        this.scoredGoalsCount = 0;
        this.receivedGoalsCount = 0;
        this.points = 0;
        this.matchesPlayed = 0;
    }

    public Team(Team t) {
        this.name = t.getName();
        this.players = t.getPlayers();
        this.winCount = t.getWinCount();
        this.drawCount = t.drawCount;
        this.defeatCount = t.defeatCount;
        this.scoredGoalsCount = t.scoredGoalsCount;
        this.receivedGoalsCount = t.receivedGoalsCount;
        this.points = t.points;
        this.matchesPlayed = t.matchesPlayed;
    }


    // ==== Getters ====

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getDefeatCount() {
        return defeatCount;
    }

    public int getScoredGoalsCount() {
        return scoredGoalsCount;
    }

    public int getReceivedGoalsCount() {
        return receivedGoalsCount;
    }

    public int getPoints() {
        return points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getNumberOfStartingPlayers() {
        int count = 0;
        for (Player p: players) {
            if (p.getState() instanceof Starting) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfSubstitutePlayers() {
        int count = 0;
        for (Player p: players) {
            if (p.getState() instanceof Substitute) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfOutOfGamePlayers() {
        int count = 0;
        for (Player p: players) {
            if (p.getState() instanceof OutOfGame) {
                count++;
            }
        }
        return count;
    }


    // ==== Setters ====

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }

    public void setDefeatCount(int defeatCount) {
        this.defeatCount = defeatCount;
    }

    public void setScoredGoalsCount(int scoredGoalsCount) {
        this.scoredGoalsCount = scoredGoalsCount;
    }

    public void setReceivedGoalsCount(int receivedGoalsCount) {
        this.receivedGoalsCount = receivedGoalsCount;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    // ==== Methods ====

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public int totalAbility() {
        int totalHability = 0;
        for (Player player: getPlayers()) {
            totalHability = totalHability + player.getAbility();
        }
        return totalHability;
    }

    public List<Player> removeAndReturnAllPlayers() {
        List<Player> list = new ArrayList<>();
        for (Player p: players) {
            list.add(p);
            players.remove(p);
        }
        return list;
    }

    public boolean has(Player p) {
        return this.getPlayers().contains(p);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }


    // ==== Equals / ToString ====

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", players=" + players +
                '}';
    }

    //public abstract int totalAbility();





    //public abstract Team clone();

}
