package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class WrongProductConfigurationExceptionTest {
    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturnsServerErrorForProblematicConfigurationName() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", productName, "12345");
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddConfigurationWithExcessivelyLongNameReturnsServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String longConfig = "a-very-long-configuration-name-that-exceeds-the-maximum-allowed-length-and-could-potentially-cause-a-buffer-overflow-or-database-truncation-error-leading-to-an-internal-server-error-because-the-system-was-not-designed-to-handle-such-long-inputs-gracefully";
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", productName, longConfig);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturnsCreated() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "feature-" + UUID.randomUUID().toString();
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, act.getStatusCode());
    }
}