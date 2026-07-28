package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    static String BASE_URL;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        BASE_URL = env != null && !env.isEmpty() ? env : System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_shouldReturn201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "CPU-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_shouldReturn201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString();
        String requiredFeature = "FeatureB-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }
}