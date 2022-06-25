package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class CopyFile {
    private final Path from;
    private final Path to;

    private CopyFile(Path from, Path to) {
        this.from = from;
        this.to = to;
    }

    private CopyFile(CopyFileFromState fromState, Path to) {
        this(fromState.from, to);
    }

    public static CopyFileFromState from(Path p) {
        return new CopyFileFromState(p);
    }

    public record CopyFileFromState(Path from) {
        public CopyFile to(Path p) {
            return new CopyFile(this, p);
        }
    }

    public void copy() throws IOException {
        FileInputStream inputStream = new FileInputStream(from.toFile());
        FileOutputStream outputStream = new FileOutputStream(createIfNotExists(to));

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();
            int r = inputChannel.read(buffer);

            if (r == -1) break;

            buffer.flip();
            outputChannel.write(buffer);
        }
    }

    private File createIfNotExists(Path path) throws IOException {
        File myFile = path.toFile();
        myFile.createNewFile();
        return myFile;
    }
}

