package com.bsadurski.fiszki.service;


import com.bsadurski.fiszki.entity.Category;
import com.bsadurski.fiszki.repository.CategoryRepository;
import com.bsadurski.fiszki.trash.Code404And403ErrorCasterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Service()
@RequestMapping("/categories")
public class CategoryService {

    @Autowired
    CategoryRepository repo;

    @Autowired
    Code404And403ErrorCasterBuilder errorCaster;

    @PostConstruct
    private void setErrorCaster() {
        errorCaster.setResourceMapper(BeanPropertyRowMapper.newInstance(Category.class))
                .setSqlFragment(this.repo.getSecuritySqLFragment());
    }

    public Category getCategory(String categoryId) {
//        errorCaster.build().cast(categoryId);
        return repo.getCategory(categoryId);
    }

    public List<Category> getCategories() {
        //Todo: ponizej walidacja
//        errorCaster.build().cast(categoryId);
        return repo.getCategories();
    }

//    @Transactional
    public int createCategory(Category f) {
        if (repo.checkIfCategoryExisted(f)) {
            throw new Error("400 - Kategoria już istnieje");
        }
        return repo.postCategory(f);
    }
}
