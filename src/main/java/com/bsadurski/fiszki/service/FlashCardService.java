package com.bsadurski.fiszki.service;

import com.bsadurski.fiszki.entity.History;
import com.bsadurski.fiszki.repository.HistoryRepository;
import com.bsadurski.fiszki.trash.Code404And403ErrorCasterBuilder;
import com.bsadurski.fiszki.entity.FlashCard;
import com.bsadurski.fiszki.entity.FlashCardWithUserId;
import com.bsadurski.fiszki.entity.IGetUserId;
import com.bsadurski.fiszki.repository.FlashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service()
public class FlashCardService {

    @Autowired
    FlashCardRepository flashcardRepo;

    @Autowired
    HistoryRepository historyRepo;

    @Autowired
    Code404And403ErrorCasterBuilder errorCaster;

    // W construktorze sie nie dalo bo Spring wyrzucil jakis Error
    @PostConstruct
    private void setErrorCaster() {
        errorCaster.setResourceMapper(new FlashCardService.FishCardRowMapper())
                .setSqlFragment(this.flashcardRepo.getSecuritySqLFragment());
    }

    public Page<FlashCard> getOnePage(Pageable pageable) {
        return flashcardRepo.getOnePage(pageable);
    }

    public List<FlashCard> getAll() {
        return flashcardRepo.getAll();
    }

    public int createFlashCard(FlashCard f) {
        int o = flashcardRepo.postFlashcard(f);
        System.out.println(o);
//        errorCaster.build().cast(f.getId());
        History h = new History();
        h.setOperationType("register");
        h.setFlashcardId(o);
        this.historyRepo.postHistory(h);
        if (!this.flashcardRepo.isAnyFlashcardSetActive()) {
            this.flashcardRepo.setFlashcardActive(o);
        }
        return o;
    }

    public FlashCard getFlashCard(String id) {
//        errorCaster.build().cast(id);
        return flashcardRepo.getFlashcard(id);
    }

    public int updateFlashCard(String id, FlashCard f) {
//        errorCaster.build().cast(id);
        History h = new History();
        h.setOperationType("update");
        h.setId(Integer.parseInt(id));
        h.setFlashcardId(Integer.parseInt(f.getId()));
        this.historyRepo.postHistory(new History());
        return flashcardRepo.updateFlashcard(f, id);
    }

    public int deleteFlashCard(@PathVariable String id) {
//        errorCaster.build().cast(id);
        return flashcardRepo.deleteFlashcard(id);
    }

    private class FishCardRowMapper implements RowMapper<IGetUserId> {

        @Override
        public FlashCardWithUserId mapRow(ResultSet rs, int rowNum) throws SQLException {
            FlashCardWithUserId o = new FlashCardWithUserId();
            o.setUserId(rs.getInt("userId"));
            o.setPolishWord(rs.getString("polishWord"));
            return o;
        }
    }
}
