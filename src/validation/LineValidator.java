package validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A beolvasott sorról eldönti megfelelő szólánc e.
 */
public class LineValidator {


    private LineValidator(){

    }
    /**
     * Elkészíti a beolvasott sor által a szólistát
     *
     * @param currentLine a beolvasott
     * @return szólánc lista, mely megfelel a kondicíóknak
     */
    public static List<String> createChainOfString(String currentLine) {

        List<String> returnValue = new ArrayList<>();
        String[] splitCurrentLine = currentLine.split(" ");
        List<String> wordList = Arrays.asList(splitCurrentLine);
        Collections.sort(wordList);

        if (validateWords(wordList))
            returnValue = wordList;

        return returnValue;
    }

    private static boolean validateWords(List<String> wordsList) {

        for (int i = 0; i < wordsList.size() - 1; i++) {
            boolean comparisonOfWords = compareToWords(wordsList.get(i), wordsList.get(i + 1));
            if (!comparisonOfWords)
                return false;
        }

        return true;
    }

    private static boolean compareToWords(String currentWord, String nextWord) {
        int lengthOfCurrentWord = currentWord.length();
        int lengthOfNextWord = nextWord.length();


        if (lengthOfCurrentWord == lengthOfNextWord)
            return comparisonProcedure(currentWord, nextWord, false);
        else
            return comparisonProcedure(currentWord, nextWord, true);

    }

    private static boolean comparisonProcedure(String currentWord, String nextWord, boolean isDifferentLengths) {

        if (currentWord != null && nextWord != null) {

            if (currentWord.equals(nextWord))
                return false;

            char[] currentWordCharArray = currentWord.toCharArray();
            char[] nextWordCharArray = nextWord.toCharArray();
            int currentIndex = 0;
            int limit = Math.min(currentWordCharArray.length, nextWordCharArray.length);


            if (isDifferentLengths) {

                List<String> currentWordCharList = Stream.of(currentWord.toCharArray()).map(String::valueOf).collect(Collectors.toList());
                List<String> nextWordCharList = Stream.of(nextWord.toCharArray()).map(String::valueOf).collect(Collectors.toList());

                List<String> differences = currentWordCharList.stream()
                        .filter(element -> nextWordCharList.contains(element))
                        .collect(Collectors.toList());

                return !differences.isEmpty();

            }


            while (currentIndex < limit) {
                char currentWordCharacter = currentWordCharArray[currentIndex];
                char nextWordCharacter = nextWordCharArray[currentIndex];
                if (currentWordCharacter != nextWordCharacter) {
                    return true;
                }
                currentIndex++;
            }

        }
        return false;
    }
}