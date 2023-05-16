CREATE TABLE club (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL UNIQUE,
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

CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  contrasena VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);



INSERT INTO club (nombre) VALUES ('Real Madrid');
INSERT INTO club (nombre) VALUES ('Barcelona');
INSERT INTO club (nombre) VALUES ('Manchester United');

INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Cristiano', 'Ronaldo', 6, 1);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Sergio', 'Ramos', 4, 1);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Luka', 'Modric', 2, 1);

INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Lionel', 'Messi', 5, 2);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Gerard', 'Pique', 6, 2);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Sergio', 'Busquets', 4, 2);

INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Wayne', 'Rooney', 7, 3);
INSERT INTO jugador (nombre, apellidos, goles, club_id) VALUES ('Paul', 'Pogba', 0, 3);


INSERT INTO user (nombre, contrasena) VALUES 
('admin', 'admin'),
('ronaldris21', '1234'),
('pablo', 'pablo');