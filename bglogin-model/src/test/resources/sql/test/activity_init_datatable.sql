CREATE TABLE activities (
  name VARCHAR(45) NOT NULL,
  date VARCHAR(45) NOT NULL,
  owner VARCHAR(60) NOT NULL,
  PRIMARY KEY (name));
  
DELETE FROM activities;
INSERT INTO activities (name, date, owner) VALUES
	('act1', 'today', 'Silvio'),
	('act2', 'tomorrow', 'Tinti');
