package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Laptop-Pro-15-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/CPU-i9-13900H")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Integrated-Graphics-Only")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .formParam("sourceFeature", "CPU-i9-13900H")
            .formParam("excludedFeature", "Integrated-Graphics-Only")
            .when()
            .post("/products/" + productName + "/constraints/excludes");

        response.then().statusCode(201);
    }
}