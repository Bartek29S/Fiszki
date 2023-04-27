package com.bsadurski.fiszki.controller;


import com.bsadurski.fiszki.entity.FlashCard;
import com.bsadurski.fiszki.service.FlashCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/fiszki")
public class FlashCardController {


    FlashCardService s;

    public FlashCardController(FlashCardService s) {
        this.s = s;
    }

    @GetMapping
    public Page<FlashCard> getOnePage(Pageable pageable) { return s.getOnePage(pageable);
    }

    @GetMapping("/all")
    public List<FlashCard> getAll() { return s.getAll();
    }

    @PostMapping
    public int createFlashCard(@Valid @RequestBody FlashCard f) {
        return s.createFlashCard(f);
    }

    @GetMapping("{id}")
    public FlashCard getFlashCard(@PathVariable String id) {
        return s.getFlashCard(id);
    }

    // ToDo/: czy tutaj dac nowa klase bez id (id juz jest w url)
    @PostMapping("{id}")
    public int updateFlashCard(@PathVariable String id, @Valid @RequestBody FlashCard f) {
        return s.updateFlashCard(id, f);
    }

    @DeleteMapping("{id}")
    public int deleteFlashCard(@PathVariable String id) {
        return s.deleteFlashCard(id);
    }
}
