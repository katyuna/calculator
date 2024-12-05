package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import java.util.Map;

public class RestClient {
    // Общая часть URL
    public final String BASE_URL = "https://seller-loans-ingress-controller.seller-loans.k8s.stage-xc/loans-calculator/";

    // Базовая спецификация для всех запросов
    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON) // Content-Type: application/json
                .setBaseUri(BASE_URL)             // Устанавливаем базовый URL
                .build();
    }

    // GET-запрос с query-параметрами
    public Response sendGetWithParams(String endpoint, Map<String, String> queryParams) {
        return RestAssured.given()
                .spec(getBaseSpec()) // Базовая спецификация
                .queryParams(queryParams) // Добавляем query-параметры
                .get(endpoint); // Выполняем GET-запрос
    }

    // POST-запрос без параметров
    public Response sendPostWithParams(String endpoint, Object body) {
        return RestAssured.given()
                .spec(getBaseSpec()) // Базовая спецификация
                .body(body) // Устанавливаем тело запроса
                .post(endpoint); // Выполняем POST-запрос
    }
    // POST-запрос с query-параметрами и телом
    public Response sendPostWithParams(String endpoint, Map<String, String> queryParams, Object body) {
        return RestAssured.given()
                .spec(getBaseSpec()) // Базовая спецификация
                .queryParams(queryParams) // Добавляем query-параметры
                .body(body) // Устанавливаем тело запроса
                .post(endpoint); // Выполняем POST-запрос
    }
}
