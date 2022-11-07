package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private String name;
    private int speed;
    private int endurance;
    private int dexterity;
    private int impulse;
    private int headGame;
    private int kick;
    private int pass;
    private final List<Team> history;
    private PlayerState state;


    // ==== Constructors ====

    public Player() {
        this.name = "New Player";
        this.speed = 0;
        this.endurance = 0;
        this.dexterity = 0;
        this.impulse = 0;
        this.headGame = 0;
        this.kick = 0;
        this.pass = 0;
        this.history = new ArrayList<>();
    }

    public Player(String name, int spd, int end, int dex, int imp, int hg, int kick, int pass, List<Team> hist) {
        this.name = name;
        this.speed = spd;
        this.endurance = end;
        this.dexterity = dex;
        this.impulse = imp;
        this.headGame = hg;
        this.kick = kick;
        this.pass = pass;
        this.history = hist;
    }

    public Player(Player p) {
        this.name = p.getName();
        this.speed = p.getSpeed();
        this.endurance = p.getEndurance();
        this.dexterity = p.getDexterity();
        this.impulse = p.getImpulse();
        this.headGame = p.getHeadGame();
        this.kick = p.getKick();
        this.pass = p.getPass();
        this.history = p.getHistory();
    }


    // ==== Getters ====

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getImpulse() {
        return impulse;
    }

    public int getHeadGame() {
        return headGame;
    }

    public int getKick() {
        return kick;
    }

    public int getPass() {
        return pass;
    }

    public PlayerState getState() { return state;}

    public List<Team> getHistory() {
        List<Team> hist = new ArrayList<>();
        for (Team t: history) {
            hist.add(new Team(t));
        }
        return hist;
    }

    public abstract int getAbility();


    // ==== Setters ====

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setImpulse(int impulse) {
        this.impulse = impulse;
    }

    public void setHeadGame(int headGame) {
        this.headGame = headGame;
    }

    public void setKick(int kick) {
        this.kick = kick;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }


    // ==== Methods ====

    public void addTeamToHistory(Team t) {
        this.history.add(t);
    }


    // ==== Equals / ToString ====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        Player p = (Player) o;
        return (this.name.equals(p.getName()) &&
                this.speed == p.getSpeed() &&
                this.endurance == p.getEndurance() &&
                this.dexterity == p.getDexterity() &&
                this.impulse == p.getImpulse() &&
                this.headGame == p.getHeadGame() &&
                this.kick == p.getImpulse() &&
                this.pass == p.getPass());
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", endurance=" + endurance +
                ", dexterity=" + dexterity +
                ", impulse=" +impulse+
                ", strength=" + impulse +
                ", headGame=" + headGame +
                ", kick=" + kick +
                ", pass=" + pass +
                "}";
    }


}
