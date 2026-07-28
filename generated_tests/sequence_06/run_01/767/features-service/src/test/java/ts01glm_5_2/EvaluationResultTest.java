package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationWithRequiresConstraintTriggersEvaluationResult() {
        String productName = "EvalResult-Req-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "Test feature A")
                .when().post("/products/" + productName + "/features/" + featureA)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "Test feature B")
                .when().post("/products/" + productName + "/features/" + featureB)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
                .when().post("/products/" + productName + "/constraints/requires")
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA)
                .then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationWithExcludesConstraintTriggersEvaluationResult() {
        String productName = "EvalResult-Excl-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "ExclFeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "ExclFeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "Excl test feature A")
                .when().post("/products/" + productName + "/features/" + featureA)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "Excl test feature B")
                .when().post("/products/" + productName + "/features/" + featureB)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when().post("/products/" + productName + "/constraints/excludes")
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA)
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB)
                .then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesTriggersEvaluationResult() {
        String productName = "EvalResult-CF-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "CFFeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "CF test feature")
                .when().post("/products/" + productName + "/features/" + featureA)
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA)
                .then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features")
                .then().statusCode(200);
    }
}