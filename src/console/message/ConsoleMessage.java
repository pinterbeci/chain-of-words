package console.message;

import java.util.List;

/**
 * A console-ra megjelenítendő felhasználónak szóló üzenezeneket kiíró osztály.
 */
public class ConsoleMessage {

    private ConsoleMessage() {
    }

    public static void messageToUserBeforeReadInput() {
        System.out.println("Az alábbi alkalmazás egy szóláncot vizsgál meg." +
                " Eldönti, hogy a láncban szereplő szavak" +
                " megfelelnek a következő kondicíónak:\n" +
                "'Minden szó egy betűben különbözik az előtte található szótól.'\n" +
                "'Ez a különbség lehet betű törlése, betű beszúrása vagy betű kicserélése más betűre.'");
        System.out.println("__________________________________________________________________________");
        System.out.println("Soronként szóközzel elválasztva adhat meg szavakat, majd 'Enter'-t nyomva\n" +
                "az alkalmaz jelzi számára, hogy megfelelő szóláncot adott-e meg.");
        System.out.println("'Done!' parancsot használva megállíthatja a program működését!");
        System.out.println("__________________________________________________________________________");
    }

    public static void messageBeforeInput() {
        System.out.println("Kérem adjon meg egy szóláncot (szavakat, szóközzel elválasztva):");
    }

    public static void writeChainOfWords(List<String> chainOfWords) {

        if (chainOfWords == null) {
            System.out.println("Ön végzett!");
            return;
        }

        if (chainOfWords.isEmpty())
            System.out.println("Nem megfelelő szólánc!");


        if (!chainOfWords.isEmpty()) {
            System.out.println("Megfelelő szólánc:");
            for (String currentWord : chainOfWords)
                System.out.print(currentWord + " ");

            System.out.println("\n__________________________________________________________________________");
        }

    }
}
