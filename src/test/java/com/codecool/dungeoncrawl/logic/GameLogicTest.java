package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.dao.JdbcDao;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class GameLogicTest {
    private Actor attacker;
    private Actor defender;
    private Cell attackerCell;
    private Cell defenderCell;
    GameLogic logic;

    @BeforeEach
    void setup() {
        attacker = mock(Actor.class);
        defender = mock(Actor.class);
        attackerCell = mock(Cell.class);
        defenderCell = mock(Cell.class);
        JdbcDao jdbcdao = new JdbcDao();
        PlayerDao playerDAO = new PlayerDao(jdbcdao);
        ItemDao itemDAO = new ItemDao(jdbcdao);
        logic = new GameLogic(playerDAO, itemDAO);

        when(attacker.getCell()).thenReturn(attackerCell);
        when(defender.getCell()).thenReturn(defenderCell);
    }

    @Test
    void checkIfDefenderIsDeadThenRemoveItFromMap() {
        when(attacker.getAttackPower()).thenReturn(5);
        when(defender.isDead()).thenReturn(true);

        logic.handleCombat(attacker, defender);
        verify(defenderCell, times(1)).setActor(null);
    }

    @Test
    void checkIfAttackerIsDeadThenRemoveItFromMap() {
        when(defender.getAttackPower()).thenReturn(5);
        when(attacker.isDead()).thenReturn(true);

        logic.handleCombat(attacker, defender);
        verify(attackerCell, times(1)).setActor(null);
    }

    @Test
    void checkIfBothSurvive() {
        when(attacker.getAttackPower()).thenReturn(5);
        when(attacker.isDead()).thenReturn(false);

        when(defender.getAttackPower()).thenReturn(5);
        when(defender.isDead()).thenReturn(false);

        logic.handleCombat(attacker, defender);

        verify(attackerCell, never()).setActor(null);
        verify(defenderCell, never()).setActor(null);
    }
}