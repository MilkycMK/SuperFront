package net.mlk.adolffront.http;

import net.mlk.adolffront.Environment;
import net.mlk.adolffront.screens.todo.TodoElement;
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

    public static int postTodo(TodoElement todo) {
        return -1;
    }

    public static void makeLogoutRequest() throws IOException {
        makeTokenRequest(Environment.LOGOUT, HttpMethod.POST, new Json());
    }

    public static MultiPartRequest.Response makeTokenRequest(String url, HttpMethod method, Json json) throws IOException {
        MultiPartRequest request = new MultiPartRequest(url, method)
                .setRequestHeader("Authorization", Environment.token)
                .addJsonData(json);
        return makeTokenRequest(request);
    }

    public static MultiPartRequest.Response makeTokenRequest(MultiPartRequest request) throws IOException {
        MultiPartRequest.Response response = request.send();
        if (response.getResponseCode() == 401) {
//            AdolfFront.deleteUserProfile();
            return null;
        }
        return response;
    }

    public static MultiPartRequest.Response makeRequest(String url, HttpMethod method, Json json) throws IOException {
        return new MultiPartRequest(url, method)
                .addJsonData(json)
                .send();
    }

}
