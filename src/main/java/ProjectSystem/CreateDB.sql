/*DROP DATABASE DevelopmentDB;*/
CREATE DATABASE DevelopmentDB;
USE DevelopmentDB;

CREATE TABLE developers(
  developerID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  teamID INT,
  PRIMARY KEY (developerID)
);

CREATE TABLE teams(
  teamID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  projectID INT,
  PRIMARY KEY (teamID)
);

CREATE TABLE DevelopmentDB.projects(
  projectID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (projectID)
);