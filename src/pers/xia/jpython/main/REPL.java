package pers.xia.jpython.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REPL {

    public static void main(String[] args) {
        driverLoop();
    }

    private static void driverLoop() {
        while (true) {
            System.out.print(">>> ");
            String input = readInput();
            if (input == null) {
                break;
            }
            if (input.equals("exit")) {
                break;
            }
            try {
                Object result = evalLine(input);
                System.out.println(result);
            } catch (Exception e) {
                // Handle exceptions here
                e.printStackTrace();
            }
        }
    }

    private static String readInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object evalLine(String line) {
        // Implement your evaluation logic here
        // Replace this with the actual evaluation code
        byte[] bytes = line.getBytes();

        System.out.println(bytes);
        return bytes;
    }
}
