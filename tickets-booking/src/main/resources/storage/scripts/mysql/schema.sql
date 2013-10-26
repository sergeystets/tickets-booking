DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Ticket ;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS  BookedTickets;

CREATE TABLE Category (
  id    INTEGER PRIMARY KEY NOT NULL,
  name VARCHAR(30) NOT NULL
);

CREATE TABLE User (
  login    VARCHAR(30) PRIMARY KEY NOT NULL,
  password VARCHAR(25) NOT NULL
);

CREATE TABLE Ticket (
  id   INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title VARCHAR(30) NOT NULL,
  dateTime  DATE NOT NULL,
  categoryId INTEGER NOT NULL,
  place INTEGER NOT NULL,
  userLogin VARCHAR(30) DEFAULT NULL
);

ALTER TABLE Ticket ADD CONSTRAINT fk_ticket_ticketCategory FOREIGN KEY (categoryId) REFERENCES Category (id);
ALTER TABLE Ticket ADD CONSTRAINT fk_ticket_user FOREIGN KEY (userLogin) REFERENCES User (login);

