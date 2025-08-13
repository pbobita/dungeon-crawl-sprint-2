package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.actors.Actor;

public class CombatService {
    public void handleCombat(Actor attacker, Actor defender) {
        defender.gainDamage(attacker.getAttackPower());
        if (defender.isDead()) {
            defender.getCell().setActor(null);
        } else {
            attacker.gainDamage(defender.getAttackPower());
            if (attacker.isDead()) {
                attacker.getCell().setActor(null);
            }
        }
    }

}
