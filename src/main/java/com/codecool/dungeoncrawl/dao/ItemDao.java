package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.items.DbItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemDao {
    private final JdbcDao jdbcDao;
    private static volatile ItemDao INSTANCE;

    public ItemDao(JdbcDao jdbcDao) {
        this.jdbcDao = Objects.requireNonNull(jdbcDao, "jdbcDao must not be null");
    }

    public static void init(ItemDao dao) {
        INSTANCE = dao;
    }

    public static Integer getIntStat(String itemName, String columnName) {
        ItemDao dao = INSTANCE;
        if (dao == null) {
            throw new IllegalStateException("ItemDao not initialized. Call ItemDao.init(...) at startup.");
        }
        return dao.getIntStatInstance(itemName, columnName);
    }

    public DbItem findBySymbol(String symbol) {
        final String sql = """
            SELECT id, name, attackPowerIncrease, healthIncrease, maxHealthIncrease, equipped, symbol
            FROM item
            WHERE symbol = ?
        """;
        final DbItem[] out = new DbItem[1];
        jdbcDao.executeQuery(sql, ps -> ps.setString(1, symbol), rs -> {
            if (rs.next()) out[0] = map(rs);
        });
        return out[0];
    }

    public List<DbItem> findAll() {
        final String sql = """
            SELECT id, name, attackPowerIncrease, healthIncrease, maxHealthIncrease, equipped, symbol
            FROM item
            ORDER BY id
        """;
        final List<DbItem> list = new ArrayList<>();
        jdbcDao.executeQuery(sql, ps -> {}, rs -> {
            while (rs.next()) list.add(map(rs));
        });
        return list;
    }

    private Integer getIntStatInstance(String itemName, String columnName) {
        for (DbItem row : findAll()) {
            if (row.name.equals(itemName)) {
                return switch (columnName) {
                    case "attackPowerIncrease" -> row.attackPowerIncrease;
                    case "healthIncrease"      -> row.healthIncrease;
                    case "maxHealthIncrease"   -> row.maxHealthIncrease;
                    default -> null;
                };
            }
        }
        return null;
    }

    private DbItem map(ResultSet rs) throws SQLException {
        return new DbItem(
                rs.getInt("id"),
                rs.getString("name"),
                getNullableInt(rs, "attackPowerIncrease"),
                getNullableInt(rs, "healthIncrease"),
                getNullableInt(rs, "maxHealthIncrease"),
                rs.getBoolean("equipped"),
                rs.getString("symbol")
        );
    }

    private Integer getNullableInt(ResultSet rs, String column) throws SQLException {
        int v = rs.getInt(column);
        return rs.wasNull() ? null : v;
    }

}
