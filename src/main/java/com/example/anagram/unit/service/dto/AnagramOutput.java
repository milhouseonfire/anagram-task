package com.example.anagram.unit.service.dto;

public class AnagramOutput extends AnagramInput {
    private final boolean valid;

    public AnagramOutput(String baseText, String anagram, boolean valid) {
        super(baseText, anagram);
        this.valid = valid;
    }

    public boolean getValid() {
        return valid;
    }
}

