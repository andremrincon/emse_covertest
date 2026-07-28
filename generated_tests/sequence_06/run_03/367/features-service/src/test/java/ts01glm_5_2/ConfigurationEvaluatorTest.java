package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithNoConstraintsTest() {
        String productName = "EvalTestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "EvalTestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithRequiresConstraintValidTest() {
        String productName = "EvalReqProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "EvalReqConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithRequiresConstraintInvalidTest() {
        String productName = "EvalInvProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "EvalInvConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "inv-src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "inv-req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithExcludesConstraintValidTest() {
        String productName = "EvalExcProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "EvalExcConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "exc-src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature)
                .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithExcludesConstraintConflictTest() {
        String productName = "EvalConfProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "EvalConfConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "conf-src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "conf-exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature)
                .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithMultipleConstraintsTest() {
        String productName = "EvalMultiProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "EvalMultiConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "multi-a-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "multi-b-" + UUID.randomUUID().toString().substring(0, 8);
        String featureC = "multi-c-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("requiredFeature", featureB)
                .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureC)
                .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
                .then().statusCode(200);
    }
}