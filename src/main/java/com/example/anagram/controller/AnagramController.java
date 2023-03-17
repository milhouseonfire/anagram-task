package com.example.anagram.controller;

import com.example.anagram.service.AnagramService;
import com.example.anagram.service.dto.AnagramInput;
import com.example.anagram.service.dto.AnagramOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anagrams")
public class AnagramController {
    private final AnagramService service;

    @Autowired
    public AnagramController(AnagramService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<AnagramOutput> createAnagram(@Validated @RequestBody AnagramInput input) {
        AnagramOutput output = this.service.evaluate(input);
        return new ResponseEntity<>(output, output.getValid() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
