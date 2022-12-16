package com.bsadurski.fiszki;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService s;

    @GetMapping("{categoryId}")
    public Category getCategory(@PathVariable String categoryId) { return s.getCategory(categoryId);
    }

    @PostMapping
    public int createCategory(@Valid @RequestBody Category f) {
        return s.createCategory(f);
    }
}
