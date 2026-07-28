package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null
            ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.config = RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig()
                        .defaultContentCharset("UTF-8"));
    }

    @Test(timeout = 60000)
    public void excludesConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "ExclTest-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatA-" + UUID.randomUUID().toString().substring(0, 6);
        String featureB = "FeatB-" + UUID.randomUUID().toString().substring(0, 6);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 6);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureA)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureB)
                .then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configName, featureA)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configName, featureB)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void requiresConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "ReqTest-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "ReqA-" + UUID.randomUUID().toString().substring(0, 6);
        String featureB = "ReqB-" + UUID.randomUUID().toString().substring(0, 6);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 6);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureA)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureB)
                .then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configName, featureA)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void excludesConstraintViolationOnConfigurationEvaluationReturns500() {
        String productName = "ExclEval-" + UUID.randomUUID().toString().substring(0, 8);
        String featureX = "FeatX-" + UUID.randomUUID().toString().substring(0, 6);
        String featureY = "FeatY-" + UUID.randomUUID().toString().substring(0, 6);
        String configName = "CfgEval-" + UUID.randomUUID().toString().substring(0, 6);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureX)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureY)
                .then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", featureX)
                .formParam("excludedFeature", featureY)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configName, featureX)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configName, featureY)
                .then().statusCode(500);

        given().when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));
    }
}