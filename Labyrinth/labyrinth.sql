CREATE DATABASE IF NOT EXISTS labyrinth;
USE labyrinth;
CREATE TABLE IF NOT EXISTS HighScore (
  Difficulty VARCHAR(50) NOT NULL,
  GameLevel  INT NOT NULL,
  DoneLevel      INT
);