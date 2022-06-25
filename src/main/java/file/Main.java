package file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path from = Paths.get("read.txt");
        Path to = Paths.get("write.txt");

        CopyFile.from(from).to(to).copy();
    }
}
