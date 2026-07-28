package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_exercisesSetSourceFeatureNameAndSetExcludedFeatureName() {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/" + productName + "/constraints/excludes")
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void retrieveProductAfterExcludesConstraintCreation_exercisesSettersDuringHydration() {
        String productName = "TestProduct-Hydrate-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithBothExcludedFeaturesActive_returnsInvalid() {
        String productName = "TestProduct-BothActive-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcA-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcA-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
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
    public void evaluateConfigurationWithOnlySourceFeatureActive_returnsValid() {
        String productName = "TestProduct-SourceOnly-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcS-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcS-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
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
    public void evaluateConfigurationWithOnlyExcludedFeatureActive_returnsValid() {
        String productName = "TestProduct-ExcludedOnly-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcE-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcE-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
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
    public void evaluateConfigurationWithNeitherFeatureActive_returnsValid() {
        String productName = "TestProduct-Neither-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcN-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcN-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
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