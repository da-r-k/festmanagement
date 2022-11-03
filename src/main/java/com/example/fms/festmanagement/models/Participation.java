package com.example.fms.festmanagement.models;

public class Participation {

    private String participantEmail;

    private Long leaderBoardPosition;

    private int competitionId;

    private int subEventId;

    public Long getLeaderBoardPosition() {
        return this.leaderBoardPosition;
    }

    public void setLeaderBoardPosition(Long leaderBoardPosition) {
        this.leaderBoardPosition = leaderBoardPosition;
    }

    private int eventId;

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

    @Override
    public String toString() {
        return "{" +
            " participantEmail='" + getParticipantEmail() + "'" +
            ", leaderBoardPosition='" + getLeaderBoardPosition() + "'" +
            ", competitionId='" + getCompetitionId() + "'" +
            ", subEventId='" + getSubEventId() + "'" +
            ", eventId='" + getEventId() + "'" +
            "}";
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
