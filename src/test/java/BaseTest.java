import calculator.model.calculation.Calculation;
import calculator.model.calculation.SpecialTerms;
import calculator.model.payment.Payment;
import calculator.testdata.TestDataGenerator;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.junit.jupiter.api.BeforeAll;
import utils.PojoJsonParser;

import java.util.Arrays;
import java.util.List;

public class BaseTest {

    // Endpoints
    protected static String calculationEndpoint;
    protected static String paymentEndpoint;

    // Variables
    protected static String calculationId;
    protected static String paymentId;
    protected static String currentDateISO;

    // Calculation
    protected static SpecialTerms specialTerms;
    protected static Calculation calculation;
    protected static String calculationJSON;

    // Payment
    protected static Payment payment;
    protected static String paymentJSON;


    @BeforeAll
    public static void setUp() {
        //Отключить проверку сертификатов
        RestAssured.config = RestAssured.config()
                .sslConfig(new SSLConfig().relaxedHTTPSValidation());

        //Устанавливаем базовый URL
        RestAssured.baseURI = "https://seller-loans-ingress-controller.seller-loans.k8s.stage-xc/loans-calculator";

        // Генерация уникальных данных перед всеми тестами
        //Endpoints
        calculationEndpoint = getEndpoint("/v2/internal/calculation/create");
        paymentEndpoint = getEndpoint("/v2/internal/calculation/append");

        //IDs and dates
        calculationId = TestDataGenerator.generateId();
        paymentId = TestDataGenerator.generateId();
        currentDateISO = TestDataGenerator.getCurrentDateTimeISO();

        //Calculation
        specialTerms = new SpecialTerms(1.0, 1.0, 98, 1.0, 1);
        calculation = new Calculation(calculationId, "simple", specialTerms);
        calculationJSON = PojoJsonParser.parseToJson(calculation);
        //Payment
        payment = new Payment(paymentId, calculationId, currentDateISO, "payment", 10000000);
        // Создаем список с одним элементом и конвертируем его в JSON
        List<Payment> payments = Arrays.asList(payment);  // Список с объектом Payment
        paymentJSON = PojoJsonParser.parseToJson(payments);
    }

    protected static String getEndpoint(String path) {
        return RestAssured.baseURI + path;
    }

}
