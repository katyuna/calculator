package loans;

import basetest.BaseTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.PojoJsonParser;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestForScheduleFromLoansApi extends BaseTest {

    protected String endpoint;
    protected String customerId;

    @DisplayName("Получить график из сервиса займов")
    @Description("Получение графика из loans API")
    @Test
    @Order(1)
    public void testGetLoanSchedule() {
        Allure.step("Установка параметров запроса");
        endpoint = "https://seller-loans-ingress-controller.seller-loans.k8s.stage-xc/loans-api/v1/loans";
        customerId = "3c1fb8d8-2ccc-45f5-837f-63a1a96c0a1a";

        Allure.step("Отправка GET-запроса к loans API: получить информацию по займу и график платежей");
        Response response = given()
                .header("customer-id", customerId)
                .header("Accept", "application/json")
                .when()
                .get(endpoint);

        Allure.step("Проверка статуса ответа");
        response.then().statusCode(200);

        Allure.step("Проверка структуры JSON-ответа");
        String responseBody = response.getBody().asString();
        Assertions.assertNotNull(responseBody, "Ответ от API не должен быть пустым");

        Allure.step("Проверка дней недели в графике");


    }
}



