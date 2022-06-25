package file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {
    @Test
    void read_whenNonExistingFilePathGivenShouldThrowFileNotFoundException() throws IOException {
        String path = "i_dont_exist.txt";

        Exception thrown = assertThrows(FileNotFoundException.class, () -> {
            FileReader.read(path);
        });

        assertThat(thrown).isNotNull()
                .isInstanceOf(FileNotFoundException.class)
                .hasMessageContaining("(No such file or directory)");
    }

    @Test
    void read_whenValidFilePathGivenShouldReadFileContentsIntoString() throws IOException {
        String path = "read.txt";

        String result = FileReader.read(path);

        assertThat(result).isNotEmpty()
                .contains("Lorem ipsum dolor sit amet");
    }

    @Test
    void write_whenValidStringGivenShouldWriteContentsIntoFile(@TempDir Path directory) throws IOException {
        Path path = directory.resolve("tmp.txt");

        FileReader.write(path.toString(), "Some content");

        assertThat(Files.exists(path)).isTrue();
        assertThat(Files.readAllLines(path)).isNotEmpty()
                .element(0)
                .isEqualTo("Some content");
    }
}