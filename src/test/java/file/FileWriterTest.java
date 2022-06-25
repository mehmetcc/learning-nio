package file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {
    @Test
    void write_whenValidStringGivenShouldWriteContentsIntoFile(@TempDir Path directory) throws IOException {
        Path path = directory.resolve("tmp.txt");

        FileWriter.write(path.toString(), "Some content");

        assertThat(Files.exists(path)).isTrue();
        assertThat(Files.readAllLines(path)).isNotEmpty()
                .element(0)
                .isEqualTo("Some content");
    }
}