# Assigment

Write a Java program that checks if two texts are anagrams of each other.  
Please use the English Wikipedia entry for the definition of anagram. The solution has to be in Java or Kotlin.
Feel free to use your favorite IDE, unit test frameworks, automated build system, etc.
You can prioritize however you like (performance, readability, extensibility, …).

----

## What is an Anagram?

An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using
all the original letters exactly once. For example, the word anagram itself can be rearranged into nag a ram,
as well as the word binary into brainy and the word adobe into abode.

The original word or phrase is known as the subject of the anagram. Any word or phrase that exactly reproduces
the letters in another order are an anagram.

### From the definition:

1- The total amount of letters present in the subject and the anagram is not necessarily the same.

2- The amount of words in the subject and the anagram is not necessarily the same.

3 - A word cannot be considered an anagram of itself since letters need to be rearranged.

----

## Proposed Solution

- The solution will check if the letters used for the text are exactly the same as those in the other text.
- The solution will not check that the anagram is composed of actual English words. For instance: Guide and ediug will
  be a valid case.
- Characters different from a-z should not be considered when evaluating the anagram.

## Implementation

### Base

I imagined this quick coding challenge as an API allowing people to store their anagrams. Although it is not fully
implemented, that would be my "product idea" for the assignment.

This is a very simple code done in a few hours; I chose to create a quick springboot application that exposes one
endpoint
that accepts Posting an Anagram to be evaluated and will return a validated one.

Why did I go this way?

- This solution is a good starting point for a further call with a dev since it provides the basis for talking about a
  backend service that provides a REST API.
- At some point, I can present in a basic way how I split responsibilities.
    - In this case, there are some Dtos, a very basic service, and a Controller.
- Different types of tests can be done (Unit and functional).

### Spec

The application exposes one endpoint for the user to post their anagrams.

- If the anagram is correct, a valid Anagram will be returned along with a 200 status code.
- If the anagram is not correct, an invalid Anagram will be returned along with a 400 status code.

### Tests

- There are basic tests that allowed me to complete the basic functionality by following TDD.
- Endpoint is tested in case of extension/modifications in the controller.

### How to use

- Open with IntelliJ and install the dependencies.
- Run AnagramApplication.
- To play with the code, you can:
    - Open http://localhost:8080/swagger-ui/index.html (Documentation included) - to use directly from the browser
    - Perform a POST request to http://localhost:8080/anagrams/
