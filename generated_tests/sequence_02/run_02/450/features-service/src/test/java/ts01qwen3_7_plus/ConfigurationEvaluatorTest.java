package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    private String productName;
    private String configurationName;

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        productName = "Product-" + UUID.randomUUID().toString();
        configurationName = "Config-" + UUID.randomUUID().toString();
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoConstraints() {
        String featureA = "FeatureA-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithRequiresConstraint() {
        String featureA = "FeatureA-" + UUID.randomUUID().toString();
        String featureB = "FeatureB-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then()
            .statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraint() {
        String featureA = "FeatureA-" + UUID.randomUUID().toString();
        String featureB = "FeatureB-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then()
            .statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA)
            .then()
            .statusCode(500);
    }
}