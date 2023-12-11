package net.mlk.adolffront.http;

import net.mlk.adolffront.AdolfFront;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.screens.todo.TodoElement;
import net.mlk.adolffront.screens.todo.TodoFile;
import net.mlk.jmson.Json;
import net.mlk.jmson.JsonList;
import net.mlk.jmson.utils.JsonConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static int postTodo(TodoElement todo) throws IOException {
        Json json = JsonConverter.convertToJson(todo);
        MultiPartRequest request = new MultiPartRequest(Environment.TODO, HttpMethod.POST)
                .addJsonData(json)
                .addFiles("files", todo.getFiles().stream()
                        .map(TodoFile::getFile)
                        .collect(Collectors.toList()));
        MultiPartRequest.Response response = makeTokenRequest(request);
        return Integer.parseInt(response.getHeaders().get("Location").get(0).split("/")[2]);
    }

    public static MultiPartRequest.Response deleteTodo(int id) throws IOException {
        return makeTokenRequest(Environment.TODO + "/" + id, HttpMethod.DELETE, new Json());
    }

    public static MultiPartRequest.Response updateTodo(TodoElement element) throws IOException {
        Json json = JsonConverter.convertToJson(element);
        MultiPartRequest.Response response = makeTokenRequest(Environment.getTodoElementUrl(element.getId()), HttpMethod.PATCH, json);
        for (TodoFile file : element.getDeletedFiles()) {
            MultiPartRequest.Response res= makeTokenRequest(Environment.getTodoFileUrl(element.getId(), file.getName()), HttpMethod.DELETE, new Json());
        }
        MultiPartRequest request = new MultiPartRequest(Environment.getTodoElementUrl(element.getId()) + "/files", HttpMethod.POST)
                .addFiles("files", element.getNewFiles().stream()
                        .map(TodoFile::getFile)
                        .collect(Collectors.toList()));
        makeTokenRequest(request);
        return response;
    }

    public static Set<TodoElement> getTodo() throws IOException {
        Set<TodoElement> elements = new HashSet<>();
        MultiPartRequest.Response response = makeTokenRequest(Environment.TODO, HttpMethod.GET, new Json());
        JsonList list = new JsonList(response.getResponse());
        for (Json json : list.getListWithJsons()) {
            elements.add(JsonConverter.convertToObject(json, TodoElement.class));
        }
        return elements;
    }

    public static void saveFile(int todoId, TodoFile file, String folder) throws IOException {
        MultiPartRequest.Response response = makeTokenRequest(Environment.getTodoFileUrl(todoId, file.getName()),
                HttpMethod.GET, new Json());
        response.saveFile(folder + "/" + file.getName());
    }

    public static MultiPartRequest.Response getFinance() throws IOException {
        return makeTokenRequest(Environment.FINANCE, HttpMethod.GET, new Json());
    }

    public static void makeLogoutRequest() throws IOException {
        makeTokenRequest(Environment.LOGOUT, HttpMethod.POST, new Json());
    }

    public static MultiPartRequest.Response makeTokenRequest(String url, HttpMethod method, Json json) throws IOException {
        MultiPartRequest request = new MultiPartRequest(url, method)
                .addJsonData(json);
        return makeTokenRequest(request);
    }

    public static MultiPartRequest.Response makeTokenRequest(MultiPartRequest request) throws IOException {
        MultiPartRequest.Response response = request
                .setRequestHeader("Authorization", Environment.token)
                .send();
        if (response.getResponseCode() == 401) {
            AdolfFront.deleteUserProfile();
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
