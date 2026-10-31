package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSetIdViaRequiresConstraintCreation() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given()
                .pathParam("productName", productName)
                .when()
                .post("/products/{productName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", feature1)
                .formParam("description", "Description 1")
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", feature2)
                .formParam("description", "Description 2")
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

    @Test(timeout = 60000)
    public void testSetIdViaExcludesConstraintCreation() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given()
                .pathParam("productName", productName)
                .when()
                .post("/products/{productName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", feature1)
                .formParam("description", "Description 1")
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", feature2)
                .formParam("description", "Description 2")
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .formParam("sourceFeature", feature1)
                .formParam("excludedFeature", feature2)
                .when()
                .post("/products/{productName}/constraints/excludes")
                .then()
                .statusCode(201);
    }
}