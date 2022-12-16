package com.bsadurski.fiszki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUser(int userId) {
        return jdbcTemplate.queryForObject("SELECT id, name, surname, email FROM test.users WHERE id=" + userId,
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public int postUser(User u) {
        return jdbcTemplate.update("INSERT INTO test.users(name, surname, email) VALUES(?,?,?)"
                , u.getName(), u.getSurname(), u.getEmail());
    }
}
