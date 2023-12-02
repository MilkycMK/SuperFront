package net.mlk.adolffront.http;

import net.mlk.adolffront.Environment;
import net.mlk.jmson.Json;
import java.io.IOException;

public class AdolfServer {

    public static MultiPartRequest.Response makeLoginRequest(String login, String password) throws IOException {
        Json json = new Json()
                .append("login", login)
                .append("password", password);
        return makeRequest(Environment.LOGIN, HttpMethod.POST, json);
    }

    public static MultiPartRequest.Response makeRegisterRequest(String login, String password) throws IOException {
        Json json = new Json()
                .append("login", login)
                .append("password", password);
        return makeRequest(Environment.REGISTER, HttpMethod.POST, json);
    }

    public static void makeLogoutRequest() throws IOException {
        new MultiPartRequest(Environment.LOGOUT, HttpMethod.POST)
                .setRequestHeader("Authorization", Environment.token)
                .send();
    }

    public static MultiPartRequest.Response makeTokenRequest(String url, HttpMethod method, Json json) throws IOException {
        return new MultiPartRequest(url, method)
                .setRequestHeader("Authorization", Environment.token)
                .addJsonData(json)
                .send();
    }

    public static MultiPartRequest.Response makeRequest(String url, HttpMethod method, Json json) throws IOException {
        return new MultiPartRequest(url, method)
                .addJsonData(json)
                .send();
    }

}
