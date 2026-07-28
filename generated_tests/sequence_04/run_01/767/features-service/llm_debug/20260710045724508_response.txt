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
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addVeryLongFeatureToConfigurationShouldReturn500() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        String longFeature = new String(new char[2000]).replace('\0','f');
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, longFeature);
        assertEquals(500, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addExtremelyLongFeatureToProductShouldReturn500() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String veryLongFeature = new String(new char[3000]).replace('\0','x');
        Response resp = given().when().post("/products/{productName}/features/{featureName}", productName, veryLongFeature);
        assertEquals(500, resp.getStatusCode());
    }
}