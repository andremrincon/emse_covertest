package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Source-" + UUID.randomUUID();
        String excludedFeature = "Excluded-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothActive() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Source-" + UUID.randomUUID();
        String excludedFeature = "Excluded-" + UUID.randomUUID();
        String configName = "Config-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveOnly() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Source-" + UUID.randomUUID();
        String excludedFeature = "Excluded-" + UUID.randomUUID();
        String configName = "Config-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationExcludedActiveOnly() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Source-" + UUID.randomUUID();
        String excludedFeature = "Excluded-" + UUID.randomUUID();
        String configName = "Config-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherActive() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Source-" + UUID.randomUUID();
        String excludedFeature = "Excluded-" + UUID.randomUUID();
        String configName = "Config-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }
}