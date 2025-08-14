CREATE table item(
                     id SERIAL PRIMARY KEY,
                     name TEXT,
                     attackPowerIncrease INTEGER,
                     healthIncrease INTEGER,
                     maxHealthIncrease INTEGER,
                     equipped BOOLEAN,
                     symbol CHAR
);

DROP TABLE IF EXISTS item;

SELECT name, COUNT(*)
FROM item
GROUP BY name
HAVING COUNT(*) > 1;

-- 2) (Optional) Remove duplicates, keeping one row (PostgreSQL 13+):
DELETE FROM item t
    USING item td
WHERE t.ctid < td.ctid AND t.name = td.name;

-- 3) Add the unique constraint:
ALTER TABLE item ADD CONSTRAINT item_name_unique UNIQUE (name);


INSERT INTO item (name, attackPowerIncrease, healthIncrease, maxHealthIncrease, equipped, symbol)
VALUES
    ('key',     NULL, NULL, NULL, false, 'k'),
    ('potion',  NULL, 4,    NULL, false, 't'),
    ('sword',   3,    NULL, NULL, false, 'w'),
    ('chainMail',   NULL,    NULL, 5, false, 'a'),
    ('gem', NULL, NULL, NULL, false, '8')
ON CONFLICT (name) DO UPDATE
    SET attackPowerIncrease = EXCLUDED.attackPowerIncrease,
        healthIncrease      = EXCLUDED.healthIncrease,
        maxHealthIncrease   = EXCLUDED.maxHealthIncrease,
        equipped            = EXCLUDED.equipped,
        symbol              = EXCLUDED.symbol;
