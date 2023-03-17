package com.example.anagram.unit.service;

import com.example.anagram.service.AnagramService;
import com.example.anagram.service.dto.AnagramInput;
import com.example.anagram.service.dto.AnagramOutput;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnagramServiceTest {

    private AnagramService service;

    @BeforeEach
    public void setup() {
        service = new AnagramService();
    }

    @Test
    public void testWordIsNotAnagramOfItSelf() {
        AnagramInput input = new AnagramInput("teacher", "teacher");
        AnagramOutput output = service.evaluate(input);
        Assertions.assertFalse(output.getValid());
    }

    @Test
    public void testAnagrams() {
        AnagramInput input = new AnagramInput("teacher", "cheater");
        AnagramOutput output = service.evaluate(input);
        Assertions.assertTrue(output.getValid());
    }

    @Test
    public void testNotAnagrams() {
        AnagramInput input = new AnagramInput("teacher", "cheating");
        AnagramOutput output = service.evaluate(input);
        Assertions.assertFalse(output.getValid());
    }

    @Test
    public void testNoSpecialChars() {
        AnagramInput input = new AnagramInput("teach4er", "ch333!%$#$.,%^&*()eating");
        AnagramOutput output = service.evaluate(input);
        Assertions.assertFalse(output.getValid());
    }

    @Test
    public void testSpaces() {
        AnagramInput input = new AnagramInput("i am lord voldemort", "tom marvolo riddle");
        AnagramOutput output = service.evaluate(input);
        Assertions.assertTrue(output.getValid());
    }

    @Test
    public void testUpperCaseDiffs() {
        AnagramInput input = new AnagramInput("IAmLordVoldemort", "TomMarvoloRiddle");
        AnagramOutput output = service.evaluate(input);
        Assertions.assertTrue(output.getValid());
    }

    @Test
    public void testLongAnagrams() {

        String realLongText = RandomStringUtils.random(21474830, true, false);
        // Reverse the string
        StringBuilder sb = new StringBuilder(realLongText);
        sb.reverse();
        AnagramInput input = new AnagramInput(realLongText, sb.toString());
        AnagramOutput output = service.evaluate(input);
        Assertions.assertTrue(output.getValid());
    }
}
