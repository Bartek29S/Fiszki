package com.bsadurski.fiszki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service()
public class FlashCardService {

    @Autowired
    FlashCardRepository repo;

    @Autowired
    Code404And403ErrorCasterBuilder errorCaster;

    // W construktorze sie nie dalo bo Spring wyrzucil jakis Error
    @PostConstruct
    private void setErrorCaster() {
        errorCaster.setResourceMapper(new FlashCardService.FishCardRowMapper())
                .setSqlFragment(this.repo.getSecuritySqLFragment());
    }

    public Page<FlashCard> getAll(Pageable pageable) { return repo.getAll(pageable);
    }

    public int createFlashCard(FlashCard f) {
        errorCaster.build().cast(f.getId());
        return repo.postFlashcard(f);
    }

    public FlashCard getFlashCard(String id) {
        errorCaster.build().cast(id);
        return repo.getFlashcard(id);
    }

    public int updateFlashCard(String id, FlashCard f) {
        errorCaster.build().cast(id);
        return repo.updateFlashcard(f, id);
    }

    public int deleteFlashCard(@PathVariable String id) {
        errorCaster.build().cast(id);
        return repo.deleteFlashcard(id);
    }

    // 1.FlashCardWithUserId nie jest beanem (patrz uwaga w FlashCardWithUserId) i temu chyba nie mozna dac tak wbudowanego mapera
    // BeanPropertyRowMapper.newInstance
    // 2.druga sprawa gdzie ten mapper ma byc (czy nie lepiej w repository)
    // 3. (pozniejsza uwaga) Zrobilem interface IGetUserId wiec chyba nie ma sensu trzymac osobnej klasy i tego mapera
    // i lepiej rozbudowac o ten interfes istniejaca klase
    private class FishCardRowMapper implements RowMapper<IGetUserId> {

        @Override
        public FlashCardWithUserId mapRow(ResultSet rs, int rowNum) throws SQLException {
            FlashCardWithUserId o = new FlashCardWithUserId();
            o.setUserId(rs.getString("userId"));
            o.setPolishWord(rs.getString("polishWord"));
            return o;
        }
    }
}
