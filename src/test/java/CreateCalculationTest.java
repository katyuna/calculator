import calculator.model.calculation.Calculation;
import calculator.model.calculation.SpecialTerms;
import calculator.testdata.TestDataGenerator;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.PojoJsonParser;

import static io.restassured.RestAssured.given;

public class CreateCalculationTest extends BaseTest {

    String endpoint = getEndpoint("/v2/internal/calculation/create");
    TestDataGenerator testDataGenerator = new TestDataGenerator();
    PojoJsonParser pojoJsonParser = new PojoJsonParser();

    @Test
    public void testCreateCalculation() {

        //Генерируем данные для калькуляции
        String calculation_id = TestDataGenerator.generateCalculationId();
        SpecialTerms specialTerms = new SpecialTerms(1.0, 1.0, 104, 1.0, 1);
        Calculation calculation = new Calculation(calculation_id, "simple", specialTerms);

        //Преобразуем POJO в JSON
        String calculationJSON = PojoJsonParser.parseToJson(calculation);

        //Отправляем запрос
        Response response = given()
                .header("Content-Type", "application/json")
                .body(calculationJSON)
                .when()
                .post(endpoint);
        // Проверяем статус ответа
        response.then().statusCode(201);
    }
}