package com.bsadurski.fiszki.service;

import com.bsadurski.fiszki.entity.ActiveFlashCard;
import com.bsadurski.fiszki.entity.FlashCard;
import com.bsadurski.fiszki.entity.History;
import com.bsadurski.fiszki.repository.FlashCardRepository;
import com.bsadurski.fiszki.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Service
public class LearningService {

    @Autowired
    private FlashCardRepository flashCardRepo;

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping()
    public FlashCard getNextFlashcard() {
        ActiveFlashCard a = this.flashCardRepo.getActiveFlashcard();
        return this.flashCardRepo.getFlashcard("" +a.getFlashcardId());
    }

    @PostMapping()
    public int registerFlashcardLearnProgress(@Valid FlashCard card) {
        FlashCard f = this.flashCardRepo.getFlashcard(card.getId());
        String operationType = "decrease";
        if (f.getLearningStage() < card.getLearningStage()) {
            f.setLearningStage(f.getLearningStage() + 1);
            operationType = "increase";
        } else {
            f.setLearningStage(f.getLearningStage() - 1);
        }
        History h = new History();
        h.setFlashcardId(Integer.parseInt(f.getId()));
        h.setOperationType(operationType);
        System.out.println(f.getId());
        this.historyRepository.postHistory(h);
        return flashCardRepo.updateFlashcard(f, f.getId());
    }
}
