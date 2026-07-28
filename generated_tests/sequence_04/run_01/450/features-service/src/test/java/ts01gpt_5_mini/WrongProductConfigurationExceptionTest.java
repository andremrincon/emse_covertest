package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddingMalformedFeatureToConfigurationReturnsInternalServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        String longFeatureName = new StringBuilder().append("feature-").append(UUID.randomUUID().toString()).append("-").append(new String(new char[600]).replace("\0","x")).toString();
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, longFeatureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testCreatingConfigurationWithExcessivelyLongNameReturnsInternalServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String longConfigName = "cfg-" + UUID.randomUUID().toString() + "-" + new String(new char[800]).replace("\0","C");
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, longConfigName).then().statusCode(500);
    }
}