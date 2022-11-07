package com.company;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player p, Player p1) {

        if (p.getAbility() > p1.getAbility())
            return 1;
        else if (p.getAbility() < p1.getAbility())
            return -1;
        else
            return 0;

    }
}