package com.company;

import java.util.List;

public class Midfielder extends Player{

    int recovery;

    // ==== Constructors ====

    public Midfielder() {
        super();
        this.recovery = 0;
    }

    public Midfielder(String name, int spd, int end, int dex, int imp, int hg, int kick, int pass, int rec, List<Team> hist) {
        super(name, spd, end, dex, imp, hg, kick, pass, hist);
        this.recovery = rec;
    }

    public Midfielder (Goalkeeper g) {
        super(g);
        this.recovery = g.getElasticity();
    }


    // ==== Getters ====

    public int getRecovery() {
        return recovery;
    }

    @Override
    public int getAbility() {
        return (int) (100 * (
                0.05 * this.getSpeed() +
                        0.05 * this.getEndurance() +
                        0.2 * this.getDexterity() +
                        0.05 * this.getImpulse() +
                        0.2 * this.getHeadGame() +
                        0.05 * this.getKick() +
                        0.05 * this.getRecovery()));
    }


    // ==== Setters ====

    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }


    // ==== Equals / ToString ====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Midfielder) {
            return super.equals(o) && this.getRecovery() == ((Midfielder) o).getRecovery();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Position= Midfielder"+
                ", name='" + getName() + '\'' +
                ", speed=" + getSpeed() +
                ", endurance=" + getEndurance() +
                ", dexterity=" + getDexterity() +
                ", impulse=" +getImpulse()+
                ", headGame=" + getHeadGame() +
                ", kick=" + getKick() +
                ", pass=" + getPass() +
                ", recovery=" + getRecovery()+
                "\n, habilidade=" + getAbility()+
                "}";
    }
}
