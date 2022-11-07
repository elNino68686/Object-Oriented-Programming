package com.company;

import java.util.List;

public class Defender extends Player {

    // ==== Constructors ====

    public Defender () {
        super();
    }

    public Defender(String name, int spd, int end, int dex, int imp, int hg, int kick, int pass, List<Team> hist) {
        super(name, spd, end, dex, imp, hg, kick, pass, hist);
    }

    public Defender (Defender d) {
        super(d);
    }


    // ==== Getters ====

    @Override
    public int getAbility() {
        return (int) (100 * (
                0.05 * this.getSpeed() +
                0.05 * this.getEndurance() +
                0.2 * this.getDexterity() +
                0.05 * this.getImpulse() +
                0.2 * this.getHeadGame() +
                0.05 * this.getKick()));
    }


    // ==== ToString ====

    @Override
    public String toString() {
        return "Player{" +
                "Position= Defender"+
                ", name='" + getName() + '\'' +
                ", speed=" + getSpeed() +
                ", endurance=" + getEndurance() +
                ", dexterity=" + getDexterity() +
                ", impulse=" +getImpulse()+
                ", headGame=" + getHeadGame() +
                ", kick=" + getKick() +
                ", pass=" + getPass() +
                "\n, habilidade=" + getAbility()+
                "}";
    }
}