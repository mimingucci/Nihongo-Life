package com.nihongo.admin.letter;

import com.nihongo.common.entity.Letter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/letter")
public class LetterApiController {
    private LetterService service;

    public LetterApiController(LetterService service) {
        this.service = service;
    }
    @GetMapping(path = "/search", produces = "application/json")
    public ResponseEntity<?> searchLetter(String letter){
        List<Letter> letters=service.search(letter);
        return ResponseEntity.ok(letters);
    }



}
