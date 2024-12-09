import calculator.model.payment.Payment;
import calculator.testdata.TestDataGenerator;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.PojoJsonParser;

import static io.restassured.RestAssured.given;

public class CreatePaymentTest extends BaseTest {
    String endpoint = getEndpoint("/v2/internal/calculation/append");
    String paymentId = TestDataGenerator.generateId();
    String currentDateISO = BaseTest.getCurrentDateISO();
    String calculationId = BaseTest.getCalculationId();
    Payment payment = new Payment(paymentId, calculationId, currentDateISO, "payment", 10000000);
    String paymentJSON = PojoJsonParser.parseToJson(payment);

    @DisplayName("Создание займа")
    @Description("Выдать займ")
    @Test
    public void testCreatePayment() {
        Allure.step("Отправка запроса на создание калькуляции");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(paymentJSON)
                .when()
                .post(endpoint);
        Allure.step("Проверяем статус ответа");
        response.then().statusCode(200);
    }
}
