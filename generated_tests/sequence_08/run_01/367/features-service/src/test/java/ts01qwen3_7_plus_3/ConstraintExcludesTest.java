package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Product-" + System.nanoTime();
        String sourceFeature = "FeatureA-" + System.nanoTime();
        String excludedFeature = "FeatureB-" + System.nanoTime();

        given()
                .pathParam("productName", productName)
                .when()
                .post("/products/{productName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", sourceFeature)
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", excludedFeature)
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes")
                .then()
                .statusCode(201);
    }
}