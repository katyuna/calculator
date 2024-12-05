import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        // Отключить проверку сертификатов
        RestAssured.config = RestAssured.config()
                .sslConfig(new SSLConfig().relaxedHTTPSValidation());
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://seller-loans-ingress-controller.seller-loans.k8s.stage-xc/loans-calculator";
    }

}
