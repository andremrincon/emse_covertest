package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetProductLoadsConstraintsAndTriggersSetId() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationTriggersConstraintSetId() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatureC-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatureD-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintTriggersSetId() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatureE-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatureF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        Response productResponse = given().when().get("/products/" + productName);
        productResponse.then().statusCode(lessThan(300));
        Integer constraintId = productResponse.jsonPath().get("constraints[0].id");

        given()
        .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
        .then()
            .statusCode(204);
    }
}