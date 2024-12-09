import calculator.model.calculation.Calculation;
import calculator.model.calculation.SpecialTerms;
import calculator.testdata.TestDataGenerator;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.PojoJsonParser;

import static io.restassured.RestAssured.given;

public class CreateCalculationTest extends BaseTest {
    String endpoint = getEndpoint("/v2/internal/calculation/create");
    String calculationId = BaseTest.getCalculationId();
    SpecialTerms specialTerms = new SpecialTerms(1.0, 1.0, 104, 1.0, 1);
    Calculation calculation = new Calculation(calculationId, "simple", specialTerms);
    String calculationJSON = PojoJsonParser.parseToJson(calculation);

    @DisplayName("Создание калькуляции")
    @Description("Создать калькуляцию с заданными параметрами")
    @Test

    public void testCreateCalculation() {

        Allure.step("Отправка запроса на создание калькуляции");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(calculationJSON)
                .when()
                .post(endpoint);
        Allure.step("Проверяем статус ответа");
        response.then().statusCode(201);
    }
}