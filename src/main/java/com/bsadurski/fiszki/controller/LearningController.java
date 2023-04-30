package com.bsadurski.fiszki.controller;


import com.bsadurski.fiszki.entity.FlashCard;
import com.bsadurski.fiszki.service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/learning")
public class LearningController {

    @Autowired
    private LearningService service;

    @GetMapping()
    public FlashCard getNextFlashcard() {
        return service.getNextFlashcard();
    }

    @PutMapping()
    public int registerFlashcardLearnProgress(@Valid @RequestBody FlashCard f) {
        System.out.println(f.getEnglishWord());
        return service.registerFlashcardLearnProgress(f);
    }


}
