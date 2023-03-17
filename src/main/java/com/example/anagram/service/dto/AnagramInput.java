package com.example.anagram.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AnagramInput {
    @NotBlank(message = "Subject is required")
    @Size(min = 2, max = 65535, message = "Subject should have at least 2 characters")
    private final String subject;

    @NotBlank(message = "Anagram is required")
    @Size(min = 2, max = 65535, message = "Anagram should have at least 2 characters")
    private final String anagram;

    public AnagramInput(String subject, String anagram) {
        this.subject = subject;
        this.anagram = anagram;
    }

    public String getSubject() {
        return subject;
    }

    public String getAnagram() {
        return anagram;
    }
}
