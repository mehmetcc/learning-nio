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

    public static void write(String path, String content) throws IOException {
        File myFile = new File(path);
        myFile.createNewFile();
        byte[] message = content.getBytes(StandardCharsets.US_ASCII);

        FileOutputStream outputStream = new FileOutputStream(myFile, false);
        FileChannel channel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (byte b : message) {
            buffer.put(b);
        }
        buffer.flip();
        channel.write(buffer);

        outputStream.close();
        channel.close();
    }
}
