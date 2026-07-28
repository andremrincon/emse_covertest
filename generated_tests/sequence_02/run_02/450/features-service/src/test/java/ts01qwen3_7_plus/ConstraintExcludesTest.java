package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    static {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Prod-Excl-1-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeat-1";
        String excludedFeature = "ExcludedFeat-1";

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
        String productName = "Prod-Excl-2-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeat-2";
        String excludedFeature = "ExcludedFeat-2";
        String configName = "Config-2";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceActive() {
        String productName = "Prod-Excl-3-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeat-3";
        String excludedFeature = "ExcludedFeat-3";
        String configName = "Config-3";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedActive() {
        String productName = "Prod-Excl-4-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeat-4";
        String excludedFeature = "ExcludedFeat-4";
        String configName = "Config-4";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothInactive() {
        String productName = "Prod-Excl-6-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeat-6";
        String excludedFeature = "ExcludedFeat-6";
        String configName = "Config-6";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testGetProductConstraintsType() {
        String productName = "Prod-Excl-5-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeat-5";
        String excludedFeature = "ExcludedFeat-5";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .body("constraints.type", hasItem("excludes"));
    }
}