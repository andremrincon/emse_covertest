package ts01qwen3_7_plus;

import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testSetIdViaRequiresConstraintCreation() {
        String productName = "Prod-Req-" + UUID.randomUUID().toString();
        String f1 = "F1-" + UUID.randomUUID().toString();
        String f2 = "F2-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/features/{featureName}", productName, f1).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/features/{featureName}", productName, f2).then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSetIdViaExcludesConstraintCreation() {
        String productName = "Prod-Exc-" + UUID.randomUUID().toString();
        String f1 = "F1-" + UUID.randomUUID().toString();
        String f2 = "F2-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/features/{featureName}", productName, f1).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/features/{featureName}", productName, f2).then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
            .formParam("sourceFeature", f1)
            .formParam("excludedFeature", f2)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }
}