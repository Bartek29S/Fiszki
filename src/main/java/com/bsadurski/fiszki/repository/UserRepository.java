package com.bsadurski.fiszki.repository;

import com.bsadurski.fiszki.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUser(int userId) {
        return jdbcTemplate.queryForObject("SELECT id, name, surname, email FROM test.users WHERE id=?",
                BeanPropertyRowMapper.newInstance(User.class), userId);
    }

    public User getUserByUserName(String login) {
        System.out.println("SELECT id, name, surname, email, password FROM test.users WHERE email=" + login);
        return jdbcTemplate.queryForObject("SELECT id, name, surname, email, password, role FROM test.users WHERE email=?",
                BeanPropertyRowMapper.newInstance(User.class), login);
    }

    public int postUser(User u) {
        return jdbcTemplate.update("INSERT INTO test.users(name, surname, email) VALUES(?,?,?)"
                , u.getName(), u.getSurname(), u.getEmail());
    }
}
