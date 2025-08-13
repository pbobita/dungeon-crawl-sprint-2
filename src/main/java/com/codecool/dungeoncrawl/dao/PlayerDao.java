package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.ItemFactory;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.MapLoader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PlayerDao {
    private final JdbcDao jdbcDao;
    private final ItemFactory itemFactory;

    public PlayerDao(JdbcDao jdbcDao, ItemFactory itemFactory) {
        this.jdbcDao = jdbcDao;
        this.itemFactory = itemFactory;
    }

    public void savePlayer(Player player) {
        String deleteSql = "DELETE FROM game";
        String insertSql = "INSERT INTO game (name, map, player_x, player_y, health, attack_power, inventory) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcDao.executeUpdate(deleteSql, stmt -> {});
        jdbcDao.executeUpdate(insertSql, stmt -> {
            stmt.setString(1, player.getName());
            stmt.setString(2, "default map");
            stmt.setInt(3, player.getX());
            stmt.setInt(4, player.getY());
            stmt.setInt(5, player.getHealth());
            stmt.setInt(6, player.getAttackPower());
            stmt.setString(7, player.getInventory().toSaveString());
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

    private void createPlayerFromResultSet(GameMap map, ResultSet rs) throws SQLException {
        int x = rs.getInt("player_x");
        int y = rs.getInt("player_y");
        int health = rs.getInt("health");
        int attackPower = rs.getInt("attack_power");
        String inventoryData = rs.getString("inventory");
        String name = rs.getString("name");

        Player player = new Player(map.getCell(x, y));

        player.setHealth(health);
        player.setAttackPower(attackPower);
        player.getInventory().fromSaveString(inventoryData, itemFactory);
        player.setName(name);

        map.setPlayer(player);
        map.getCell(x, y).setActor(player);
    }

}