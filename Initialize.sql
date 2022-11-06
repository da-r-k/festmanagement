CREATE DATABASE dbms;
        
CREATE TABLE Cart
(
  cartId           INT     NOT NULL AUTO_INCREMENT,
  participantEmail VARCHAR(255) NOT NULL,
  PRIMARY KEY (cartId)
);

CREATE TABLE CartItemDetails
(
  quantity INT NULL    ,
  itemId   INT NOT NULL,
  cartId   INT NOT NULL,
  PRIMARY KEY (itemId, cartId)
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
  TransactionId INT      NOT NULL AUTO_INCREMENT,
  cartId        INT      NOT NULL,
  amount        INT      NULL    ,
  datetime      DATETIME NOT NULL,
  PRIMARY KEY (TransactionId)
);

CREATE TABLE Users
(
  userEmail VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  role      VARCHAR(255) NOT NULL,
  PRIMARY KEY (userEmail)
);

ALTER TABLE Participant
  ADD CONSTRAINT FK_Users_TO_Participant
    FOREIGN KEY (participantEmail)
    REFERENCES Users (userEmail) ON DELETE CASCADE;

ALTER TABLE Organiser
  ADD CONSTRAINT FK_Users_TO_Organiser
    FOREIGN KEY (organiserEmail)
    REFERENCES Users (userEmail) ON DELETE CASCADE;

ALTER TABLE Organiser
  ADD CONSTRAINT FK_Event_TO_Organiser
    FOREIGN KEY (eventId)
    REFERENCES Event (eventId) ON DELETE CASCADE;

ALTER TABLE Event
  ADD CONSTRAINT FK_Organiser_TO_Event
    FOREIGN KEY (headEmail)
    REFERENCES Organiser (organiserEmail) ON DELETE CASCADE;

ALTER TABLE SubEvent
  ADD CONSTRAINT FK_Event_TO_SubEvent
    FOREIGN KEY (eventId)
    REFERENCES Event (eventId) ON DELETE CASCADE;

ALTER TABLE Competition
  ADD CONSTRAINT FK_SubEvent_TO_Competition
    FOREIGN KEY (subEventId, eventId)
    REFERENCES SubEvent (subEventId, eventId) ON DELETE CASCADE;

ALTER TABLE Participation
  ADD CONSTRAINT FK_Participant_TO_Participation
    FOREIGN KEY (participantEmail)
    REFERENCES Participant (participantEmail) ON DELETE CASCADE;

ALTER TABLE Fund
  ADD CONSTRAINT FK_Event_TO_Fund
    FOREIGN KEY (eventId)
    REFERENCES Event (eventId) ON DELETE CASCADE;

ALTER TABLE Fund
  ADD CONSTRAINT FK_Sponsor_TO_Fund
    FOREIGN KEY (sponsorId)
    REFERENCES Sponsor (sponsorId) ON DELETE CASCADE;

ALTER TABLE Cart
  ADD CONSTRAINT FK_Participant_TO_Cart
    FOREIGN KEY (participantEmail)
    REFERENCES Participant (participantEmail) ON DELETE CASCADE;

ALTER TABLE Participation
  ADD CONSTRAINT FK_Competition_TO_Participation
    FOREIGN KEY (competitionId, subEventId, eventId)
    REFERENCES Competition (competitionId, subEventId, eventId) ON DELETE CASCADE;

ALTER TABLE CartItemDetails
  ADD CONSTRAINT FK_Item_TO_CartItemDetails
    FOREIGN KEY (itemId)
    REFERENCES Item (itemId) ON DELETE CASCADE;

ALTER TABLE CartItemDetails
  ADD CONSTRAINT FK_Cart_TO_CartItemDetails
    FOREIGN KEY (cartId)
    REFERENCES Cart (cartId) ON DELETE CASCADE;

ALTER TABLE Transaction
  ADD CONSTRAINT FK_Cart_TO_Transaction
    FOREIGN KEY (cartId)
    REFERENCES Cart (cartId) ON DELETE CASCADE;

INSERT INTO Users VALUES('admin@admin.com','password','admin');