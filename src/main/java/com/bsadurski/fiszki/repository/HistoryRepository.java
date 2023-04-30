package com.bsadurski.fiszki.repository;

import com.bsadurski.fiszki.entity.Authorisation;
import com.bsadurski.fiszki.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Authorisation user;

    public List<History> getAllHistory() {
        return jdbcTemplate.query(
                "SELECT * FROM test.history  WHERE userId=" + user.getUserId(),
                BeanPropertyRowMapper.newInstance(History.class));
    }

    public int postHistory(History h) {
        return jdbcTemplate.update("INSERT INTO test.history(flashcardId, operationType, userId) VALUES(?,?,?)"
                ,h.getFlashcardId(), h.getOperationType(), user.getUserId());
    }
}


