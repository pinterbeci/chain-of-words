package reader;

import console.message.ConsoleMessage;
import validation.LineValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * A console-ról való felhasználói adatok beolvasását, és adat elkészítését elvégző osztály.
 */
public class ConsoleReader {


    private ConsoleReader() {
    }

    /**
     * Megvalóstjuk a console-ról való beolvasást.
     */
    public static void readDataFromConsole() {
        BufferedReader br;
        List<String> chainOfWords;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String readedLine;
            ConsoleMessage.messageToUserBeforeReadInput();
            do {
                ConsoleMessage.messageBeforeInput();
                readedLine = br.readLine();
                chainOfWords = LineValidator.createChainOfString(readedLine);
                ConsoleMessage.writeChainOfWords(chainOfWords);
            }
            while (!"Done!".equals(readedLine));

        } catch (IOException e) {
            System.out.println("Hiba a sorok beolvasása során!");
            throw new RuntimeException(e);
        }
    }


}
