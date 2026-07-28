package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName1)
            .formParam("description", "Test feature 1")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName2)
            .formParam("description", "Test feature 2")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithActivatedFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .formParam("description", "Test feature")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithEmptyProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}");

        response.then().statusCode(200);
    }
}