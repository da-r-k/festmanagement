package com.example.fms.festmanagement.models;

import jakarta.validation.constraints.Pattern;

public class Member {

    private int memberId;

    private String firstName;

    private String lastName;

    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid Email")
    private String emailId;

    @Pattern(regexp = "^[1-9][0-9]{9}$", message = "Invalid Phone Number")
    private String phoneNumber;

    private int eventId;

    private int password;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

}
