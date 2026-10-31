package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DuplicatedObjectExceptionTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testDuplicatedProductCreation() {
        String productName = "Product_" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDuplicatedFeatureCreation() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String featureName = "Feature_" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDuplicatedConfigurationCreation() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String configurationName = "Config_" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
            .statusCode(201);
    }
}