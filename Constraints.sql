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

ALTER TABLE CartItemDetails
  ADD CONSTRAINT FK_Cart_TO_CartItemDetails
    FOREIGN KEY (cartId)
    REFERENCES Cart (cartId) ON DELETE CASCADE;

ALTER TABLE CartItemDetails
  ADD CONSTRAINT FK_Item_TO_CartItemDetails
    FOREIGN KEY (itemId)
    REFERENCES Item (itemId) ON DELETE CASCADE;

ALTER TABLE Participation
  ADD CONSTRAINT FK_Competition_TO_Participation
    FOREIGN KEY (competitionId, subEventId, eventId)
    REFERENCES Competition (competitionId, subEventId, eventId) ON DELETE CASCADE;

ALTER TABLE Transaction
  ADD CONSTRAINT FK_Cart_TO_Transaction
    FOREIGN KEY (cartId, participantEmail)
    REFERENCES Cart (cartId, participantEmail) ON DELETE CASCADE;