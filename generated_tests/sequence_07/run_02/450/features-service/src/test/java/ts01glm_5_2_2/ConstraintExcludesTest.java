package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConstraintExcludesTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "ExcludedFeat").then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "SourceFeat")
            .formParam("excludedFeature", "ExcludedFeat")
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetTypeAndAccessorsViaProduct() {
        String productName = "TestProduct-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "ExcludedFeat").then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "SourceFeat")
            .formParam("excludedFeature", "ExcludedFeat")
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateBothFeaturesActive() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "ExcludedFeat").then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "SourceFeat")
            .formParam("excludedFeature", "ExcludedFeat")
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, "ExcludedFeat").then().statusCode(500);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", is(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlySourceActive() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "ExcludedFeat").then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "SourceFeat")
            .formParam("excludedFeature", "ExcludedFeat")
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, "SourceFeat").then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlyExcludedActive() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "ExcludedFeat").then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "SourceFeat")
            .formParam("excludedFeature", "ExcludedFeat")
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, "ExcludedFeat").then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateNeitherActive() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "ExcludedFeat").then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "SourceFeat")
            .formParam("excludedFeature", "ExcludedFeat")
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesWithExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "ExcludedFeat").then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "SourceFeat")
            .formParam("excludedFeature", "ExcludedFeat")
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, "SourceFeat").then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }
}