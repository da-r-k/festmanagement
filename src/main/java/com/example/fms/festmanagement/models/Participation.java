package com.example.fms.festmanagement.models;

public class Participation {

    private String participantEmail;

    private int leaderBoardPosition;

    private int competitionId;

    private int subEventId;

    private int eventId;

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public int getPosition() {
        return leaderBoardPosition;
    }

    public void setPosition(int position) {
        this.leaderBoardPosition = position;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getSubEventId() {
        return subEventId;
    }

    public void setSubEventId(int subEventId) {
        this.subEventId = subEventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

}
