import calculator.model.calculation.Calculation;
import calculator.model.calculation.SpecialTerms;
import calculator.testdata.TestDataGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.PojoJsonParser;

import static io.restassured.RestAssured.given;

public class CreateCalculationTest extends BaseTest{

    String endpoint = getEndpoint("/v2/internal/calculation/create");
    TestDataGenerator testDataGenerator = new TestDataGenerator();
    PojoJsonParser pojoJsonParser = new PojoJsonParser();

    @Test
    public void testCreateCalculation() {
        // Отключаем проверку серта - нужно вынести на уровень выше
        //RestAssured.config = RestAssured.config()
        //        .sslConfig(new SSLConfig().relaxedHTTPSValidation());

        System.out.println(endpoint);

        String calculation_id = testDataGenerator.generateCalculationId();

        //Генерируем данные для калькуляции
        SpecialTerms specialTerms = new SpecialTerms(1.0, 1.0, 104, 1.0, 1);
        Calculation calculation = new Calculation(calculation_id, "simple", specialTerms);
        //Преобразуем POJO в JSON

        String calculationJSON = pojoJsonParser.parseToJson(calculation);
        System.out.println(calculationJSON);
        //Отправляем запрос
        Response response = given()
                .header("Content-Type", "application/json")
                .body(calculationJSON)
                .when()
                .post(endpoint);
        // Получаем тело ответа
        String responseBody = response.getBody().asString();

        // Проверяем статус ответа
        response.then().statusCode(201);
    }
}