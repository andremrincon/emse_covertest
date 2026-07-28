package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithoutConstraints() {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithRequiresConstraint() {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));

        Response response = given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraint() {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature).then().statusCode(500);

        Response response = given().when().get("/products/" + productName + "/configurations/" + configurationName);

        response.then().body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesAfterEvaluation() {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features");

        response.then().body("$", hasItem(requiredFeature));
    }
}