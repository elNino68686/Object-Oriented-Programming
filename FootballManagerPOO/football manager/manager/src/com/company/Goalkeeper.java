package com.company;

import java.util.List;

public class Goalkeeper extends Player{

    private int elasticity;


    // ==== Constructors ====

    public Goalkeeper() {
        super();
        this.elasticity = 0;
    }

    public Goalkeeper(String name, int spd, int end, int dex, int imp, int hg, int kick, int pass, int elast, List<Team> hist) {
        super(name, spd, end, dex, imp, hg, kick, pass, hist);
        this.elasticity = elast;
    }

    public Goalkeeper (Goalkeeper g) {
        super(g);
        this.elasticity = g.getElasticity();
    }


    // ==== Getters ====

    public int getElasticity() {
        return elasticity;
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
                0.05 * this.getElasticity()));
    }


    // ==== Setters ====

    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }


    // ==== Equals / ToString ====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Goalkeeper) {
            return super.equals(o) && this.getElasticity() == ((Goalkeeper) o).getElasticity();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Position= Goal Keeper"+
                ", name='" + getName() + '\'' +
                ", speed=" + getSpeed() +
                ", endurance=" + getEndurance() +
                ", dexterity=" + getDexterity() +
                ", impulse=" +getImpulse()+
                ", headGame=" + getHeadGame() +
                ", kick=" + getKick() +
                ", pass=" + getPass() +
                ", elasticity=" + getElasticity()+
                "\n, habilidade=" + getAbility()+
                "}";
    }
}
