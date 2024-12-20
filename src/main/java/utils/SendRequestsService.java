package utils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SendRequestsService {

    public void sendNoParametersPost(String json, String endpoint) {

        Response response = given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(endpoint);
    }
}
