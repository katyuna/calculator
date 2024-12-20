import calculator.model.calculation.Calculation;
import calculator.model.calculation.SpecialTerms;

import calculator.model.payment.Payment;
import calculator.testdata.TestDataGenerator;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.PojoJsonParser;

import java.util.Arrays;
import java.util.List;

import static calculator.testdata.TestDataGenerator.getTotalDaysUntilMonday;
import static io.restassured.RestAssured.given;


public class EndOfLoanNotMondayTest extends BaseTest{
    @DisplayName("400, если последняя выплата по графику приходится на вт - пт")
    @Description("Проверка, что если последняя выплата приходится на вт - пт, то будет ошибка 400 и сообщение вида End date should be MONDAY, 2025-03-30 is not")
    @Test
    @Order(1)
    public void testEndOfLoanNotMonday() {

        String id = TestDataGenerator.generateId();
        //Calculation
        int incorrectLoanTerm = getTotalDaysUntilMonday(90) + 1;
        SpecialTerms specialTerms = new SpecialTerms(1.0, 1.0, incorrectLoanTerm, 1.0, 1);
        Calculation calculation = new Calculation(id, "simple", specialTerms);
        String calculationJson = PojoJsonParser.parseToJson(calculation);
        System.out.println(calculationJson);

        Allure.step("Создание калькуляции");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(calculationJson)
                .when()
                .post(calculationEndpoint);
        Allure.step("Проверка статуса ответа");
        response.then().statusCode(201);


        //Payment
        payment = new Payment(id, id, currentDateISO, "payment", 10000000);
        // Создаем список с одним элементом и конвертируем его в JSON
        List<Payment> payments = Arrays.asList(payment);  // Список с объектом Payment
        String paymentJson = PojoJsonParser.parseToJson(payments);
        System.out.println(paymentJson);

        Allure.step("Выдача займа");
        Response response2 = given()
                .header("Content-Type", "application/json")
                .body(paymentJson)
                .when()
                .post(paymentEndpoint);
        System.out.println(response2.toString());
        Allure.step("Проверка статуса ответа 400");
        response2.then().statusCode(400);
    }
}
