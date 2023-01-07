package com.bsadurski.fiszki.trash;

import com.bsadurski.fiszki.entity.Authorisation;
import com.bsadurski.fiszki.entity.IGetUserId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

// nie umiem wstrzyknac zaleznosci za pomoca DI
public class Code404And403ErrorCaster {

    JdbcTemplate jdbcTemplate;

    Authorisation user;

    private String sqlFragment;

    private RowMapper resClass;

    public Code404And403ErrorCaster(String s, RowMapper r, Authorisation u, JdbcTemplate t) {
        sqlFragment = s;
        resClass = r;
        jdbcTemplate = t;
        user = u;
    }

    public void cast(String id) {
        errorHandler(id);
    }

    private void errorHandler(String id) {
        List<IGetUserId> foundResources = jdbcTemplate.query(sqlFragment + id, resClass);
        if (foundResources.isEmpty()) throw new Error("404 - nie znaleziono zasobu");
        if (foundResources.get(0).getUserId().compareTo(this.user.getUserId()) != 0) throw new Error("403 - mie masz uprawnień do zasobu");
    }

}



