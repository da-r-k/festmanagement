package com.example.fms.festmanagement.models;

public class Event {

    private int eventId;

    private String eventName;

    private String headEmail;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getHeadEmail() {
        return headEmail;
    }

    public void setHeadEmail(String headEmail) {
        this.headEmail = headEmail;
    }


    @Override
    public String toString() {
        return "{" +
            " eventId='" + getEventId() + "'" +
            ", eventName='" + getEventName() + "'" +
            ", headEmail='" + getHeadEmail() + "'" +
            "}";
    }

}
