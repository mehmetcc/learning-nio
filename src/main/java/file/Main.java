package file;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String contents = FileReader.read("read.txt");
        System.out.println(contents);
    }
}
