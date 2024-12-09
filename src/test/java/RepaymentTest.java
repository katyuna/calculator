import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepaymentTest extends BaseTest {

    @DisplayName("Создание калькуляции")
    @Description("Создать калькуляцию с заданными параметрами")
    @Test
    @Order(1)
    public void testCreateCalculation() {
        System.out.println("Тест 1: Калькуляция. Тело запроса: " + calculationJSON);
        Allure.step("Отправка запроса на создание калькуляции");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(calculationJSON)
                .when()
                .post(calculationEndpoint);
        Allure.step("Проверяем статус ответа");
        response.then().statusCode(201);
        System.out.println("Тест 1: Калькуляция. Создание калькуляции завершено");
    }

    @DisplayName("Создание займа")
    @Description("Выдать займ")
    @Test
    @Order(2)
    public void testCreatePayment() {
        // Выводим тело запроса перед отправкой
        System.out.println("Тест 2: Создание займа. Тело запроса: " + paymentJSON);
        Allure.step("Отправка запроса на создание займа");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(paymentJSON)
                .when()
                .post(paymentEndpoint);
        Allure.step("Проверяем статус ответа");
        response.then().statusCode(200);
        System.out.println("Тест 2: Выдача займа. Создание займа завершено");
    }


}
