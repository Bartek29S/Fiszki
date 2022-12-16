package com.bsadurski.fiszki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {

    @Autowired
    Authorisation user;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getSecuritySqLFragment() {
        return "SELECT * FROM test.categories WHERE id=";
    }

    public Category getCategory(String categoryId) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, userId FROM test.categories WHERE id=?",
                BeanPropertyRowMapper.newInstance(Category.class), categoryId);
    }

    public int postCategory(Category u) {
        return jdbcTemplate.update("INSERT INTO test.categories(name, userId) VALUES(?, ?)"
                , u.getName(), user.getUserId());
    }

    /** @noinspection ConstantConditions*/
    // IDE pisalo ze to moze zwrocic nulla a a ja wlasnie po
    // to uzylem jakiegos exists aby tego nie robilo (udalo mi sie wylaczyc wbudowanym komentarzem)
    public boolean checkIfCategoryExisted(Category u) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT  1 FROM test.categories  WHERE name=? AND userId=?)"
                , Boolean.class, u.getName(), user.getUserId());
    }
}
