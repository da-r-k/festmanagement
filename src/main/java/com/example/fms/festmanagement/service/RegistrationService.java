package com.example.fms.festmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.doa.ParticipantRepo;
import com.example.fms.festmanagement.doa.UserRepo;
import com.example.fms.festmanagement.models.Participant;
import com.example.fms.festmanagement.models.User;

@Service
public class RegistrationService {

    @Autowired
    private ParticipantRepo participantRepo;

    @Autowired
    private UserRepo userRepo;

    public void register(User users, Participant participant) {
        participant.setParticipantEmail(users.getUserEmail());
        users.setRole("participant");
        userRepo.insertUser(users);
        participantRepo.insertParticipant(participant);
    }
    
}
