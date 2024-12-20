package calculator;

import basetest.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepaymentTest extends BaseTest {

    @DisplayName("Создание калькуляции")
    @Description("Создать калькуляцию с заданными параметрами")
    @Test
    @Order(1)
    public void testCreateCalculation() {
        System.out.println("--- Тест 1 Запущен. Калькуляция. Тело запроса: " + calculationJSON);
        Allure.step("Отправка запроса на создание калькуляции");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(calculationJSON)
                .when()
                .post(calculationEndpoint);
        Allure.step("Проверка статуса ответа");
        response.then().statusCode(201);
        System.out.println("--- Создание калькуляции завершено");
    }

    @DisplayName("Создание займа")
    @Description("Выдать займ")
    @Test
    @Order(2)
    public void testCreatePayment() {
        // Выводим тело запроса перед отправкой
        System.out.println("--- Тест 2 Запущен. Создание займа. Тело запроса: " + paymentJSON);
        Allure.step("Отправка запроса на создание займа");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(paymentJSON)
                .when()
                .post(paymentEndpoint);
        //System.out.println("Response Body: " + response.getBody().asString());
        Allure.step("Проверка статуса ответа");
        response.then().statusCode(200);
        System.out.println("--- Создание займа завершено");
    }

    @DisplayName("Получение графика выплат")
    @Description("Получить график выплат")
    @Test
    @Order(3)
    public void testSchedule() {
        System.out.println("--- Тест 3 Запущен. Получение графика платежей");
        Allure.step("Отправка запроса на создание графика платежей");
        Response response = given()
                .header("Content-Type", "application/json")
                .queryParam("calculation_id", calculationId)
                .queryParam("calculation_date", currentDate)
                .when()
                .post(scheduleEndpoint);

        Allure.step("Проверка статуса ответа");
        response.then().statusCode(200);
        System.out.println("График выплат получен");

        Allure.step("Проверка, что график не пустой");
        String responseBody = response.getBody().asString();
        Assertions.assertFalse(responseBody.isEmpty(), "График не должен быть пустым");

        Allure.step("Проверка, что график содержит платежи");
        // Преобразование ответа в список объектов
        List<Object> scheduleList = response.jsonPath().getList("$");
        //Проверяем, что массив не пустой
        Assertions.assertFalse(scheduleList.isEmpty(), "График должен содержать платежи");

        Allure.step("Проверка, что итоговая сумма выплат больше суммы займа и никакой платеж не потерялся");
        // Получение суммы по полю amount_exp2
        int totalAmountExp2 = 0;
        for (Object schedule : scheduleList) {
            int amountExp2 = (int) ((Map<String, Object>) schedule).get("amount_exp2");
            totalAmountExp2 += amountExp2;
        }
        System.out.println("Сумма по всем amount_exp2: " + totalAmountExp2);
        System.out.println("Тело кредита: " + payment.amountExp2);

        // Проверка условия с JUnit
        assertTrue(payment.amountExp2 < totalAmountExp2,
                "Ожидается, что тело кредита меньше чем конечная выплата по кредиту, которая включает проценты, комиссии и пени");

        Allure.step("Проверка, что  общая сумма по всему графику платежей (сумма всех платежей) больше, чем тело кредита на сумму процентов, комиссий и пени");

        // Суммирование проценты + комиссии + пени
        int totalPerComPenAmountExp2 = 0;
        for (Object schedule : scheduleList) {
            int commissionExp2 = (int) ((Map<String, Object>) schedule).get("commission_exp2");
            int percentsAmountExp2 = (int) ((Map<String, Object>) schedule).get("percents_amount_exp2");
            int penaltyExp2 = (int) ((Map<String, Object>) schedule).get("penalty_exp2");
            totalPerComPenAmountExp2  += commissionExp2 + percentsAmountExp2 + penaltyExp2;
        }
        System.out.println("Сумма процентов, комиссий и пени: " + totalPerComPenAmountExp2);
        // Проверка с JUnit
        assertEquals(totalAmountExp2 - payment.amountExp2, totalPerComPenAmountExp2, "Общая сумма по всему графику платежей больше, чем тело кредита на сумму процентов и комиссий");
    }
}

