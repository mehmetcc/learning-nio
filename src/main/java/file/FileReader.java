package file;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class FileReader {
    public static String read(String path) throws IOException {
        String content = "";
        FileInputStream inputStream = new FileInputStream(path);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        channel.read(buffer);
        content += new String(buffer.array(), StandardCharsets.UTF_8);

        inputStream.close();
        channel.close();

        return content;
    }
}
