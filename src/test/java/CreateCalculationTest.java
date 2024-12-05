import calculator.model.calculation.Calculation;
import calculator.model.calculation.SpecialTerms;
import calculator.testdata.TestDataGenerator;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.junit.Test;
import utils.PojoJsonParser;

import static io.restassured.RestAssured.given;

public class CreateCalculationTest extends BaseTest{

    String endpoint = "https://seller-loans-ingress-controller.seller-loans.k8s.stage-xc/loans-calculator/v2/internal/calculation/create";



    @Test
    public void testCreateCalculation() {
        // Отключаем проверку серта - нужно вынести на уровень выше
        RestAssured.config = RestAssured.config()
                .sslConfig(new SSLConfig().relaxedHTTPSValidation());

        System.out.println(endpoint);
        TestDataGenerator testDataGenerator = new TestDataGenerator();
        String calculation_id = testDataGenerator.generateCalculationId();

        //Генерируем данные для калькуляции
        SpecialTerms specialTerms = new SpecialTerms(1.0, 1.0, 104, 1.0, 1);
        Calculation calculation = new Calculation(calculation_id, "simple", specialTerms);
        //Преобразуем POJO в JSON
        PojoJsonParser pojoJsonParser = new PojoJsonParser();
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