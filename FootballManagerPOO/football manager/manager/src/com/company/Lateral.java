package com.company;

import java.util.List;

public class Lateral extends Player {

    private int cross;


    // ==== Constructors ====

    public Lateral() {
        super();
        this.cross = 0;
    }

    public Lateral(String name, int spd, int end, int dex, int imp, int hg, int kick, int pass, int cross, List<Team> hist) {
        super(name, spd, end, dex, imp, hg, kick, pass, hist);
        this.cross = cross;
    }

    public Lateral (Goalkeeper g) {
        super(g);
        this.cross = g.getElasticity();
    }


    // ==== Getters ====

    public int getCross() {
        return cross;
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
                        0.05 * this.getCross()));
    }


    // ==== Setters ====

    public void setCross(int cross) {
        this.cross = cross;
    }


    // ==== Equals / ToString ====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Lateral) {
            return super.equals(o) && this.getCross() == ((Lateral) o).getCross();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Position= Lateral"+
                ", name='" + getName() + '\'' +
                ", speed=" + getSpeed() +
                ", endurance=" + getEndurance() +
                ", dexterity=" + getDexterity() +
                ", impulse=" +getImpulse()+
                ", headGame=" + getHeadGame() +
                ", kick=" + getKick() +
                ", pass=" + getPass() +
                ", cross=" + getCross()+
                "\n, habilidade=" + getAbility()+
                "}";
    }
}
