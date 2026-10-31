package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();

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
                .pathParam("featureName", requiredFeature)
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(lessThan(300));

        RequestSpecification req = given()
                .pathParam("productName", productName)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature);

        req.when()
                .post("/products/{productName}/constraints/requires")
                .then()
                .statusCode(201);
    }
}