package com.bsadurski.fiszki.trash;

import com.bsadurski.fiszki.entity.Authorisation;
import com.bsadurski.fiszki.entity.Category;
import com.bsadurski.fiszki.entity.IGetUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component()
@Scope("prototype")
public class Code404And403ErrorCasterBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Authorisation user;

    private String sqlFragment;

    private RowMapper resClass;

    public Code404And403ErrorCasterBuilder(){}

    public  Code404And403ErrorCasterBuilder  setSqlFragment(String sql) {
        this.sqlFragment = sql;
        return this;
    }

    public Code404And403ErrorCasterBuilder setResourceMapper(BeanPropertyRowMapper<Category> c) {
        this.resClass = c;
        return this;
    }

    public Code404And403ErrorCasterBuilder setResourceMapper(RowMapper<IGetUserId> c) {
        this.resClass = c;
        return this;
    }

    public Code404And403ErrorCaster build() {
        if (sqlFragment == null || resClass == null) throw
                new Error("zanim uruchomisz build nalezy wywołać metody setSqlFragment i setResourceMapper");
        return new Code404And403ErrorCaster(sqlFragment, resClass, user, jdbcTemplate);
    }
}


