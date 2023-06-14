package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSorter {

    private static List<String> sort(List<String> wordList) {
        return sort(wordList, new ArrayList<>());
    }

    private static List<String> sort(List<String> wordList, List<String> returnValue) {

        if (wordList == null)
            return returnValue;

        if (wordList.isEmpty())
            return returnValue;

        if (wordList.size() == 1)
            return returnValue;

        String firstElement = wordList.get(0);
        boolean removeFirstElement = false;

        for (int i = 1; i < wordList.size(); i++) {
            if (hasDeletedCharInCurrentWord(firstElement, wordList.get(i))) {
                returnValue.add(wordList.get(i));
                removeFirstElement = true;
            }
        }
        wordList.removeAll(returnValue);

        for (int i = 1; i < wordList.size(); i++) {
            if (hasInsertedCharacterInTargetWord(firstElement, wordList.get(i))) {
                returnValue.add(wordList.get(i));
                removeFirstElement = true;
            }
        }
        wordList.removeAll(returnValue);

        for (int i = 1; i < wordList.size(); i++) {
            if (hasModifiedCharacter(firstElement, wordList.get(i))) {
                returnValue.add(wordList.get(i));
                removeFirstElement = true;
            }
        }
        wordList.removeAll(returnValue);
        if (removeFirstElement) {
            wordList.remove(0);
            returnValue.add(firstElement);
        }

        return sort(wordList, returnValue);
    }


    //hány karakterben térnek el egymástól két string, ha azok hossza megegyezik
    private static boolean hasModifiedCharacter(String currentWord, String targetWord) {
        if (currentWord.equals(targetWord))
            return false;
        int differentCharacters = 0;
        //ha megegyezik a hosszuk, megnézzük hány karakter eltérés van közöttük
        //az adott indexeket figyelembe véve
        if (currentWord.length() == targetWord.length()) {
            for (int i = 0; i < currentWord.length(); i++) {
                if (currentWord.charAt(i) != targetWord.charAt(i)) {
                    differentCharacters++;
                }
            }
            return differentCharacters == 1;
        }
        return false;
    }

    private static boolean hasDeletedCharInCurrentWord(String currentWord, String targetWord) {
        //1. rendezési szempont
        //első string hosszabb egy karakterrel
        //tehát a target törlésen esett át
        return rightInsertOrDeleteOrModification(currentWord, targetWord, false);
    }

    private static boolean hasInsertedCharacterInTargetWord(String currentWord, String targetWord) {
        //2. rendezési szempont
        //első string rövidebb egy karakterrel
        //tehát a target bővítésen esett át
        return rightInsertOrDeleteOrModification(currentWord, targetWord, true);
    }

    //ha különböző a méretűk,akkor ergó:
    //bővítés vagy törlés történhetett
    private static boolean rightInsertOrDeleteOrModification(String currentWord, String targetWord, boolean insert) {
        //egy elemmel való bővítés/törlés megengedett csakis
        int subtractOfLengths = Math.subtractExact(currentWord.length(), targetWord.length());
        int shorterWordLength = Math.min(currentWord.length(), targetWord.length());
        int longerWordLength = Math.max(currentWord.length(), targetWord.length());
        int longerStringCurrentIndex = longerWordLength - 1;

        String shorterWord = "";
        String longerWord = "";
        int firstDifferentCharIndex = 0;

        if (!insert && subtractOfLengths == -1)
            return false;

        if (insert && subtractOfLengths == 1)
            return false;

        if (subtractOfLengths == -1) {
            shorterWord = currentWord;
            longerWord = targetWord;
        }

        if (subtractOfLengths == 1) {
            shorterWord = targetWord;
            longerWord = currentWord;
        }

        if (subtractOfLengths == 0)
            return false;

        //meg kell nézni, pont ebben az egy karakterben tér-e el az eggyel hosszabb másik stringtől
        //ezzel viszgálom, hogy a hosszabb string nem e az első karakterében tér el a rövidebbtől
        for (int i = shorterWordLength - 1; i >= 0; i--, longerStringCurrentIndex--) {
            if (shorterWord.charAt(i) != longerWord.charAt(longerStringCurrentIndex)) {
                //megkeresem azt a karaktert, amelyben eltérnek
                firstDifferentCharIndex = longerStringCurrentIndex;

                //abban az esetben, ha itt nem talál különbséget, feltételezve a hosszabb string esetén
                //az legelső indexű karakter lett beszúrva

                //ha nem legelső lett beszúrva, akkor meg kell nézni, hogy ezt eltávolítva a hosszabb stringből,
                //megegyezik e a két string
                StringBuilder wordBuilder = new StringBuilder(longerWord);
                wordBuilder.deleteCharAt(firstDifferentCharIndex);
                return wordBuilder.toString().equals(shorterWord);
            }
        }
        //ha az első karakterben térnek el
        boolean firstCharModified = shorterWord.charAt(0) != longerWord.charAt(0);
        if (firstCharModified) {
            StringBuilder wordBuilder = new StringBuilder(longerWord);
            wordBuilder.deleteCharAt(firstDifferentCharIndex);
            return wordBuilder.toString().equals(shorterWord);
        }
        return false;
    }


    public static void main(String[] args) {

        List<String> wordList2 = new ArrayList<>(
                new ArrayList<>(
                        Arrays.asList("coat", "hat", "hot", "dog", "cat", "hog", "cot", "oat"))
        );

        System.out.println(
                sort(wordList2)
        );
    }
}
