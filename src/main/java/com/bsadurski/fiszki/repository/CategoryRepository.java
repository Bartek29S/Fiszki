package com.bsadurski.fiszki.repository;

import com.bsadurski.fiszki.entity.Authorisation;
import com.bsadurski.fiszki.entity.Category;
import com.bsadurski.fiszki.entity.FlashCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Category> getCategories() {
        return jdbcTemplate.query(
                "SELECT id, name, userId FROM test.categories",
                BeanPropertyRowMapper.newInstance(Category.class));
    }

    public int postCategory(Category u) {
        return jdbcTemplate.update("INSERT INTO test.categories(name, userId) VALUES(?, ?)"
                , u.getName(), user.getUserId());
    }

    /** @noinspection ConstantConditions*/
    public boolean checkIfCategoryExisted(Category u) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT  1 FROM test.categories  WHERE name=? AND userId=?)"
                , Boolean.class, u.getName(), user.getUserId());
    }
}
