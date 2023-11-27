package multipart;

import net.mlk.jmson.Json;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MultiPartRequest {
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final HashMap<String, String> headers = new HashMap<>();
    private final String boundary = UUID.randomUUID().toString();
    private URI uri;
    private String method;
    boolean opened = false;

    public MultiPartRequest() {

    }

    public MultiPartRequest(String url) {
        this.setUrl(url);
    }

    public MultiPartRequest(String url, String method) {
        this.setUrl(url).setMethod(method);
    }

    public MultiPartRequest addJsonData(Json json) {
        for (String key : json.keySet()) {
            this.addValue(key, json.get(key));
        }
        return this;
    }

    public MultiPartRequest addValue(String name, Object value) {
        try {
            if (this.method.equalsIgnoreCase("get")) {
                String newQuery = this.uri.getQuery();
                if (newQuery == null) {
                    newQuery = name + "=" + value;
                } else {
                    newQuery += "&" + name + "=" + value;
                }
                this.uri = new URI(uri.getScheme(), uri.getAuthority(),
                        uri.getPath(), newQuery, uri.getFragment());
                return this;
            }
            StringBuilder builder = new StringBuilder();
            if (!this.opened) {
                builder.append("--").append(this.boundary);
                this.opened = true;
            }

            builder.append("\r\n").append(String.format("Content-Disposition: form-data; name=\"%s\"", name)).append("\r\n\r\n")
                    .append(value).append("\r\n")
                    .append("--").append(this.boundary);

            this.byteArrayOutputStream.write(builder.toString().getBytes());
            return this;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public MultiPartRequest addFile(String field, File file) {
        try {
            if (this.method.equalsIgnoreCase("get")) {
                throw new RuntimeException("Can't send file via GET request");
            }
            StringBuilder builder = new StringBuilder();
            if (!this.opened) {
                builder.append("--").append(this.boundary);
                this.opened = true;
            }
            builder.append("\r\n")
                    .append(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"", field)).append(file.getName()).append("\"\r\n")
                    .append("Content-Type: application/octet-stream\r\n\r\n");
            this.byteArrayOutputStream.write(builder.toString().getBytes());

            FileInputStream fileInputStream = new FileInputStream(file);
            int b;
            while ((b = fileInputStream.read()) != -1) {
                this.byteArrayOutputStream.write(b);
            }

            this.byteArrayOutputStream.write(("\r\n--" + this.boundary).getBytes());
            fileInputStream.close();
            return this;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public MultiPartResponse send() throws IOException {
        HttpURLConnection connection;
        if (this.uri.getScheme().equalsIgnoreCase("https")) {
            connection = (HttpsURLConnection) this.uri.toURL().openConnection();
        } else {
            connection = (HttpURLConnection) this.uri.toURL().openConnection();
        }
        connection.setDoInput(true);
        connection.setRequestMethod(this.method);
        this.headers.forEach(connection::setRequestProperty);

        if (!connection.getRequestMethod().equalsIgnoreCase("GET")) {
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.boundary);
            BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
            this.byteArrayOutputStream.write("--".getBytes());
            outputStream.write(this.byteArrayOutputStream.toByteArray());
            outputStream.flush();
        }
        return new MultiPartResponse(connection);
    }

    public MultiPartRequest setRequestHeader(String header, String value) {
        this.headers.put(header, value);
        return this;
    }

    public MultiPartRequest setRequestHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public MultiPartRequest setMethod(String method) {
        if (method.equalsIgnoreCase("PATCH")) {
            this.headers.put("X-HTTP-Method-Override", "PATCH");
            this.method = "POST";
        } else {
            this.method = method;
        }
        return this;
    }

    public MultiPartRequest setUrl(String url) {
        try {
            this.uri = new URI(url);
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
        return this;
    }

}
