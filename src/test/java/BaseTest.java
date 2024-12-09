import calculator.testdata.TestDataGenerator;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    TestDataGenerator testDataGenerator = new TestDataGenerator();
    protected static String calculationId = TestDataGenerator.generateId();

    protected static String currentDateISO = TestDataGenerator.getCurrentDateTimeISO();

    // Статический метод для получения calculationId
    public static String getCalculationId() {
        return calculationId;
    }

    public static String getCurrentDateISO() {
        return currentDateISO;
    }

    protected String getEndpoint(String path) {
        return RestAssured.baseURI + path;
    }

    @BeforeAll
    public static void setUp() {
        // Отключить проверку сертификатов
        RestAssured.config = RestAssured.config()
                .sslConfig(new SSLConfig().relaxedHTTPSValidation());
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://seller-loans-ingress-controller.seller-loans.k8s.stage-xc/loans-calculator";
    }




}
