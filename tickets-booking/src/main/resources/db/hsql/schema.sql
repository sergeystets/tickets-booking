DROP TABLE Ticket IF EXISTS;
DROP TABLE User IF EXISTS;
DROP TABLE BookedTickets IF EXISTS;
DROP TABLE TicketCategory IF EXISTS;


CREATE TABLE Category (
  id    INTEGER IDENTITY PRIMARY KEY NOT NULL,
  name VARCHAR(30) NOT NULL
);

CREATE TABLE Ticket (
  id   INTEGER IDENTITY PRIMARY KEY NOT NULL,
  title VARCHAR(30) NOT NULL,
  dateTime  DATE NOT NULL,
  categoryId INTEGER NOT NULL,
  place INTEGER NOT NULL 
);

ALTER TABLE Ticket ADD CONSTRAINT fk_ticket_ticketCategory FOREIGN KEY (categoryId) REFERENCES Category (id);

CREATE TABLE User (
  login    VARCHAR(30) PRIMARY KEY NOT NULL,
  password VARCHAR(25) NOT NULL
);

CREATE TABLE BookedTickets (
  userLogin    VARCHAR(30) NOT NULL,
  ticketId INTEGER NOT NULL
);

ALTER TABLE BookedTickets ADD CONSTRAINT fk_bookedTickets_user FOREIGN KEY (userLogin) REFERENCES User (login);
ALTER TABLE BookedTickets ADD CONSTRAINT fk_bookedTickets_ticket FOREIGN KEY (ticketId) REFERENCES Ticket (id);
