package com.bsadurski.fiszki.repository;

import com.bsadurski.fiszki.entity.ActiveFlashCard;
import com.bsadurski.fiszki.entity.Authorisation;
import com.bsadurski.fiszki.entity.FlashCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class FlashCardRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Authorisation user;

    public String getSecuritySqLFragment() {
        return "SELECT * FROM test.fleshcard WHERE id=";
    }

    public Page<FlashCard> getOnePage(Pageable page) {
        Sort.Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Sort.Order.by("englishWord");
        List<FlashCard> flashcards = jdbcTemplate.query(
                "SELECT id, englishWord, polishWord FROM test.fleshcard  WHERE userId=" + user.getUserId() + " ORDER BY " + order.getProperty() + " "
                        + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset(),
                BeanPropertyRowMapper.newInstance(FlashCard.class));

        return new PageImpl<FlashCard>(flashcards, page, count());
    }

    public List<FlashCard> getAll() {
        return  jdbcTemplate.query(
                "SELECT * FROM test.fleshcard  WHERE userId=" + user.getUserId(),
                BeanPropertyRowMapper.newInstance(FlashCard.class));
    }

    public int postFlashcard2(FlashCard f) {
        return jdbcTemplate.update("INSERT INTO test.fleshcard(englishWord, polishWord, userId, categoryId, learningStage) VALUES(?,?,?,?,?)"
                , f.getEnglishWord(), f.getPolishWord(), user.getUserId(), f.getCategoryId(), f.getLearningStage());
    }

    public int postFlashcard(FlashCard f) {
        String sql = "INSERT INTO test.fleshcard(englishWord, polishWord, userId, categoryId, learningStage) VALUES(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, f.getEnglishWord());
            pst.setString(2, f.getPolishWord());
            pst.setInt(3, user.getUserId());
            pst.setInt(4, f.getCategoryId());
            pst.setInt(5, f.getLearningStage());
            return pst;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        f.setId("" + id);
        System.out.println(id);
        return id;
    }

    public Boolean isAnyFlashcardSetActive() {
        return  !jdbcTemplate.query(
                "SELECT * FROM test.activeflashcard  WHERE userId=" + user.getUserId(),
                BeanPropertyRowMapper.newInstance(ActiveFlashCard.class)).isEmpty();
    }

    public int setFlashcardActive(int fId) {
        return  jdbcTemplate.update("INSERT INTO test.activeflashcard(userId, flashcardId) VALUES(?,?)"
                , user.getUserId(), fId);

    }

    public ActiveFlashCard getActiveFlashcard() {
        return  jdbcTemplate.queryForObject("SELECT * FROM  test.activeflashcard WHERE userId=" + user.getUserId()
                , BeanPropertyRowMapper.newInstance(ActiveFlashCard.class));

    }
    public FlashCard getFlashcard(String cardId) {
        return jdbcTemplate.queryForObject("SELECT * FROM test.fleshcard WHERE id =" + cardId,
                BeanPropertyRowMapper.newInstance(FlashCard.class));
    }

    public int updateFlashcard(FlashCard f, String cardId) {
        return jdbcTemplate.update("UPDATE test.fleshcard SET test.fleshcard.englishWord=? , test.fleshcard.polishWord=?, test.fleshcard.learningStage=? WHERE test.fleshcard.id =?"
                , f.getEnglishWord(), f.getPolishWord(), f.getLearningStage(), cardId);
    }

    public int deleteFlashcard(String cardId) {
        return jdbcTemplate.update("DELETE FROM test.fleshcard WHERE id =?", cardId);
    }

    private int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM test.fleshcard", Integer.class);
    }
}


