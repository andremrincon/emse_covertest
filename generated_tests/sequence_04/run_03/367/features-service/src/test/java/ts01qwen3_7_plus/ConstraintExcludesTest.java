package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintToCoverSetters() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();

        RestAssured.given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("description", "desc1")
                .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("description", "desc2")
                .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(201);
    }
}