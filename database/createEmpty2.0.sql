--
-- File generated with SQLiteStudio v3.2.1 on Mo. Mai 4 23:28:40 2020
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: AdditionalCaloriesBurned
DROP TABLE IF EXISTS AdditionalCaloriesBurned;

CREATE TABLE AdditionalCaloriesBurned (
    ID       INTEGER PRIMARY KEY AUTOINCREMENT,
    date     BIGINT  UNIQUE
                     NOT NULL,
    Calories REAL
);


-- Table: BMIAdult
DROP TABLE IF EXISTS BMIAdult;

CREATE TABLE BMIAdult (
    ID     INTEGER PRIMARY KEY AUTOINCREMENT,
    BMI    REAL,
    name_0 STRING,
    name_1 STRING
);


-- Table: CaloriesOnDay
DROP TABLE IF EXISTS CaloriesOnDay;

CREATE TABLE CaloriesOnDay (
    id      INTEGER PRIMARY KEY AUTOINCREMENT,
    date    STRING  UNIQUE,
    calorie DOUBLE,
    DateInt INTEGER UNIQUE
);


-- Table: DayWeight
DROP TABLE IF EXISTS DayWeight;

CREATE TABLE DayWeight (
    id      INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT
                    UNIQUE,
    date    STRING  UNIQUE,
    weight  REAL,
    DateInt INTEGER
);


-- Table: ingridient
DROP TABLE IF EXISTS ingridient;

CREATE TABLE ingridient (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    name_0   STRING,
    name_1   STRING,
    calories DOUBLE
);


-- Table: NotePads
DROP TABLE IF EXISTS NotePads;

CREATE TABLE NotePads (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    noteLine TEXT
);


-- Table: nutrition
DROP TABLE IF EXISTS nutrition;

CREATE TABLE nutrition (
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    name_0           STRING,
    name_1           STRING,
    recomendedAmount INTEGER
);


-- Table: profile
DROP TABLE IF EXISTS profile;

CREATE TABLE profile (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    login    STRING  NOT NULL,
    password STRING,
    size     REAL,
    pal      REAL,
    birthday STRING,
    age      INTEGER,
    sex      INTEGER
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


-- Table: refRecipeComment
DROP TABLE IF EXISTS refRecipeComment;

CREATE TABLE refRecipeComment (
    ID        INTEGER PRIMARY KEY AUTOINCREMENT,
    fk_recipe INTEGER REFERENCES recipe (id),
    comment   TEXT
);


-- Table: tupper
DROP TABLE IF EXISTS tupper;

CREATE TABLE tupper (
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    calories     INTEGER,
    fullWeight   REAL,
    tupperWeight REAL,
    date         STRING,
    DateInt      INTEGER
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
