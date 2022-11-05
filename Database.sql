CREATE DATABASE dbms;
       
CREATE TABLE Cart
(
  cartId           INT     NOT NULL AUTO_INCREMENT,
  participantEmail VARCHAR(255) NOT NULL,
  PRIMARY KEY (cartId, participantEmail)
);

CREATE TABLE CartItemDetails
(
  quantity INT NOT NULL DEFAULT 0,
  cartId   INT NOT NULL,
  itemId   INT NOT NULL,
  PRIMARY KEY (cartId, itemId)
);

CREATE TABLE Competition
(
  competitionId   INT     NOT NULL AUTO_INCREMENT,
  competitionName VARCHAR(255) NULL    ,
  prize           INT     NULL    ,
  subEventId      INT     NOT NULL,
  eventId         INT     NOT NULL,
  PRIMARY KEY (competitionId, subEventId, eventId)
);

CREATE TABLE Event
(
  eventId   INT     NOT NULL AUTO_INCREMENT,
  eventName VARCHAR(255) NOT NULL,
  headEmail VARCHAR(255) NOT NULL,
  PRIMARY KEY (eventId)
);

CREATE TABLE Fund
(
  eventId   INT NOT NULL,
  sponsorId INT NOT NULL,
  amount    INT NOT NULL,
  PRIMARY KEY (eventId, sponsorId)
);

CREATE TABLE Item
(
  itemId       INT     NOT NULL AUTO_INCREMENT,
  itemName     VARCHAR(255) NULL    ,
  sellingPrice INT     NULL    ,
  stock        INT     NULL    ,
  costPrice    INT     NULL    ,
  PRIMARY KEY (itemId)
);

CREATE TABLE Organiser
(
  organiserEmail VARCHAR(255) NOT NULL,
  firstName      VARCHAR(255) NOT NULL,
  lastName       VARCHAR(255) NOT NULL,
  mobileNumber   VARCHAR(255) NOT NULL,
  eventId        INT     NULL    ,
  PRIMARY KEY (organiserEmail)
);

CREATE TABLE Participant
(
  participantEmail VARCHAR(255) NOT NULL,
  firstName        VARCHAR(255) NOT NULL,
  lastName         VARCHAR(255) NOT NULL,
  mobileNumber     VARCHAR(255) NOT NULL,
  sex              VARCHAR(255) NOT NULL,
  college          VARCHAR(255) NOT NULL,
  pinCode          VARCHAR(255) NULL    ,
  streetName       VARCHAR(255) NULL    ,
  PRIMARY KEY (participantEmail)
);

CREATE TABLE Participation
(
  leaderBoardPosition INT     NULL    ,
  participantEmail    VARCHAR(255) NOT NULL,
  competitionId       INT     NOT NULL,
  subEventId          INT     NOT NULL,
  eventId             INT     NOT NULL,
  PRIMARY KEY (participantEmail, competitionId, subEventId, eventId)
);

CREATE TABLE Sponsor
(
  sponsorId   INT     NOT NULL AUTO_INCREMENT,
  sponsorName VARCHAR(255) NOT NULL,
  PRIMARY KEY (sponsorId)
);

CREATE TABLE SubEvent
(
  subEventId   INT     NOT NULL AUTO_INCREMENT,
  startDate    DATE    NOT NULL,
  venue        VARCHAR(255) NOT NULL,
  eventId      INT     NOT NULL,
  endDate      DATE    NOT NULL,
  subEventName VARCHAR(255) NOT NULL,
  PRIMARY KEY (subEventId, eventId)
);

CREATE TABLE Transaction
(
  transactionId    INT      NOT NULL AUTO_INCREMENT,
  amount           INT      NOT NULL,
  dateTime         DATETIME NOT NULL,
  cartId           INT      NOT NULL,
  participantEmail VARCHAR(255)  NOT NULL,
  PRIMARY KEY (transactionId)
);

CREATE TABLE Users
(
  userEmail VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  role      VARCHAR(255) NOT NULL,
  PRIMARY KEY (userEmail)
);




