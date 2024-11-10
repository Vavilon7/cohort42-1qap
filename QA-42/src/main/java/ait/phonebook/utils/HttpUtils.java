package ait.phonebook.utils;

import ait.phonebook.dto.TokenDto;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static ait.phonebook.utils.HttpUtils.HttpMethods.*;
import static ait.phonebook.utils.Utils.isNullOrEmpty;
import static io.restassured.RestAssured.given;

public class HttpUtils {
    public static Properties properties = TestProperties.getINSTANCE().getProperties();
    public static final String LOGIN_ENDPOINT = properties.getProperty("login.endpoint");
    public static final String REGISTRATION_ENDPOINT = properties.getProperty("registration.endpoint");
    public static final String CONTACTS_ENDPOINT = properties.getProperty("contact.endpoint");


    public static <T> T postResponse(Object body, String endpoint, int statusCode, Class<T> responseClass) {
        return getResponse(POST, endpoint, null, statusCode, body).as(responseClass);
    }

    public static <T> T postResponseWithToken(Object body, String endpoint, int statusCode, String token, Class<T> responseClass) {
        return getResponse(POST, endpoint, token, statusCode, body).as(responseClass);
    }

    public static <T> T putResponse(Object body, String endpoint, int statusCode, String token, Class<T> responseClass) {
        return getResponse(PUT, endpoint, token, statusCode, body).as(responseClass);
    }

    public static <T> T getResponse(String token, String endpoint, int statusCode, Class<T> responseClass) {
        return getResponse(GET, endpoint, token, statusCode, null).as(responseClass);
    }

    public static <T> T deleteResponse(String token, String endpoint, int statusCode, Class<T> responseClass) {
        return getResponse(DELETE, endpoint, token, statusCode, null).as(responseClass);
    }

    private static Response getResponse(HttpMethods method, String endpoint, String token, int statusCode, Object body) {
        //RequestSpecification - это сам запрос, через него можно выделить общую часть запроса
        RequestSpecification requestSpecification = given().spec(getRequestSpecBuilder(token).build()).when().log().all();

        if (method.equals(POST)) { // - POST запрос
            return requestSpecification
                    .body(body)// Тело запроса (POST & PUT)
                    .post(endpoint)
                    .then()
                    .log().all()
                    .assertThat().statusCode(statusCode)
                    .extract().response();
        } else if (method.equals(PUT)) { // - PUT запрос
            return requestSpecification
                    .body(body)// Тело запроса (POST & PUT)
                    .put(endpoint)
                    .then()
                    .log().all()
                    .assertThat().statusCode(statusCode)
                    .extract().response();
        } else if (method.equals(GET)) { // - GET запрос
            return requestSpecification
                    .get(endpoint)
                    .then()
                    .log().all()
                    .assertThat().statusCode(statusCode)
                    .extract().response();
        } else if (method.equals(DELETE)) {// - DELETE запрос
            return requestSpecification
                    .delete(endpoint)
                    .then()
                    .log().all()
                    .assertThat().statusCode(statusCode)
                    .extract().response();
        }
        return null;
    }

    //Помогает нам установить параметры общие для наших запросов/ В данном случае мы добавляем хэдэры к нашим запросам
    private static RequestSpecBuilder getRequestSpecBuilder(String token) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        if (!isNullOrEmpty(token)) {
            headers.put("Authorization", token);
        }
        requestSpecBuilder.addHeaders(headers);
        return requestSpecBuilder;
    }

    public enum HttpMethods {
        GET,
        POST,
        PUT,
        DELETE
    }


}
