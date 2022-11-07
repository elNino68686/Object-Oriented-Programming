package com.company;

import java.util.List;

public class Forward extends Player {

    // ==== Constructors ====

    public Forward () {
        super();
    }

    public Forward(String name, int spd, int end, int dex, int imp, int hg, int kick, int pass, List<Team> hist) {
        super(name, spd, end, dex, imp, hg, kick, pass, hist);
    }

    public Forward (Forward f) {
        super(f);
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
                "Position= Forward"+
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

