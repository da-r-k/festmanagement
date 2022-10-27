package com.example.fms.festmanagement.models;

public class Participation {

    private int participationId;

    private int position;

    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

}
