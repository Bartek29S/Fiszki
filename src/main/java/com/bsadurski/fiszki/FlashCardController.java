package com.bsadurski.fiszki;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/fiszki")
public class FlashCardController {

    @Autowired
    FlashCardService s;

    @GetMapping
    public Page<FlashCard> getAll(Pageable pageable) { return s.getAll(pageable);
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
