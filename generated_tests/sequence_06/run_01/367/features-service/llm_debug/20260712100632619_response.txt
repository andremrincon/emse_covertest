package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class EvaluationResultTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationWithRequiresConstraintSatisfiedReturnsValid() {
        String productName = "EvalTest-ReqValid-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "source-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "required-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, requiredFeature)
            .then()
                .statusCode(500);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(200)
                .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void getConfigurationWithExcludesConstraintViolatedReturnsInvalid() {
        String productName = "EvalTest-ExclInvalid-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "excl-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
            .then()
                .statusCode(500);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(200)
                .body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void getConfigurationActiveFeaturesTriggersEvaluation() {
        String productName = "EvalTest-ActiveFeats-" + UUID.randomUUID().toString().substring(0, 8);
        String featureOne = "feat-one-" + UUID.randomUUID().toString().substring(0, 8);
        String featureTwo = "feat-two-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureOne)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureTwo)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureOne)
                .formParam("requiredFeature", featureTwo)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureOne)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureTwo)
            .then()
                .statusCode(500);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200);
    }
}