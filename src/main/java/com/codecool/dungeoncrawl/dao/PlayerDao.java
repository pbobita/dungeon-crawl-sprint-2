package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.MapLoader;

import java.util.Optional;

public class PlayerDao {
    private final JdbcDao jdbcDao;

    public PlayerDao(JdbcDao jdbcDao) {
        this.jdbcDao = jdbcDao;
    }

    public void savePlayer(Player player) {
        String deleteSql = "DELETE FROM game";
        String insertSql = "INSERT INTO game (map, player_x, player_y, health, attack_power, inventory) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcDao.executeUpdate(deleteSql, stmt -> {});
        jdbcDao.executeUpdate(insertSql, stmt -> {
            stmt.setString(1, "default map");
            stmt.setInt(2, player.getX());
            stmt.setInt(3, player.getY());
            stmt.setInt(4, player.getHealth());
            stmt.setInt(5, player.getAttackPower());
            stmt.setString(6, player.getInventory());
        });
    }

    public Optional<GameMap> loadPlayer() {
        String querySql = "SELECT * FROM game LIMIT 1";
        final GameMap[] mapHolder = new GameMap[1];

        jdbcDao.executeQuery(querySql, stmt -> {}, rs -> {
            if (rs.next()) {
                GameMap map = MapLoader.loadMap(true);
                createPlayerFromResultSet(map, rs);
                mapHolder[0] = map;
            }
        });

        return Optional.ofNullable(mapHolder[0]);
    }

    private void createPlayerFromResultSet(GameMap map, java.sql.ResultSet rs) throws java.sql.SQLException {
        int x = rs.getInt("player_x");
        int y = rs.getInt("player_y");
        int health = rs.getInt("health");
        int attackPower = rs.getInt("attack_power");
        String inventory = rs.getString("inventory");

        Player player = new Player(map.getCell(x, y));
        player.setHealth(health);
        player.setAttackPower(attackPower);
        player.setInventoryFromString(inventory);

        map.setPlayer(player);
        map.getCell(x, y).setActor(player);
    }
}