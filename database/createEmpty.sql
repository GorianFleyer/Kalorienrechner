--
-- File generated with SQLiteStudio v3.2.1 on Di. Feb. 25 13:42:40 2020
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: CaloriesOnDay
DROP TABLE IF EXISTS CaloriesOnDay;

CREATE TABLE CaloriesOnDay (
    id      INTEGER PRIMARY KEY AUTOINCREMENT,
    date    STRING  NOT NULL ON CONFLICT ROLLBACK
                    UNIQUE,
    calorie DOUBLE
);


-- Table: DayWeight
DROP TABLE IF EXISTS DayWeight;

CREATE TABLE DayWeight (
    id     INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT
                   UNIQUE,
    date   STRING  UNIQUE
                   NOT NULL,
    weight REAL
);


-- Table: ingridient
DROP TABLE IF EXISTS ingridient;

CREATE TABLE ingridient (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    name_0   STRING,
    name_1   STRING,
    calories DOUBLE
);


-- Table: nutrition
DROP TABLE IF EXISTS nutrition;

CREATE TABLE nutrition (
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    name_0           STRING,
    name_1           STRING,
    recomendedAmount INTEGER
);


-- Table: recipe
DROP TABLE IF EXISTS recipe;

CREATE TABLE recipe (
    id     INTEGER PRIMARY KEY AUTOINCREMENT,
    name_0 STRING,
    name_1 STRING
);


-- Table: refDateNutrition
DROP TABLE IF EXISTS refDateNutrition;

CREATE TABLE refDateNutrition (
    fk_date      BIGINT REFERENCES CaloriesOnDay (id),
    fk_nutrition        REFERENCES nutrition (id),
    amount       REAL,
    PRIMARY KEY (
        fk_date COLLATE RTRIM,
        fk_nutrition COLLATE RTRIM
    )
);


-- Table: refIngredientRecipe
DROP TABLE IF EXISTS refIngredientRecipe;

CREATE TABLE refIngredientRecipe (
    fk_recipe     BIGINT REFERENCES recipe (id),
    fk_ingredient BIGINT REFERENCES ingridient (id),
    amount        BIGINT,
    PRIMARY KEY (
        fk_recipe COLLATE RTRIM,
        fk_ingredient COLLATE RTRIM
    )
);


-- Table: refNutritionIngridient
DROP TABLE IF EXISTS refNutritionIngridient;

CREATE TABLE refNutritionIngridient (
    fk_ingridient BIGINT REFERENCES ingridient (id),
    fk_nutrition  BIGINT REFERENCES nutrition (id),
    amount        REAL,
    PRIMARY KEY (
        fk_ingridient COLLATE RTRIM,
        fk_nutrition COLLATE RTRIM
    )
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
