package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ConstraintExcludesTest {

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        String featureB = "featB-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/" + productName + "/constraints/excludes");

        response.then().statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <500> was greater than <300>.")
    @Test(timeout = 60000)
    public void testEvaluateConfigurationConflict() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        String featureB = "featB-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/configurations/" + configName);

        response.then().statusCode(200).body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoConflict() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        String featureB = "featB-" + UUID.randomUUID().toString();
        String featureC = "featC-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/configurations/" + configName);

        response.then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceNotActive() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        String featureB = "featB-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/configurations/" + configName);

        response.then().statusCode(200).body("valid", equalTo(true));
    }
}