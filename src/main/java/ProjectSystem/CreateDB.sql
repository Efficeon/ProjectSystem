CREATE DATABASE DevelopmentDB;
USE DevelopmentDB;

CREATE TABLE developers(
  developerID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (developerID)
);

CREATE TABLE teams(
  teamID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (teamID)
);

CREATE TABLE projects(
  projectID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (projectID)
);

CREATE TABLE teams_developers (
  teamID BIGINT NOT NULL ,
  developerID BIGINT NOT NULL
);

CREATE TABLE projects_teams (
  teamID BIGINT NOT NULL ,
  projectID BIGINT NOT NULL
);
