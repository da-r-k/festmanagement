package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Member;
import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @Autowired
    private JdbcTemplate t;

    private void initMember() {

        String x = "CREATE TABLE IF NOT EXISTS Member (memberId INT, firstName VARCHAR(20), lastName VARCHAR(20), emailId VARCHAR(50), phoneNumber CHAR(10), eventId INT, password VARCHAR(20), PRIMARY KEY (memberId))";

        t.update(x);

    }

    private void insertMember(Member member) {

        String x = "INSERT INTO Member VALUES (?, ?, ?, ?, ?, ?, ?)";

        t.update(x, member.getMemberId(), member.getFirstName(), member.getLastName(), member.getemailId(), member.getPhoneNumber(), member.getEventId(), member.getPassword());

    }

    private Member selectMember(int memberId) {

        try {

            String x = "SELECT * FROM Member WHERE memberId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Member.class), new Object[] {memberId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    private void deleteMember(int memberId) {

        String x = "DELETE FROM Member WHERE memberId = ?";

        t.update(x, memberId);

    }

}
