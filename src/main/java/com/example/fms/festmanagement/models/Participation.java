package com.example.fms.festmanagement.models;

public class Participation {

    private int participationId;

    private int position;

    private String participantEmail;

    private int competitionId;

    public int getParticipationId() {
        return participationId;
    }

    public void setParticipationId(int participationId) {
        this.participationId = participationId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

}
