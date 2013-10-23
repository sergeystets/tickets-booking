INSERT INTO Category VALUES (0, 'STANDARD');
INSERT INTO Category VALUES (1, 'PREMIUM');
INSERT INTO Category VALUES (2, 'BAR');


INSERT INTO Ticket VALUES (DEFAULT, 'Saw 1', '2013-10-01', 0, 12);
INSERT INTO Ticket VALUES (DEFAULT, 'Saw 2', '2013-10-03', 0, 1);
INSERT INTO Ticket VALUES (DEFAULT, 'Saw 3', '2013-10-04', 2, 6);
INSERT INTO Ticket VALUES (DEFAULT, 'Saw 4', '2013-10-03', 1, 5);


INSERT INTO User VALUES ('admin', 'admin');
INSERT INTO User VALUES ('user', 'user');

INSERT INTO BookedTickets VALUES ('admin', 1);
