package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {


    private ConsoleReader() {
    }

    public static void readDataFromConsole() {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String readedLine;

            do {
                readedLine = br.readLine();
            }
            while (!"Done!".equals(readedLine));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
