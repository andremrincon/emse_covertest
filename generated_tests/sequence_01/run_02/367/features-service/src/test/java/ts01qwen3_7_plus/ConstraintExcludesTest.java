package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "P_" + UUID.randomUUID().toString();
        String sourceFeature = "F1_" + UUID.randomUUID().toString();
        String excludedFeature = "F2_" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_SourceFalse() {
        String productName = "P_" + UUID.randomUUID().toString();
        String sourceFeature = "F1_" + UUID.randomUUID().toString();
        String excludedFeature = "F2_" + UUID.randomUUID().toString();
        String configName = "C_" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_SourceTrue_ExcludedFalse() {
        String productName = "P_" + UUID.randomUUID().toString();
        String sourceFeature = "F1_" + UUID.randomUUID().toString();
        String excludedFeature = "F2_" + UUID.randomUUID().toString();
        String configName = "C_" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_BothTrue() {
        String productName = "P_" + UUID.randomUUID().toString();
        String sourceFeature = "F1_" + UUID.randomUUID().toString();
        String excludedFeature = "F2_" + UUID.randomUUID().toString();
        String configName = "C_" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", equalTo(false));
    }
}