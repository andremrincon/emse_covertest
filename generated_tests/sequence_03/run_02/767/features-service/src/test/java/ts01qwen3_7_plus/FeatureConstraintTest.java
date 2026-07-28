package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Test(timeout = 60000)
    public void testSetIdViaRequiresConstraintCreation() {
        String productName = "Product-SetId-Test-" + System.nanoTime();
        String feature1 = "Feature1-SetId-" + System.nanoTime();
        String feature2 = "Feature2-SetId-" + System.nanoTime();

        given()
                .pathParam("productName", productName)
                .when()
                .post("/products/{productName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", feature1)
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", feature2)
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
                .when()
                .post("/products/{productName}/constraints/requires")
                .then()
                .statusCode(201);
    }
}