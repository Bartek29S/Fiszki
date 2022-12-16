package com.bsadurski.fiszki;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Service()
@RequestMapping("/categories")
public class CategoryService {

    @Autowired
    CategoryRepository repo;

    @Autowired
    Code404And403ErrorCasterBuilder errorCaster;

    // W construktorze sie nie dalo bo Spring wyrzucil jakis Error)
    @PostConstruct
    private void setErrorCaster() {
        //tuta nie wiem czemu zwreacane BeanPropertyRowMapper<Category> jest nie zgodne z RowMapper<IGetUserId> ani nawet z BeanPropertyRowMapper<IGetUserId>
        errorCaster.setResourceMapper(BeanPropertyRowMapper.newInstance(Category.class))
                .setSqlFragment(this.repo.getSecuritySqLFragment());
    }

    public Category getCategory(String categoryId) {
        errorCaster.build().cast(categoryId);
        return repo.getCategory(categoryId);
    }

    public int createCategory(Category f) {
        if (repo.checkIfCategoryExisted(f)) {
            throw new Error("400 - Kategoria już istnieje");
        }
        return repo.postCategory(f);
    }
}
