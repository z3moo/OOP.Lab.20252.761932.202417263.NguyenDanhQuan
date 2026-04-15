package hust.soict.itep.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) {
        String filename = "myFile.exe";
        byte[] inputBytes = { 0 };
        long startTime;
        long endTime;

        try {
            inputBytes = Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        startTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (byte b: inputBytes) {
            sb.append((char) b);
        }
        String outputString = sb.toString();
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(outputString.length());
    }
}
