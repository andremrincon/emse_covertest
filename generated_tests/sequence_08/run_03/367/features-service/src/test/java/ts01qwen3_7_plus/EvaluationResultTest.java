package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesTriggersEvaluation() {
        String productName = "EvalProduct1-" + System.currentTimeMillis();
        String configurationName = "eval-config-1";
        String featureName = "feature-1";

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithRequiresConstraintTriggersEvaluation() {
        String productName = "EvalProduct2-" + System.currentTimeMillis();
        String configurationName = "eval-config-2";
        String feature1 = "feature-req-1";
        String feature2 = "feature-req-2";

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature1).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature2).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", feature1).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", feature1).formParam("requiredFeature", feature2).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithExcludesConstraintTriggersEvaluation() {
        String productName = "EvalProduct3-" + System.currentTimeMillis();
        String configurationName = "eval-config-3";
        String feature1 = "feature-excl-1";
        String feature2 = "feature-excl-2";

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature1).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature2).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", feature1).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", feature2).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post("/products/{productName}/constraints/excludes").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .statusCode(200);
    }
}