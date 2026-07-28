package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    private String baseUrl;
    private String productName;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
        productName = "Laptop-Pro-15-" + UUID.randomUUID().toString();
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", "CPU-i9-13900H")
            .formParam("excludedFeature", "Integrated-Graphics-Only")
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductToTriggerConstraintLoad() {
        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", "CPU-i9-13900H")
            .formParam("excludedFeature", "Integrated-Graphics-Only")
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
        .when()
            .get("/products/{productName}")
        .then()
            .statusCode(200);
    }
}