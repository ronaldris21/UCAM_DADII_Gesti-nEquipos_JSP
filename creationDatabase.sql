CREATE TABLE club (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE jugador (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellidos VARCHAR(50) NOT NULL,
  goles INT DEFAULT 0,
  club_id INT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (club_id) REFERENCES club(id) ON DELETE CASCADE
);


INSERT INTO club (nombre) VALUES ('Real Madrid');
INSERT INTO club (nombre) VALUES ('Barcelona');
INSERT INTO club (nombre) VALUES ('Manchester United');

INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Cristiano', 'Ronaldo', 0, 1);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Sergio', 'Ramos', 0, 1);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Luka', 'Modric', 0, 1);

INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Lionel', 'Messi', 0, 2);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Gerard', 'Pique', 0, 2);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Sergio', 'Busquets', 0, 2);

INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Wayne', 'Rooney', 0, 3);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Cristiano', 'Ronaldo', 0, 3);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Paul', 'Pogba', 0, 3);