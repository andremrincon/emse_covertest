package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothActive() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(500);

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceActive() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedActive() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherActive() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(200);
    }
}