package com.example.fms.festmanagement.models;

public class Competition {

    private int competitionId;

    private String competitionName;

    private int prize;

    private int eventId;

    private int subEventId;

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSubEventId() {
        return subEventId;
    }

    public void setSubEventId(int subEventId) {
        this.subEventId = subEventId;
    }

}
