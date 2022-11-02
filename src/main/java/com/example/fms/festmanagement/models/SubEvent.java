package com.example.fms.festmanagement.models;

import java.util.Date;

public class SubEvent {

    private int subEventId;

    private String subEventName;

    private Date startDate;

    private Date endDate;

    private String venue;

    private int eventId;

    public int getSubEventId() {
        return subEventId;
    }

    public void setSubEventId(int subEventId) {
        this.subEventId = subEventId;
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

    public void setEventId(int eventId) { this.eventId = eventId; }

    public String getSubEventName() { return subEventName; }

    public void setSubEventName(String subEventName) { this.subEventName = subEventName; }
}
