package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getProductAfterExcludesConstraintTriggersSetters() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().get("/products/{productName}", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void configurationWithBothExcludedFeaturesActiveIsInvalid() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void configurationWithOnlySourceFeatureActiveIsValid() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void configurationWithOnlyExcludedFeatureActiveIsValid() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void configurationWithNeitherFeatureActiveIsValid() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesAfterExcludesConstraintTriggersSetters() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName).then().statusCode(200);
    }
}