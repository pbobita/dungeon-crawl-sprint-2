package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.ItemFactory;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.MapSerializer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerDao {
    private final JdbcDao jdbcDao;
    private final ItemFactory itemFactory;

    public PlayerDao(JdbcDao jdbcDao, ItemFactory itemFactory) {
        this.jdbcDao = jdbcDao;
        this.itemFactory = itemFactory;
    }

    public void savePlayer(GameMap map) {
        Player player = map.getPlayer();
        String insertSql = """
        INSERT INTO game (name, map, player_x, player_y, health, attack_power, inventory, saved_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

        jdbcDao.executeUpdate(insertSql, stmt -> {
            stmt.setString(1, player.getName());
            stmt.setString(2, MapSerializer.serializeMap(map));
            stmt.setInt(3, player.getX());
            stmt.setInt(4, player.getY());
            stmt.setInt(5, player.getHealth());
            stmt.setInt(6, player.getAttackPower());
            stmt.setString(7, player.getInventory().toSaveString());
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
        });
    }

    public Optional<GameMap> loadLatestPlayerByName(String playerName) {
        String querySql = """
        SELECT * FROM game
        WHERE name = ?
        ORDER BY saved_at DESC
        LIMIT 1
    """;

        final GameMap[] mapHolder = new GameMap[1];

        jdbcDao.executeQuery(querySql, stmt -> stmt.setString(1, playerName), rs -> {
            if (rs.next()) {
                String mapData = rs.getString("map");
                GameMap map = MapLoader.loadMap(mapData, true);
                createPlayerFromResultSet(map, rs);
                mapHolder[0] = map;
            }
        });

        return Optional.ofNullable(mapHolder[0]);
    }

    public List<String> getAllSavedPlayerNames() {
        List<String> names = new ArrayList<>();
        jdbcDao.executeQuery("SELECT DISTINCT name FROM game", stmt -> {}, rs -> {
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        });
        return names;
    }

    private void createPlayerFromResultSet(GameMap map, ResultSet rs) throws SQLException {
        int x = rs.getInt("player_x");
        int y = rs.getInt("player_y");
        int health = rs.getInt("health");
        int attackPower = rs.getInt("attack_power");
        String inventoryData = rs.getString("inventory");
        String name = rs.getString("name");

        Player player = new Player(map.getCell(x, y), 5, 5);

        player.setHealth(health);
        player.setAttackPower(attackPower);
        player.getInventory().fromSaveString(inventoryData, itemFactory);
        player.setName(name);

        map.setPlayer(player);
        map.getCell(x, y).setActor(player);
    }

}