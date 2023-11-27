package multipart;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class MultiPartResponse {
    private final HttpURLConnection connection;
    private final InputStream inputStream;
    private String response;

    public MultiPartResponse(HttpURLConnection connection) {
        InputStream inputStream;
        this.connection = connection;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException ex) {
            inputStream = connection.getErrorStream();
        }
        this.inputStream = inputStream;
    }

    public Map<String, List<String>> getHeaders() {
        return this.connection.getHeaderFields();
    }

    public int getResponseCode() {
        try {
            return this.connection.getResponseCode();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getResponse() {
        try {
            return this.inputStream == null ? null : new String(readResponse(this.inputStream), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public File saveFile(String path) {
        File file = new File(path);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(readResponse(this.inputStream));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return file;
    }

    private static byte[] readResponse(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int b;
        while ((b = inputStream.read()) != -1) {
            byteArrayOutputStream.write(b);
        }
        in.close();
        return byteArrayOutputStream.toByteArray();
    }
}
