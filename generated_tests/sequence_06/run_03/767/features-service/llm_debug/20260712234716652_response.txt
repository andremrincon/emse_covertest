package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Product-" + System.nanoTime();
        String sourceFeature = "Source-" + System.nanoTime();
        String requiredFeature = "Required-" + System.nanoTime();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }
}