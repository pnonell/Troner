CREATE DATABASE IF NOT exists Troner;
USE Troner;

CREATE TABLE Usuari(
  id_jugador BIGINT UNSIGNED AUTO_INCREMENT,
  login VARCHAR(511),
  mail VARCHAR(511),
  contrasenya VARCHAR(511),
  punts INT,
  data_registre DATE,
  data_ultimacces DATE,
  tecla_up INT,
  tecla_down INT,
  tecla_left INT,
  tecla_right INT,
  PRIMARY KEY (id_jugador)
);

CREATE TABLE puntuacio(
  id_jugador BIGINT UNSIGNED,
  id_puntuacio BIGINT UNSIGNED AUTO_INCREMENT,
  puntuacio INT,
  PRIMARY KEY (id_puntuacio),
  FOREIGN KEY (id_jugador) REFERENCES Usuari(id_jugador)
);
