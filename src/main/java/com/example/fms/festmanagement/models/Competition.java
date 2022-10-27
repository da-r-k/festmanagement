package com.example.fms.festmanagement.models;

public class Competition {

    private int competitionId;

    private String competitionName;

    private String prize;

    private int eventId;

    private int subeventId;

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

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSubeventId() {
        return subeventId;
    }

    public void setSubeventId(int subeventId) {
        this.subeventId = subeventId;
    }

}
