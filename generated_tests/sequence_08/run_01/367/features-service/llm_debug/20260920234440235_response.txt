package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @Before
    public void setUp() {
        String envUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoConstraints() {
        String productName = "Product-NC-" + UUID.randomUUID().toString();
        String featureName = "Feature-NC";
        String configName = "Config-NC";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationRequiresConstraint() {
        String productName = "Product-RC-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-Feature";
        String requiredFeature = "Required-Feature";
        String configName = "Config-RC";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationExcludesConstraint() {
        String productName = "Product-EC-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-Feature-EC";
        String excludedFeature = "Excluded-Feature-EC";
        String configName = "Config-EC";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
                .then()
                .statusCode(500);
    }
}