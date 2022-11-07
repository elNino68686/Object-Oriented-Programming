package com.company;

public class QuickMatch extends Match {

    public QuickMatch(Team teamA, Team teamB) {
        super(teamA, teamB);
    }

    @Override
    public void startMatch() {
        for (Player p: getTeamA().getPlayers()) {
            if (!(p.getState() instanceof Starting)) {
                p.setState(new Substitute());
            }
        }
    }


}
