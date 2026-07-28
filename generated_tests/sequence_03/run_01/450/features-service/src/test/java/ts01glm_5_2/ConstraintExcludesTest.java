package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.UUID;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintCoversSetSourceAndSetExcluded() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "SrcF-" + UUID.randomUUID();
        String excludedFeature = "ExcF-" + UUID.randomUUID();

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
    public void testEvaluateConfigurationBothFeaturesActiveCoversTrueBranch() {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();
        String sourceFeature = "SrcF-" + UUID.randomUUID();
        String excludedFeature = "ExcF-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .body("valid", is(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceActiveCoversFalseBranchSecondCondition() {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();
        String sourceFeature = "SrcF-" + UUID.randomUUID();
        String excludedFeature = "ExcF-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedActiveCoversFalseBranchFirstCondition() {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();
        String sourceFeature = "SrcF-" + UUID.randomUUID();
        String excludedFeature = "ExcF-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherFeatureActiveCoversFalseBranchBothConditions() {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();
        String sourceFeature = "SrcF-" + UUID.randomUUID();
        String excludedFeature = "ExcF-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .body("valid", is(true));
    }
}