package com.example.fms.festmanagement.models;

import java.sql.Date;

public class SubEvent {

    private int subeventId;

    private Date startDate;

    private Date endDate;

    private String venue;

    private int eventId;

    public int getSubeventId() {
        return subeventId;
    }

    public void setSubeventId(int subeventId) {
        this.subeventId = subeventId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

}
