package com.example.anagram.service;

import com.example.anagram.service.dto.AnagramInput;
import com.example.anagram.service.dto.AnagramOutput;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AnagramService {
    public AnagramOutput evaluate(AnagramInput input) {
        // All to lowercase , remove white spaces , convert to array.
        String subject = input.getSubject().toLowerCase();
        String possibleAnagram = input.getAnagram().toLowerCase();
        if (subject.equals(possibleAnagram)) {
            return new AnagramOutput(input.getSubject(), input.getAnagram(), false);
        }
        char[] basTextAsArray = subject.replaceAll("[^a-zA-Z]", "").toCharArray();
        char[] possibleAnagramAsArray = possibleAnagram.replaceAll("[^a-zA-Z]", "").toCharArray();
        Arrays.sort(basTextAsArray);
        Arrays.sort(possibleAnagramAsArray);
        return new AnagramOutput(input.getSubject(), input.getAnagram(), Arrays.equals(basTextAsArray, possibleAnagramAsArray));
    }
}
