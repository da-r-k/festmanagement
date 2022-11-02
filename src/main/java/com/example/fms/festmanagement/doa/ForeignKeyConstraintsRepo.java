package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ForeignKeyConstraintsRepo {

    @Autowired
    private JdbcTemplate t;

    public void initFK1() {

        String x = "ALTER TABLE Participant ADD CONSTRAINT FK_Users_TO_Participant FOREIGN KEY (participantEmail) REFERENCES Users (userEmail)";

        t.update(x);

    }

    public void initFK2() {

        String x = "ALTER TABLE Organiser ADD CONSTRAINT FK_Users_TO_Organiser FOREIGN KEY (organiserEmail) REFERENCES Users (userEmail)";

        t.update(x);

    }

    public void initFK3() {

        String x = "ALTER TABLE Organiser ADD CONSTRAINT FK_Event_TO_Organiser FOREIGN KEY (eventId) REFERENCES Event (eventId)";

        t.update(x);

    }

    public void initFK4() {

        String x = "ALTER TABLE Event ADD CONSTRAINT FK_Organiser_TO_Event FOREIGN KEY (headEmail) REFERENCES Organiser (organiserEmail)";

        t.update(x);

    }

    public void initFK5() {

        String x = "ALTER TABLE SubEvent ADD CONSTRAINT FK_Event_TO_SubEvent FOREIGN KEY (eventId) REFERENCES Event (eventId)";

        t.update(x);

    }

    public void initFK6() {

        String x = "ALTER TABLE Competition ADD CONSTRAINT FK_SubEvent_TO_Competition FOREIGN KEY (subEventId, eventId) REFERENCES SubEvent (subEventId, eventId)";

        t.update(x);

    }

    public void initFK7() {

        String x = "ALTER TABLE Participation ADD CONSTRAINT FK_Competition_TO_Participation FOREIGN KEY (competitionId) REFERENCES Competition (competitionId, subEventId, eventId)";

        t.update(x);

    }

    public void initFK8() {

        String x = "ALTER TABLE Participation ADD CONSTRAINT FK_Participant_TO_Participation FOREIGN KEY (participantEmail) REFERENCES Participant (participantEmail)";

        t.update(x);

    }

    public void initFK9() {

        String x = "ALTER TABLE Fund ADD CONSTRAINT FK_Event_TO_Fund FOREIGN KEY (eventId) REFERENCES Event (eventId)";

        t.update(x);

    }

    public void initFK10() {

        String x = "ALTER TABLE Fund ADD CONSTRAINT FK_Sponsor_TO_Fund FOREIGN KEY (sponsorId) REFERENCES Sponsor (sponsorId)";

        t.update(x);

    }

    public void initFK11() {

        String x = "ALTER TABLE Cart ADD CONSTRAINT FK_Participant_TO_Cart FOREIGN KEY (participantEmail) REFERENCES Participant (participantEmail)";

        t.update(x);

    }

    public void initFK12() {

        String x = "ALTER TABLE CartItemDetails ADD CONSTRAINT FK_Cart_TO_CartItemDetails FOREIGN KEY (cartId) REFERENCES Cart (cartId)";

        t.update(x);

    }

    public void initFK13() {

        String x = "ALTER TABLE CartItemDetails ADD CONSTRAINT FK_Item_TO_CartItemDetails FOREIGN KEY (itemId) REFERENCES Item (itemId)";

        t.update(x);

    }

    public void initFK14() {

        String x = "ALTER TABLE Transaction ADD CONSTRAINT FK_Cart_TO_Transaction FOREIGN KEY (cartId) REFERENCES Cart (cartId)";

        t.update(x);

    }

}
