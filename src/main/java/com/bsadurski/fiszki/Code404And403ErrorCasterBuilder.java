package com.bsadurski.fiszki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component()
public class Code404And403ErrorCasterBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Authorisation user;

    private String sqlFragment;

    private RowMapper resClass;

    public Code404And403ErrorCasterBuilder(){}

    Code404And403ErrorCasterBuilder setSqlFragment(String sql) {
        this.sqlFragment = sql;
        return this;
    }

    // nie wiem jak zrobic inaczej zeby byla jedna metoda (chwilabym uniknac koniecnzosci wszedzie uzywania
    // BeanPropertyRowMapper.newInstance(IGetUserId.class))
    Code404And403ErrorCasterBuilder setResourceMapper(BeanPropertyRowMapper<Category> c) {
        this.resClass = c;
        return this;
    }

    Code404And403ErrorCasterBuilder setResourceMapper(RowMapper<IGetUserId> c) {
        this.resClass = c;
        return this;
    }

    Code404And403ErrorCaster build() {
        if (sqlFragment == null || resClass == null) throw
                new Error("zanim uruchomisz build nalezy wywołać metody setSqlFragment i setResourceMapper");
        return new Code404And403ErrorCaster(sqlFragment, resClass, user, jdbcTemplate);
    }
}

// nie umiem wstrzyknac zaleznosci za pomoca DI
class Code404And403ErrorCaster {

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

    void cast(String id) {
        errorHandler(id);
    }

    private void errorHandler(String id) {
        List<IGetUserId> foundResources = jdbcTemplate.query(sqlFragment + id, resClass);
        if (foundResources.isEmpty()) throw new Error("404 - nie znaleziono zasobu");
        if (foundResources.get(0).getUserId().compareTo(this.user.getUserId()) != 0) throw new Error("403 - mie masz uprawnień do zasobu");
    }

}


