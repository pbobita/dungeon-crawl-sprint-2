package com.codecool.dungeoncrawl.repository;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.MapLoader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameState {

    public static void savePlayer(Player player) {
        JDBCHandler.runSql("DELETE FROM game;");

        String sql = String.format(
                "INSERT INTO game (map, player_x, player_y, health, attack_power, inventory) " +
                        "VALUES ('default map', %d, %d, %d, %d, '%s');",
                player.getX(),
                player.getY(),
                player.getHealth(),
                player.getAttackPower(),
                player.getInventory()
        );

        JDBCHandler.runSql(sql);
    }

    public static GameMap loadSavedGame() {
        try {
            ResultSet resultSet = JDBCHandler.runSql("SELECT * FROM game LIMIT 1");

            if (resultSet.next()) {
                int x = resultSet.getInt("player_x");
                int y = resultSet.getInt("player_y");
                int health = resultSet.getInt("health");
                int attackPower = resultSet.getInt("attack_power");
                String inventory = resultSet.getString("inventory");

                GameMap map = MapLoader.loadMap(true);

                Player player = new Player(map.getCell(x, y));
                player.setHealth(health);
                player.setAttackPower(attackPower);
                player.setInventoryFromString(inventory);

                map.setPlayer(player);
                map.getCell(x, y).setActor(player);

                System.out.println("Player successfully loaded.");
                return map;
            } else {
                System.out.println("No saved game found.");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error loading saved game: " + e.getMessage());
            return null;
        }
    }


}
