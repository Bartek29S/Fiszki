package com.bsadurski.fiszki.controller;


import com.bsadurski.fiszki.entity.Category;
import com.bsadurski.fiszki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/categories")
public class CategoryController {

    CategoryService s;

    public CategoryController(CategoryService s) {
        this.s = s;
    }

    @GetMapping("{categoryId}")
    public Category getCategory(@PathVariable String categoryId) { return s.getCategory(categoryId);
    }

    @GetMapping()
    public List<Category> getCategories() { return s.getCategories();
    }

    @PostMapping
    public int createCategory(@Valid @RequestBody Category f) {
        return s.createCategory(f);
    }
}
